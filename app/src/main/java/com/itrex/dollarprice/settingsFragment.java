package com.itrex.dollarprice;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.LoadAdError;
import com.google.firebase.messaging.FirebaseMessaging;
import com.onesignal.OneSignal;


public class settingsFragment extends Fragment {

    public settingsFragment() {
        // Required empty public constructor
    }

    InterstitialAd interstitialAd;
    Switch newsSwitch;
    Button rating;
    Button facebook;
    Button share;
    String FACEBOOK_URL = "https://m.facebook.com/Dollar.2.EGP/";
    String APP_URL = "https://play.google.com/store/apps/details?id=com.hwaya.dollarprice";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_settings, container, false);

        newsSwitch = v.findViewById(R.id.switch1);
        SharedPreferences mSharedPreferencesNews = getActivity().getSharedPreferences("news_state", Context.MODE_PRIVATE);
        newsSwitch.setChecked(mSharedPreferencesNews.getBoolean("news_state",true));

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

        newsSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!newsSwitch.isChecked()) {

                    SharedPreferences.Editor editor = getActivity().getSharedPreferences("news_state",Context.MODE_PRIVATE).edit();
                    editor.putBoolean("news_state",false);
                    editor.apply();
                    OneSignal.initWithContext(null);
                } else {

                    SharedPreferences.Editor editor = getActivity().getSharedPreferences("news_state",Context.MODE_PRIVATE).edit();
                    editor.putBoolean("news_state",true);
                    editor.apply();
                    OneSignal.initWithContext(getActivity());
                }
            }
        });

        // Setting up the Rating Operation.
        rating = v.findViewById(R.id.rateBTN);
        rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("market://details?id="+getActivity().getPackageName())));
                }
                catch (android.content.ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/stpre/apps/details?id="+getActivity().getPackageName())));
                }
            }
        });


        // Setting up the Facebook Operation.
        facebook = v.findViewById(R.id.facebookBTN);
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                String facebookURL = getFacebookPageURL(getActivity());
                facebookIntent.setData(Uri.parse(facebookURL));
                startActivity(facebookIntent);
            }
        });


        // Setting up the Share Activity.
        share = v.findViewById(R.id.shareBTN);
        share.setOnClickListener((view) -> {
            Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,APP_URL);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
        });


        return v;
    }

    public String getFacebookPageURL (Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.Egypt",0).versionCode;

            if (versionCode >= 3002850) {
                return "fb://facewebmodal/f?href="+FACEBOOK_URL;
            } else {
                return "fb://page/"+"Dollar.2.EGP/";
            }
        } catch (PackageManager.NameNotFoundException e) {
            return FACEBOOK_URL;
        }
    }

    private void showInterstitial() {
        if (interstitialAd.isLoaded()) {
            interstitialAd.show();
        }
    }
}