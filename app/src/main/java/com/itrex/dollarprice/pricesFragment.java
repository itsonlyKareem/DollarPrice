package com.itrex.dollarprice;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

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

public class pricesFragment extends Fragment {

    public pricesFragment() {
        // Required empty public constructor
    }
    InterstitialAd interstitialAd;
    AdView pricesAdView; // Ad banner.
    RecyclerView recyclerView; // List of banks.
    Spinner spinner; // Container for available currencies.
    ImageView currencyFlag; // Flag corresponding to the currency.
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ImageView connectivity;

    PricesAdapter pricesAdapter; // Adapter to view the data in the Recycler View.

    List <PricesModel> List_sar = listenToDocument("sar"); //
    List <PricesModel> List_usd = listenToDocument("usd"); //
    List <PricesModel> List_eur = listenToDocument("eur"); //
    List <PricesModel> List_kwd = listenToDocument("kwd"); // Available currencies.
    List <PricesModel> List_aed = listenToDocument("aed"); //
    List <PricesModel> List_qar = listenToDocument("qar"); //

    NetworkStatus status; // A Listener for internet connectivity.

    ProgressDialog progressDialog; // A warning dialog initialized if internet is not connected.




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_prices, container, false);

        // Initialize Mobile Ads SDK
        MobileAds.initialize(getActivity());

        // Add Ad mob
        pricesAdView = v.findViewById(R.id.adView_prices);
        AdRequest adRequest = new AdRequest.Builder().build();
        pricesAdView.loadAd(adRequest);


        interstitialAd = new InterstitialAd(getActivity());
        interstitialAd.setAdUnitId("ca-app-pub-9037650239384734/4408100482");
        AdRequest adRequest2 = new AdRequest.Builder().build();
        interstitialAd.loadAd(adRequest2);
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


        // Setting up the Recycler View
        recyclerView = v.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(pricesAdapter);

        // Setting up the Spinner
        final String[] currencies = getResources().getStringArray(R.array.Spinner_items);
        spinner = v.findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),R.layout.hint,currencies);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        // Setting up the flag corresponding to the chosen currency.
        currencyFlag = v.findViewById(R.id.currencyFlag);
        connectivity = v.findViewById(R.id.connectivity);

        // Setting up the Network Status.
        status = new NetworkStatus();


        // Choosing which currency shows up in the list
       spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               switch (currencies[position]) {
                   case "الدولار الأمريكي" : {
                       currencyFlag.setImageResource(R.drawable.usd);

                       if (isActive()) {
                           List_usd = listenToDocument("usd");
                           connectivity.setImageResource(R.drawable.wifi);
                       } else connectivity.setImageResource(R.drawable.no_wifi);
                   }
                   break;
                   case "اليورو" : {
                       currencyFlag.setImageResource(R.drawable.eur);

                       if (isActive()) {
                           List_eur = listenToDocument("eur");
                           connectivity.setImageResource(R.drawable.wifi);
                       } else connectivity.setImageResource(R.drawable.no_wifi);
                   }
                   break;
                   case "الريال السعودي" : {
                       currencyFlag.setImageResource(R.drawable.sar);
                       if (isActive()) {
                           List_sar = listenToDocument("sar");
                           connectivity.setImageResource(R.drawable.wifi);
                       } else connectivity.setImageResource(R.drawable.no_wifi);
                   }
                   break;
                   case "الدرهم الإماراتي" : {
                       currencyFlag.setImageResource(R.drawable.aed);
                       if (isActive()) {
                           List_aed = listenToDocument("aed");
                           connectivity.setImageResource(R.drawable.wifi);
                       } else connectivity.setImageResource(R.drawable.no_wifi);

                   }
                   break;
                   case "الدينار الكويتي" : {
                       currencyFlag.setImageResource(R.drawable.kwd);
                       if (isActive()) {
                           List_kwd = listenToDocument("kwd");
                           connectivity.setImageResource(R.drawable.wifi);
                       } else connectivity.setImageResource(R.drawable.no_wifi);
                   }
                   break;
                   case "الريال القطري" : {
                       currencyFlag.setImageResource(R.drawable.qar);
                       if (isActive()) {
                           List_qar = listenToDocument("qar");
                           connectivity.setImageResource(R.drawable.wifi);
                       } else connectivity.setImageResource(R.drawable.no_wifi);

                   }
               }
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });

        return v;
    }

    public List<PricesModel> listenToDocument (String path) {
        List<PricesModel> tempData = new ArrayList<>();
        db.collection(path)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error != null) {
                            System.out.println("Listen Failed.");
                        }

                        List<PricesModel> fbData = new ArrayList<>();
                        for (QueryDocumentSnapshot doc : value) {
                           doc.getData();
                           PricesModel pricesModel = doc.toObject(PricesModel.class);
                           fbData.add(pricesModel);
                        }
                        PricesAdapter pricesAdapter = new PricesAdapter(tempData);
                        tempData.clear();
                        tempData.addAll(fbData);
                        pricesAdapter.notifyDataSetChanged();
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerView.setAdapter(pricesAdapter);


                    }
                });
        return tempData;
    } // To fetch real-time data from the firestore.


    public boolean isActive () {
        if (!NetworkStatus.getInstance(getContext()).isOnline()) {
            Toast.makeText(getContext(), "برجاء التأكد من وجود اتصال إنترنت لتحميل أحدث الأسعار", Toast.LENGTH_SHORT).show();
            return false;
        }
        else return true;
    }  // To Check for internet Connectivity.

    private void showInterstitial() {
        if (interstitialAd.isLoaded()) {
            interstitialAd.show();
        }
    }

}