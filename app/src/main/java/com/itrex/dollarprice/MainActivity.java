package com.itrex.dollarprice;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.OnPaidEventListener;
import com.google.android.gms.ads.ResponseInfo;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.messaging.FirebaseMessaging;
import com.onesignal.OneSignal;

public class MainActivity extends AppCompatActivity {
    InterstitialAd interstitialAd;
    NetworkStatus status;
    private static final String ONESIGNAL_APP_ID = "23202c25-752f-4c98-8b8f-b36727e89e76";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences mSharedPreferencesNews = getSharedPreferences("news_state", Context.MODE_PRIVATE);





        SharedPreferences preferences = getSharedPreferences("preferences",0);

        SharedPreferences.Editor editor = preferences.edit();
        boolean firstRun = preferences.getBoolean("firstRun", true);
        if (firstRun) {
            new AlertDialog.Builder(this)
                    .setTitle("أهلا بك في سعر الدولار")
                    .setMessage("عزيزي المستخدم, يجب تفعيل خاصية الإنترنت لتتمكن من عرض أحدث الأسعار")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .setNegativeButton(android.R.string.no,null)
                    .setIcon(R.drawable.ic_launchicon)
                    .show();
            editor.putBoolean("firstRun", false);
            editor.apply();

            OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE,OneSignal.LOG_LEVEL.NONE);
            OneSignal.initWithContext(this);
            OneSignal.setAppId(ONESIGNAL_APP_ID);

        }

        status = new NetworkStatus();

        if (!isActive()) {

            new AlertDialog.Builder(this)
                    .setTitle("تحذير!")
                    .setMessage("هل ترغب في تفعيل الإنترنت لعرض أحدث الأسعار؟")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                                Intent panelIntent = new
                                        Intent(Settings.Panel.ACTION_INTERNET_CONNECTIVITY);
                                startActivityForResult(panelIntent, 0);
                            } else {
                                // for previous android version
                                WifiManager wifiManager = (WifiManager)
                                        getBaseContext().getApplicationContext().getSystemService(WIFI_SERVICE);
                                wifiManager.setWifiEnabled(true);
                            }

                        }
                    })
                    .setNegativeButton(android.R.string.no,null)
                    .show();

        }


        // Setting up the Navigation between fragments and the bottom navigation bar.
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        NavController navController = Navigation.findNavController(this,R.id.fragment);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.pricesFragment,R.id.calculatorFragment,R.id.goldFragment,R.id.newsFragment,R.id.settingsFragment).build();
        NavigationUI.setupActionBarWithNavController(this,navController,appBarConfiguration);
        NavigationUI.setupWithNavController(bottomNavigationView,navController);

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                if (destination.equals(R.id.pricesFragment)) {
                    interstitialAd = new InterstitialAd(MainActivity.this);
                    interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
                    AdRequest adRequest = new AdRequest.Builder().build();
                    interstitialAd.loadAd(adRequest);
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
                } else if (destination.equals(R.id.calculatorFragment)) {
                    interstitialAd = new InterstitialAd(MainActivity.this);
                    interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
                    AdRequest adRequest = new AdRequest.Builder().build();
                    interstitialAd.loadAd(adRequest);
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
                } else if (destination.equals(R.id.goldFragment)) {
                    interstitialAd = new InterstitialAd(MainActivity.this);
                    interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
                    AdRequest adRequest = new AdRequest.Builder().build();
                    interstitialAd.loadAd(adRequest);
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
                } else if (destination.equals(R.id.newsFragment)) {
                    interstitialAd = new InterstitialAd(MainActivity.this);
                    interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
                    AdRequest adRequest = new AdRequest.Builder().build();
                    interstitialAd.loadAd(adRequest);
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
                } else if (destination.equals(R.id.settingsFragment)) {
                    interstitialAd = new InterstitialAd(MainActivity.this);
                    interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
                    AdRequest adRequest = new AdRequest.Builder().build();
                    interstitialAd.loadAd(adRequest);
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
                }
            }
        });


        ActionBar actionBar = getSupportActionBar(); // Hides the appbar at the top.
        actionBar.hide();
    }
    public boolean isActive () {
        if (!NetworkStatus.getInstance(this).isOnline()) {
            Toast.makeText(this, "برجاء التأكد من وجود اتصال إنترنت لتحميل أحدث الأسعار", Toast.LENGTH_SHORT).show();
            return false;
        }
        else return true;
    }  // To Check for internet Connectivity.

    private void showInterstitial() {
        if (interstitialAd.isLoaded()) {
            interstitialAd.show();
        }
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}