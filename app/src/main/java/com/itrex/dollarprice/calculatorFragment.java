package com.itrex.dollarprice;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class calculatorFragment extends Fragment {

    public calculatorFragment() {
        // Required empty public constructor
    }
    InterstitialAd interstitialAd;
    AdView adView;
    EditText AmountText;
    ImageView selectedImage;
    TextView currencyState;
    CalcAdapterAll calcAdapterAll;
    CalcAdapterFav calcAdapterFav;
    TextView selectedName;
    RecyclerView recyclerViewAll;
    ImageView connectivity;
    Switch favSwitch;
    String[] keysArabic;
    float newAmount;
    String[] FinalList;
    List<String> list = new ArrayList<>();
    List<String> favKeys = new ArrayList<>();
    List<String> favValues = new ArrayList<>();
    List<String> FinalfavKeys = new ArrayList<>();
    List<String> FinalfavValues = new ArrayList<>();
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    FragmentTransaction ft;




    int[] flags = {R.drawable.russia,R.drawable.trinidad_and_tobago,R.drawable.nepal,R.drawable.qatar,R.drawable.denmark,R.drawable.botswana,R.drawable.sri_lanka,R.drawable.brunei,R.drawable.norway,R.drawable.taiwan,R.drawable.poland,R.drawable.great_britain,R.drawable.kazakhstan,R.drawable.pakistan,R.drawable.singapore,R.drawable.hungary,R.drawable.mexico,R.drawable.canada,R.drawable.venezuela,R.drawable.united_arab_emirates,R.drawable.bahrain,R.drawable.australia,R.drawable.saudi_arabia,R.drawable.sweden,R.drawable.china,R.drawable.bulgaria,R.drawable.south_africa,R.drawable.south_korea,R.drawable.hong_kong,R.drawable.new_zealand,R.drawable.colombia,R.drawable.chile,R.drawable.iceland,R.drawable.iran,R.drawable.oman,R.drawable.egypt,R.drawable.malaysia,R.drawable.brazil,R.drawable.indonesia,R.drawable.mauritania,R.drawable.croatia,R.drawable.india,R.drawable.argentina,R.drawable.euro,R.drawable.switzerland,R.drawable.philippines,R.drawable.kuwait,R.drawable.czech_republic,R.drawable.thailand,R.drawable.turkey,R.drawable.japan,R.drawable.libya,R.drawable.romania};




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_calculator, container, false);

        AmountText = v.findViewById(R.id.amountText);
        recyclerViewAll = v.findViewById(R.id.recyclerView_All);
        selectedImage = v.findViewById(R.id.selectedImage);
        selectedName = v.findViewById(R.id.selectedName);
        keysArabic = getResources().getStringArray(R.array.currenciesArabic);
        connectivity = v.findViewById(R.id.wifi);
        favSwitch = v.findViewById(R.id.FavSwitch);
        currencyState = v.findViewById(R.id.currency_state);

        MobileAds.initialize(getContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

            }
        });
        adView = v.findViewById(R.id.adView_calculator);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

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


        list = loadData(getContext(),"values");
        FinalList = list.toArray(new String[0]); // Permanent list of values loaded once the application starts.

        favKeys = loadData(getContext(),"favKeys"); // names of saved currencies.
        favValues = loadData(getContext(),"favValues"); // values of saved currencies.
        preferences =PreferenceManager.getDefaultSharedPreferences(getContext());
        editor = preferences.edit();

        boolean firstRun = preferences.getBoolean("firstRun",true);

        ft = getFragmentManager().beginTransaction();

        if (isUp()) {
            connectivity.setImageResource(R.drawable.wifi); // To change the wifi state icon.
            if (firstRun) {
                DownloadTaskOnline task = new DownloadTaskOnline();
                task.execute("https://currency.hwayadesigns.com/ap.txt?"); // Execution of receiving API data and transform it to lists.

                // Responsible for real time update of calculations.
                AmountText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (TextUtils.isEmpty(AmountText.getText())) {
                            recyclerViewAll.setAdapter(null);
                            newAmount = (float) 0;
                            CalcAdapterAll calcAdapter = new CalcAdapterAll(keysArabic,loadData(getContext(),"values").toArray(new String[0]),newAmount,selectedName,selectedImage,getContext(),recyclerViewAll,favSwitch,AmountText);
                            calcAdapter.notifyDataSetChanged();
                            recyclerViewAll.setLayoutManager(new LinearLayoutManager(getContext()));
                            recyclerViewAll.setAdapter(calcAdapter);
                            System.out.println("NULL");
                        } else {
                            recyclerViewAll.setAdapter(null);
                            String newAmountText = AmountText.getText().toString();
                            newAmount = Float.parseFloat(newAmountText);
                            CalcAdapterAll calcAdapter = new CalcAdapterAll(keysArabic,loadData(getContext(),"values").toArray(new String[0]),newAmount,selectedName,selectedImage,getContext(),recyclerViewAll,favSwitch,AmountText);
                            calcAdapter.notifyDataSetChanged();
                            recyclerViewAll.setLayoutManager(new LinearLayoutManager(getContext()));
                            recyclerViewAll.setAdapter(calcAdapter);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });


                // Responsible for switching between all currencies and favorite currencies.
                favSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (buttonView.isChecked()) {

                            System.out.println(loadData(getContext(),"favKeys"));

                            favKeys.removeAll(loadData(getContext(),"favKeys"));
                            favValues.removeAll(loadData(getContext(),"favValues"));
                            FinalfavValues = favValues;
                            FinalfavKeys = favKeys;
                            FinalfavKeys.addAll(loadData(getContext(),"favKeys"));
                            FinalfavValues.addAll(loadData(getContext(),"favValues"));
                            saveData(getContext(),FinalfavKeys,"favKeys");
                            saveData(getContext(),FinalfavValues,"favValues");

                            CalcAdapterFav calcAdapterFav = new CalcAdapterFav(FinalfavKeys.toArray(new String[0]),FinalfavValues.toArray(new String[0]),newAmount,flags,selectedName,selectedImage,recyclerViewAll,favSwitch,AmountText,FinalList,getContext());
                            calcAdapterFav.notifyDataSetChanged();
                            recyclerViewAll.setLayoutManager(new LinearLayoutManager(getContext()));
                            recyclerViewAll.setAdapter(calcAdapterFav);


                            currencyState.setText("العملات المفضلة");

                            AmountText.addTextChangedListener(new TextWatcher() {
                                @Override
                                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                }

                                @Override
                                public void onTextChanged(CharSequence s, int start, int before, int count) {
                                    if (TextUtils.isEmpty(AmountText.getText())) {
                                        recyclerViewAll.setAdapter(null);
                                        newAmount = (float) 0;
                                        CalcAdapterFav calcAdapterFav = new CalcAdapterFav(loadData(getContext(),"favKeys").toArray(new String[0]), loadData(getContext(),"favValues").toArray(new String[0]), newAmount, flags, selectedName, selectedImage, recyclerViewAll, favSwitch, AmountText, FinalList,getContext());
                                        calcAdapterFav.notifyDataSetChanged();
                                        recyclerViewAll.setLayoutManager(new LinearLayoutManager(getContext()));
                                        recyclerViewAll.setAdapter(calcAdapterFav);
                                    } else {
                                        recyclerViewAll.setAdapter(null);
                                        String newAmountText = AmountText.getText().toString();
                                        newAmount = Float.parseFloat(newAmountText);
                                        CalcAdapterFav calcAdapterFav = new CalcAdapterFav(loadData(getContext(),"favKeys").toArray(new String[0]),loadData(getContext(),"favValues").toArray(new String[0]), newAmount, flags, selectedName, selectedImage, recyclerViewAll, favSwitch, AmountText, FinalList,getContext());
                                        calcAdapterFav.notifyDataSetChanged();
                                        recyclerViewAll.setLayoutManager(new LinearLayoutManager(getContext()));
                                        recyclerViewAll.setAdapter(calcAdapterFav);
                                    }

                                }

                                @Override
                                public void afterTextChanged(Editable s) {

                                }
                            });
                        } else if (!buttonView.isChecked()){

                            FinalfavKeys.clear();
                            FinalfavValues.clear();
                            FinalfavKeys = loadData(getContext(),"favKeys");
                            FinalfavValues = loadData(getContext(),"favValues");
                            favKeys = FinalfavKeys;
                            favValues = FinalfavValues;
                            favValues = loadData(getContext(),"favValues");
                            favKeys = loadData(getContext(),"favKeys");
                            saveData(getContext(),favKeys,"favKeys");
                            saveData(getContext(),favValues,"favValues");

                            System.out.println(loadData(getContext(),"favKeys"));

                            currencyState.setText("كل العملات");
                            FinalList = loadData(getContext(),"values").toArray(new String[0]);
                            CalcAdapterAll calcAdapterAll = new CalcAdapterAll(keysArabic,loadData(getContext(),"values").toArray(new String[0]),newAmount,selectedName,selectedImage,getContext(),recyclerViewAll,favSwitch,AmountText);
                            calcAdapterAll.notifyDataSetChanged();
                            recyclerViewAll.setLayoutManager(new LinearLayoutManager(getContext()));
                            recyclerViewAll.setAdapter(calcAdapterAll);

                            AmountText.addTextChangedListener(new TextWatcher() {
                                @Override
                                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                }

                                @Override
                                public void onTextChanged(CharSequence s, int start, int before, int count) {
                                    if (TextUtils.isEmpty(AmountText.getText())) {
                                        recyclerViewAll.setAdapter(null);
                                        newAmount = (float) 0;
                                        CalcAdapterAll calcAdapter = new CalcAdapterAll(keysArabic,loadData(getContext(),"values").toArray(new String[0]),newAmount,selectedName,selectedImage,getContext(),recyclerViewAll,favSwitch,AmountText);
                                        calcAdapter.notifyDataSetChanged();
                                        recyclerViewAll.setLayoutManager(new LinearLayoutManager(getContext()));
                                        recyclerViewAll.setAdapter(calcAdapter);
                                        System.out.println("NULL");
                                    } else {
                                        recyclerViewAll.setAdapter(null);
                                        String newAmountText = AmountText.getText().toString();
                                        newAmount = Float.parseFloat(newAmountText);
                                        CalcAdapterAll calcAdapter = new CalcAdapterAll(keysArabic,loadData(getContext(),"values").toArray(new String[0]),newAmount,selectedName,selectedImage,getContext(),recyclerViewAll,favSwitch,AmountText);
                                        calcAdapter.notifyDataSetChanged();
                                        recyclerViewAll.setLayoutManager(new LinearLayoutManager(getContext()));
                                        recyclerViewAll.setAdapter(calcAdapter);
                                    }
                                }

                                @Override
                                public void afterTextChanged(Editable s) {

                                }
                            });
                        }
                    }
                });
            } else if (!firstRun) {

                CalcAdapterAll calcAdapter = new CalcAdapterAll(keysArabic,loadData(getContext(),"values").toArray(new String[0]),newAmount,selectedName,selectedImage,getContext(),recyclerViewAll,favSwitch,AmountText);
                calcAdapter.notifyDataSetChanged();
                recyclerViewAll.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerViewAll.setAdapter(calcAdapter);

                // Responsible for real time update of calculations.
                AmountText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (TextUtils.isEmpty(AmountText.getText())) {
                            recyclerViewAll.setAdapter(null);
                            newAmount = (float) 0;
                            CalcAdapterAll calcAdapter = new CalcAdapterAll(keysArabic,loadData(getContext(),"values").toArray(new String[0]),newAmount,selectedName,selectedImage,getContext(),recyclerViewAll,favSwitch,AmountText);
                            calcAdapter.notifyDataSetChanged();
                            recyclerViewAll.setLayoutManager(new LinearLayoutManager(getContext()));
                            recyclerViewAll.setAdapter(calcAdapter);
                            System.out.println("NULL");
                        } else {
                            recyclerViewAll.setAdapter(null);
                            String newAmountText = AmountText.getText().toString();
                            newAmount = Float.parseFloat(newAmountText);
                            CalcAdapterAll calcAdapter = new CalcAdapterAll(keysArabic,loadData(getContext(),"values").toArray(new String[0]),newAmount,selectedName,selectedImage,getContext(),recyclerViewAll,favSwitch,AmountText);
                            calcAdapter.notifyDataSetChanged();
                            recyclerViewAll.setLayoutManager(new LinearLayoutManager(getContext()));
                            recyclerViewAll.setAdapter(calcAdapter);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });


                // Responsible for switching between all currencies and favorite currencies.
                favSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (buttonView.isChecked()) {

                            System.out.println(loadData(getContext(),"favKeys"));

                            favKeys.removeAll(loadData(getContext(),"favKeys"));
                            favValues.removeAll(loadData(getContext(),"favValues"));
                            FinalfavValues = favValues;
                            FinalfavKeys = favKeys;
                            FinalfavKeys.addAll(loadData(getContext(),"favKeys"));
                            FinalfavValues.addAll(loadData(getContext(),"favValues"));
                            saveData(getContext(),FinalfavKeys,"favKeys");
                            saveData(getContext(),FinalfavValues,"favValues");

                            CalcAdapterFav calcAdapterFav = new CalcAdapterFav(FinalfavKeys.toArray(new String[0]),FinalfavValues.toArray(new String[0]),newAmount,flags,selectedName,selectedImage,recyclerViewAll,favSwitch,AmountText,FinalList,getContext());
                            calcAdapterFav.notifyDataSetChanged();
                            recyclerViewAll.setLayoutManager(new LinearLayoutManager(getContext()));
                            recyclerViewAll.setAdapter(calcAdapterFav);


                            currencyState.setText("العملات المفضلة");

                            AmountText.addTextChangedListener(new TextWatcher() {
                                @Override
                                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                }

                                @Override
                                public void onTextChanged(CharSequence s, int start, int before, int count) {
                                    if (TextUtils.isEmpty(AmountText.getText())) {
                                        recyclerViewAll.setAdapter(null);
                                        newAmount = (float) 0;
                                        CalcAdapterFav calcAdapterFav = new CalcAdapterFav(loadData(getContext(),"favKeys").toArray(new String[0]), loadData(getContext(),"favValues").toArray(new String[0]), newAmount, flags, selectedName, selectedImage, recyclerViewAll, favSwitch, AmountText, FinalList,getContext());
                                        calcAdapterFav.notifyDataSetChanged();
                                        recyclerViewAll.setLayoutManager(new LinearLayoutManager(getContext()));
                                        recyclerViewAll.setAdapter(calcAdapterFav);
                                    } else {
                                        recyclerViewAll.setAdapter(null);
                                        String newAmountText = AmountText.getText().toString();
                                        newAmount = Float.parseFloat(newAmountText);
                                        CalcAdapterFav calcAdapterFav = new CalcAdapterFav(loadData(getContext(),"favKeys").toArray(new String[0]),loadData(getContext(),"favValues").toArray(new String[0]), newAmount, flags, selectedName, selectedImage, recyclerViewAll, favSwitch, AmountText, FinalList,getContext());
                                        calcAdapterFav.notifyDataSetChanged();
                                        recyclerViewAll.setLayoutManager(new LinearLayoutManager(getContext()));
                                        recyclerViewAll.setAdapter(calcAdapterFav);
                                    }

                                }

                                @Override
                                public void afterTextChanged(Editable s) {

                                }
                            });
                        } else if (!buttonView.isChecked()){

                            FinalfavKeys.clear();
                            FinalfavValues.clear();
                            FinalfavKeys = loadData(getContext(),"favKeys");
                            FinalfavValues = loadData(getContext(),"favValues");
                            favKeys = FinalfavKeys;
                            favValues = FinalfavValues;
                            favValues = loadData(getContext(),"favValues");
                            favKeys = loadData(getContext(),"favKeys");
                            saveData(getContext(),favKeys,"favKeys");
                            saveData(getContext(),favValues,"favValues");

                            System.out.println(loadData(getContext(),"favKeys"));

                            currencyState.setText("كل العملات");
                            FinalList = loadData(getContext(),"values").toArray(new String[0]);
                            CalcAdapterAll calcAdapterAll = new CalcAdapterAll(keysArabic,loadData(getContext(),"values").toArray(new String[0]),newAmount,selectedName,selectedImage,getContext(),recyclerViewAll,favSwitch,AmountText);
                            calcAdapterAll.notifyDataSetChanged();
                            recyclerViewAll.setLayoutManager(new LinearLayoutManager(getContext()));
                            recyclerViewAll.setAdapter(calcAdapterAll);

                            AmountText.addTextChangedListener(new TextWatcher() {
                                @Override
                                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                }

                                @Override
                                public void onTextChanged(CharSequence s, int start, int before, int count) {
                                    if (TextUtils.isEmpty(AmountText.getText())) {
                                        recyclerViewAll.setAdapter(null);
                                        newAmount = (float) 0;
                                        CalcAdapterAll calcAdapter = new CalcAdapterAll(keysArabic,loadData(getContext(),"values").toArray(new String[0]),newAmount,selectedName,selectedImage,getContext(),recyclerViewAll,favSwitch,AmountText);
                                        calcAdapter.notifyDataSetChanged();
                                        recyclerViewAll.setLayoutManager(new LinearLayoutManager(getContext()));
                                        recyclerViewAll.setAdapter(calcAdapter);
                                        System.out.println("NULL");
                                    } else {
                                        recyclerViewAll.setAdapter(null);
                                        String newAmountText = AmountText.getText().toString();
                                        newAmount = Float.parseFloat(newAmountText);
                                        CalcAdapterAll calcAdapter = new CalcAdapterAll(keysArabic,loadData(getContext(),"values").toArray(new String[0]),newAmount,selectedName,selectedImage,getContext(),recyclerViewAll,favSwitch,AmountText);
                                        calcAdapter.notifyDataSetChanged();
                                        recyclerViewAll.setLayoutManager(new LinearLayoutManager(getContext()));
                                        recyclerViewAll.setAdapter(calcAdapter);
                                    }
                                }

                                @Override
                                public void afterTextChanged(Editable s) {

                                }
                            });
                        }
                    }
                });
            }





        }
        else { // Same process as before but with no connection, therefore all data shown is from the cache memory.
            Toast.makeText(getContext(), "برجاء التأكد من وجود إتصال بالإنترنت", Toast.LENGTH_SHORT).show();
            connectivity.setImageResource(R.drawable.no_wifi);

            CalcAdapterAll calcAdapterAll = new CalcAdapterAll(keysArabic,FinalList,newAmount,selectedName,selectedImage,getContext(),recyclerViewAll,favSwitch,AmountText);
            calcAdapterAll.notifyDataSetChanged();
            recyclerViewAll.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerViewAll.setAdapter(calcAdapterAll);

            AmountText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (TextUtils.isEmpty(AmountText.getText())) {
                        recyclerViewAll.setAdapter(null);
                        newAmount = (float) 0;
                        CalcAdapterAll calcAdapter = new CalcAdapterAll(keysArabic,FinalList,newAmount,selectedName,selectedImage,getContext(),recyclerViewAll,favSwitch,AmountText);
                        calcAdapter.notifyDataSetChanged();
                        recyclerViewAll.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerViewAll.setAdapter(calcAdapter);
                        System.out.println("NULL");
                    } else {
                        recyclerViewAll.setAdapter(null);
                        String newAmountText = AmountText.getText().toString();
                        newAmount = Float.parseFloat(newAmountText);
                        CalcAdapterAll calcAdapter = new CalcAdapterAll(keysArabic,FinalList,newAmount,selectedName,selectedImage,getContext(),recyclerViewAll,favSwitch,AmountText);
                        calcAdapter.notifyDataSetChanged();
                        recyclerViewAll.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerViewAll.setAdapter(calcAdapter);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });


            favSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (buttonView.isChecked()) {

                        favKeys.addAll(loadData(getContext(),"favKeys"));
                        favValues.addAll(loadData(getContext(),"favValues"));
                        favKeys.removeAll(loadData(getContext(),"favKeys"));
                        favValues.removeAll(loadData(getContext(),"favValues"));
                        FinalfavValues = favValues;
                        FinalfavKeys = favKeys;
                        FinalfavKeys.addAll(loadData(getContext(),"favKeys"));
                        FinalfavValues.addAll(loadData(getContext(),"favValues"));
                        saveData(getContext(),FinalfavKeys,"favKeys");
                        saveData(getContext(),FinalfavValues,"favValues");

                        currencyState.setText("العملات المفضلة");


                        CalcAdapterFav calcAdapterFav = new CalcAdapterFav(loadData(getContext(),"favKeys").toArray(new String[0]),loadData(getContext(),"favValues").toArray(new String[0]),newAmount,flags,selectedName,selectedImage,recyclerViewAll,favSwitch,AmountText,FinalList,getContext());
                        calcAdapterFav.notifyDataSetChanged();
                        recyclerViewAll.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerViewAll.setAdapter(calcAdapterFav);
                        AmountText.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                if (TextUtils.isEmpty(AmountText.getText())) {
                                    recyclerViewAll.setAdapter(null);
                                    newAmount = (float) 0;
                                    CalcAdapterFav calcAdapterFav = new CalcAdapterFav(loadData(getContext(),"favKeys").toArray(new String[0]),loadData(getContext(),"favValues").toArray(new String[0]), newAmount, flags, selectedName, selectedImage, recyclerViewAll, favSwitch, AmountText, FinalList,getContext());
                                    calcAdapterFav.notifyDataSetChanged();
                                    recyclerViewAll.setLayoutManager(new LinearLayoutManager(getContext()));
                                    recyclerViewAll.setAdapter(calcAdapterFav);
                                } else {
                                    recyclerViewAll.setAdapter(null);
                                    String newAmountText = AmountText.getText().toString();
                                    newAmount = Float.parseFloat(newAmountText);
                                    CalcAdapterFav calcAdapterFav = new CalcAdapterFav(loadData(getContext(),"favKeys").toArray(new String[0]),loadData(getContext(),"favValues").toArray(new String[0]), newAmount, flags, selectedName, selectedImage, recyclerViewAll, favSwitch, AmountText, FinalList,getContext());
                                    calcAdapterFav.notifyDataSetChanged();
                                    recyclerViewAll.setLayoutManager(new LinearLayoutManager(getContext()));
                                    recyclerViewAll.setAdapter(calcAdapterFav);
                                }

                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                            }
                        });
                    } else {
                        FinalfavKeys = loadData(getContext(),"favKeys");
                        FinalfavValues = loadData(getContext(),"favValues");
                        favKeys = FinalfavKeys;
                        favValues = FinalfavValues;
                        favValues = loadData(getContext(),"favValues");
                        favKeys = loadData(getContext(),"favKeys");
                        saveData(getContext(),favKeys,"favKeys");
                        saveData(getContext(),favValues,"favValues");

                        currencyState.setText("كل العملات");


                        FinalList = loadData(getContext(),"values").toArray(new String[0]);
                        CalcAdapterAll calcAdapterAll = new CalcAdapterAll(keysArabic,FinalList,newAmount,selectedName,selectedImage,getContext(),recyclerViewAll,favSwitch,AmountText);
                        calcAdapterAll.notifyDataSetChanged();
                        recyclerViewAll.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerViewAll.setAdapter(calcAdapterAll);

                        AmountText.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                if (TextUtils.isEmpty(AmountText.getText())) {
                                    recyclerViewAll.setAdapter(null);
                                    newAmount = (float) 0;
                                    CalcAdapterAll calcAdapter = new CalcAdapterAll(keysArabic,FinalList,newAmount,selectedName,selectedImage,getContext(),recyclerViewAll,favSwitch,AmountText);
                                    calcAdapter.notifyDataSetChanged();
                                    recyclerViewAll.setLayoutManager(new LinearLayoutManager(getContext()));
                                    recyclerViewAll.setAdapter(calcAdapter);
                                    System.out.println("NULL");
                                } else {
                                    recyclerViewAll.setAdapter(null);
                                    String newAmountText = AmountText.getText().toString();
                                    newAmount = Float.parseFloat(newAmountText);
                                    CalcAdapterAll calcAdapter = new CalcAdapterAll(keysArabic,FinalList,newAmount,selectedName,selectedImage,getContext(),recyclerViewAll,favSwitch,AmountText);
                                    calcAdapter.notifyDataSetChanged();
                                    recyclerViewAll.setLayoutManager(new LinearLayoutManager(getContext()));
                                    recyclerViewAll.setAdapter(calcAdapter);
                                }
                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                            }
                        });
                    }
                }
            });
        }




        return v;
    }


    // Class to obtain data and inflate the Recycler view
    public class DownloadTaskOnline extends AsyncTask<String,Void,String>{
        String[] keysArray;
        String[] valuesArray;
        CalcAdapterAll calcAdapterAll;
        String[] haha;
        private ProgressDialog progressDialog;

        String Amount_text = calculatorFragment.this.AmountText.getText().toString();
        float amount = (float)0;

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            if (isActive()) {
                try {
                    url = new URL(urls[0]);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    InputStream in = urlConnection.getInputStream();
                    InputStreamReader reader = new InputStreamReader(in);
                    int data = reader.read();

                    while (data != -1) {
                        char current = (char) data;
                        result += current;
                        data = reader.read();
                    }

                    System.out.println(result+"kkkkkkkkkkkkkkkkkkkkkk");
                    return result;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(getActivity(),ProgressDialog.THEME_HOLO_LIGHT);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setTitle("Processing...");
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
                System.out.println(result + "kkkk");
                try {
                    JSONObject object = new JSONObject(result);
                    Iterator keystoCopyIterator = object.keys();
                    List<String> keysList = new ArrayList<>();
                    while (keystoCopyIterator.hasNext()) {
                        String key = (String) keystoCopyIterator.next();
                        keysList.add(key);
                    }
                    keysArray = keysList.toArray(new String[keysList.size()]);
                    System.out.println(Arrays.toString(keysArray));

                    valuesArray = new String[keysArray.length];
                    for (int i = 0; i < keysArray.length; i++) {
                        valuesArray[i] = (String) object.get(keysArray[i]);
                    }

                }  catch (JSONException e) {
                    e.printStackTrace();
                }


            List<String> list = new ArrayList<>(Arrays.asList(valuesArray));

                saveData(getContext(),list,"values");

            list = loadData(getContext(),"values");
            haha = list.toArray(new String[0]);


                System.out.println(Arrays.toString(haha));


            System.out.println(Arrays.toString(valuesArray));
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            calcAdapterAll = new CalcAdapterAll(calculatorFragment.this.keysArabic,loadData(getContext(),"values").toArray(new String[0]),amount,calculatorFragment.this.selectedName,calculatorFragment.this.selectedImage,getContext(),calculatorFragment.this.recyclerViewAll,calculatorFragment.this.favSwitch,calculatorFragment.this.AmountText);
            calculatorFragment.this.recyclerViewAll.setLayoutManager(new LinearLayoutManager(getContext()));
            calculatorFragment.this.recyclerViewAll.setAdapter(calcAdapterAll);
            editor.putBoolean("firstRun",false);
            editor.apply();



        }
    }

    // to save data permanently into SharedPreferences.
    public static void saveData (Context context,List<String>values,String key) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        JSONArray a = new JSONArray();
        for (int i=0;i<values.size();i++) {
            a.put(values.get(i));
        }
        if (!values.isEmpty()) {
            editor.putString(key,a.toString());
        } else {
            editor.putString(key,null);
        }
        editor.apply();

    }

    // to load permanent data from SharedPreferences.
    public static List<String> loadData (Context context,String key) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String json = preferences.getString(key,null);
       List<String> values = new ArrayList<>();
        if (json!=null) {
            try{
                JSONArray a = new JSONArray(json);
                for (int i=0;i<a.length();i++) {
                    String value = a.optString(i);
                    values.add(value);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return values;
    }

    // To Check for internet Connectivity.
    public boolean isActive () {
        if (!NetworkStatus.getInstance(getContext()).isOnline()) {
            Toast.makeText(getContext(), "برجاء التأكد من وجود اتصال إنترنت لتحميل أحدث الأسعار", Toast.LENGTH_SHORT).show();
            return false;
        }
        else return true;
    }

    public boolean isUp() {
        ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    private void showInterstitial() {
        if (interstitialAd.isLoaded()) {
            interstitialAd.show();
        }
    }

}