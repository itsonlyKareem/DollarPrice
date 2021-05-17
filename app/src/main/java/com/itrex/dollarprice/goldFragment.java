package com.itrex.dollarprice;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;

import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.List;

public class goldFragment extends Fragment {

    public goldFragment() {
        // Required empty public constructor
    }

    InterstitialAd interstitialAd;
    AdView adView;

    RecyclerView recyclerView; // List of gold names.

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    List<GoldModel> List_gold = listenToDocument("gold"); // Available gold names.

    GoldAdapter goldAdapter; // Adapter to view the data in the Recycler View.



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_gold, container, false);

        MobileAds.initialize(getActivity());
        adView = v.findViewById(R.id.adView_gold);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        interstitialAd = new InterstitialAd(getActivity());
        interstitialAd.setAdUnitId("ca-app-pub-9037650239384734/4954486276");
        AdRequest adRequest1 = new AdRequest.Builder().build();
        interstitialAd.loadAd(adRequest1);

        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
            }

            @Override
            public void onAdLoaded() {
                showInterstitial();
            }
        });

        // Setting up the Recycler View.
        recyclerView = v.findViewById(R.id.recyclerView_gold);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(goldAdapter);


        return v;
    }

    private List<GoldModel> listenToDocument(String path) {
        List<GoldModel> tempData = new ArrayList<>();
        db.collection(path)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error != null) {
                            System.out.println("Listen Failed.");
                        }

                        List<GoldModel> fbData = new ArrayList<>();
                        for (QueryDocumentSnapshot doc : value) {
                            doc.getData();
                            GoldModel goldModel = doc.toObject(GoldModel.class);
                            fbData.add(goldModel);
                        }

                        GoldAdapter goldAdapter = new GoldAdapter(tempData);
                        tempData.clear();
                        tempData.addAll(fbData);
                        goldAdapter.notifyDataSetChanged();
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerView.setAdapter(goldAdapter);
                    }

                });

        return tempData;
    }

    private void showInterstitial() {
        if (interstitialAd.isLoaded()) {
            interstitialAd.show();
        }
    }
}