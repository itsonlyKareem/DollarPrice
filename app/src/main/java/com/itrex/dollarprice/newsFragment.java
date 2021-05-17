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

public class newsFragment extends Fragment {

    public newsFragment() {
        // Required empty public constructor
    }

    InterstitialAd interstitialAd;
    AdView adView;

    RecyclerView recyclerView;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    List<NewsModel> List_news = listenToDocument("news");

    NewsAdapter newsAdapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_news, container, false);

        MobileAds.initialize(getActivity());
        adView = v.findViewById(R.id.adView_news);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        interstitialAd = new InterstitialAd(getActivity());
        interstitialAd.setAdUnitId("ca-app-pub-9037650239384734/4599263055");
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
        recyclerView = v.findViewById(R.id.recyclerView_news);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(newsAdapter);


        return v;
    }


    private List<NewsModel> listenToDocument(String path) {
        List<NewsModel> tempData = new ArrayList<>();
        db.collection(path)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error != null) {
                            System.out.println("Listen Failed");
                        }

                        List<NewsModel> fbData = new ArrayList<>();
                        for (QueryDocumentSnapshot doc : value) {
                            doc.getData();
                            NewsModel newsModel = doc.toObject(NewsModel.class);
                            fbData.add(newsModel);
                        }

                        NewsAdapter newsAdapter = new NewsAdapter(tempData);
                        tempData.clear();
                        tempData.addAll(fbData);
                        newsAdapter.notifyDataSetChanged();
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerView.setAdapter(newsAdapter);
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