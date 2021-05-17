package com.itrex.dollarprice;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class CalcAdapterFav extends RecyclerView.Adapter<CalcAdapterFav.ViewHolder> {
    String[] keysArray;
    String[] valuesArray;
    float amount;
    int[] flags;
    TextView selectedName;
    ImageView selectedImage;
    RecyclerView recyclerViewAll;
    Switch favSwitch;
    EditText AmountText;
    Context context;
    String[] referenceValuesList;
    List<String> favKeys = new ArrayList<>();
    List<String> favValues = new ArrayList<>();
    List<String> FinalfavKeys = new ArrayList<>();
    List<String> FinalfavValues = new ArrayList<>();


    public CalcAdapterFav(String[] keysArray, String[] valuesArray, float amount, int[] flags, TextView selectedName, ImageView selectedImage, RecyclerView recyclerViewAll, Switch favSwitch, EditText AmountText, String[] referenceValuesList,Context context) {
        this.keysArray = keysArray;
        this.valuesArray = valuesArray;
        this.amount = amount;
        this.flags = flags;
        this.selectedName = selectedName;
        this.selectedImage = selectedImage;
        this.recyclerViewAll = recyclerViewAll;
        this.favSwitch = favSwitch;
        this.AmountText = AmountText;
        this.referenceValuesList = referenceValuesList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.calc_rows_all,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.name.setText(keysArray[position]);
        if (keysArray[position].equals("الروبل الروسي")){
            holder.flag.setImageResource(flags[0]);
        } else if (keysArray[position].equals("دولار ترينيدادي")) {
            holder.flag.setImageResource(flags[1]);
        } else if (keysArray[position].equals("روبية نيبالية")) {
            holder.flag.setImageResource(flags[2]);
        } else if (keysArray[position].equals("ريـال قطري")) {
            holder.flag.setImageResource(flags[3]);
        } else if (keysArray[position].equals("كورونا دانيمركية")) {
            holder.flag.setImageResource(flags[4]);
        } else if (keysArray[position].equals("بولا بوتسوانية")) {
            holder.flag.setImageResource(flags[5]);
        } else if (keysArray[position].equals("روبية سريلانكية")) {
            holder.flag.setImageResource(flags[6]);
        } else if (keysArray[position].equals("دولار بوروني")) {
            holder.flag.setImageResource(flags[7]);
        } else if (keysArray[position].equals("كورونا نرويجية")) {
            holder.flag.setImageResource(flags[8]);
        } else if (keysArray[position].equals("دولار تايواني جديد")) {
            holder.flag.setImageResource(flags[9]);
        } else if (keysArray[position].equals("زلوتي بولندي")) {
            holder.flag.setImageResource(flags[10]);
        } else if (keysArray[position].equals("جنيه استرليني")) {
            holder.flag.setImageResource(flags[11]);
        } else if (keysArray[position].equals("تينج كازاخستاني")) {
            holder.flag.setImageResource(flags[12]);
        } else if (keysArray[position].equals("روبية باكستانية")) {
            holder.flag.setImageResource(flags[13]);
        } else if (keysArray[position].equals("دولار سينغافوري")) {
            holder.flag.setImageResource(flags[14]);
        } else if (keysArray[position].equals("فورينت مجري")) {
            holder.flag.setImageResource(flags[15]);
        } else if (keysArray[position].equals("بيسو مكسيكي")) {
            holder.flag.setImageResource(flags[16]);
        } else if (keysArray[position].equals("دولار كندي")) {
            holder.flag.setImageResource(flags[17]);
        } else if (keysArray[position].equals("بوليفار فنزويلي")) {
            holder.flag.setImageResource(flags[18]);
        } else if (keysArray[position].equals("درهم إماراتي")) {
            holder.flag.setImageResource(flags[19]);
        } else if (keysArray[position].equals("دينار بحريني")) {
            holder.flag.setImageResource(flags[20]);
        } else if (keysArray[position].equals("دولار أسترالي")) {
            holder.flag.setImageResource(flags[21]);
        } else if (keysArray[position].equals("ريـال سعودي")) {
            holder.flag.setImageResource(flags[22]);
        } else if (keysArray[position].equals("كرونا سويدية")) {
            holder.flag.setImageResource(flags[23]);
        } else if (keysArray[position].equals("يوان صيني")) {
            holder.flag.setImageResource(flags[24]);
        } else if (keysArray[position].equals("ليف بلغاري")) {
            holder.flag.setImageResource(flags[25]);
        } else if (keysArray[position].equals("راند جنوب أفريقي")) {
            holder.flag.setImageResource(flags[26]);
        } else if (keysArray[position].equals("يون كوريا الجنوبية")) {
            holder.flag.setImageResource(flags[27]);
        } else if (keysArray[position].equals("دولار هونج كونج")) {
            holder.flag.setImageResource(flags[28]);
        } else if (keysArray[position].equals("دولار نيوزيلاندي")) {
            holder.flag.setImageResource(flags[29]);
        } else if (keysArray[position].equals("بيسو كولومبي")) {
            holder.flag.setImageResource(flags[30]);
        } else if (keysArray[position].equals("بيسو شيلي")) {
            holder.flag.setImageResource(flags[31]);
        } else if (keysArray[position].equals("كرونا آيسلندي")) {
            holder.flag.setImageResource(flags[32]);
        } else if (keysArray[position].equals("ريـال إيراني")) {
            holder.flag.setImageResource(flags[33]);
        } else if (keysArray[position].equals("ريـال عماني")) {
            holder.flag.setImageResource(flags[34]);
        } else if (keysArray[position].equals("جنيه مصري")) {
            holder.flag.setImageResource(flags[35]);
        } else if (keysArray[position].equals("رينجيت ماليزي")) {
            holder.flag.setImageResource(flags[36]);
        } else if (keysArray[position].equals("ريـال برازيلي")) {
            holder.flag.setImageResource(flags[37]);
        } else if (keysArray[position].equals("روبية إندونيسية")) {
            holder.flag.setImageResource(flags[38]);
        } else if (keysArray[position].equals("روبية موريشيوس")) {
            holder.flag.setImageResource(flags[39]);
        } else if (keysArray[position].equals("كونا كرواتية")) {
            holder.flag.setImageResource(flags[40]);
        } else if (keysArray[position].equals("روبية هندية")) {
            holder.flag.setImageResource(flags[41]);
        } else if (keysArray[position].equals("بيسو أرجنتيني")) {
            holder.flag.setImageResource(flags[42]);
        } else if (keysArray[position].equals("يورو")) {
            holder.flag.setImageResource(flags[43]);
        } else if (keysArray[position].equals("فرانك سويسري")) {
            holder.flag.setImageResource(flags[44]);
        } else if (keysArray[position].equals("بيسو فليبيني")) {
            holder.flag.setImageResource(flags[45]);
        } else if (keysArray[position].equals("دينار كويتي")) {
            holder.flag.setImageResource(flags[46]);
        } else if (keysArray[position].equals("كورونا تشيكية")) {
            holder.flag.setImageResource(flags[47]);
        } else if (keysArray[position].equals("بات تايلاندي")) {
            holder.flag.setImageResource(flags[48]);
        } else if (keysArray[position].equals("ليرة تركية")) {
            holder.flag.setImageResource(flags[49]);
        } else if (keysArray[position].equals("ين ياباني")) {
            holder.flag.setImageResource(flags[50]);
        } else if (keysArray[position].equals("دينار ليبي")) {
            holder.flag.setImageResource(flags[51]);
        } else if (keysArray[position].equals("نيو ليو روماني")) {
            holder.flag.setImageResource(flags[52]);
        }

        CharSequence text = selectedName.getText();
        if (text.equals("الروبل الروسي")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(String.valueOf(referenceValuesList[0])))));
        else if (text.equals("دولار ترينيدادي")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(String.valueOf(referenceValuesList[1])))));
        else if (text.equals("روبية نيبالية")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(String.valueOf(referenceValuesList[2])))));
        else if (text.equals("ريـال قطري")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(String.valueOf(referenceValuesList[3])))));
        else if (text.equals("كورونا دانيمركية")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(String.valueOf(referenceValuesList[4])))));
        else if (text.equals("بولا بوتسوانية")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(String.valueOf(referenceValuesList[5])))));
        else if (text.equals("روبية سريلانكية")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(String.valueOf(referenceValuesList[6])))));
        else if (text.equals("دولار بوروني")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(String.valueOf(referenceValuesList[7])))));
        else if (text.equals("كورونا نرويجية")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(String.valueOf(referenceValuesList[8])))));
        else if (text.equals("دولار تايواني جديد")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(String.valueOf(referenceValuesList[9])))));
        else if (text.equals("زلوتي بولندي")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(String.valueOf(referenceValuesList[10])))));
        else if (text.equals("جنيه استرليني")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(String.valueOf(referenceValuesList[11])))));
        else if (text.equals("تينج كازاخستاني")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(String.valueOf(referenceValuesList[12])))));
        else if (text.equals("روبية باكستانية")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(String.valueOf(referenceValuesList[13])))));
        else if (text.equals("دولار سينغافوري")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(String.valueOf(referenceValuesList[14])))));
        else if (text.equals("فورينت مجري")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(String.valueOf(referenceValuesList[15])))));
        else if (text.equals("بيسو مكسيكي")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(String.valueOf(referenceValuesList[16])))));
        else if (text.equals("دولار كندي")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(String.valueOf(referenceValuesList[17])))));
        else if (text.equals("بوليفار فنزويلي")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(String.valueOf(referenceValuesList[18])))));
        else if (text.equals("درهم إماراتي")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(String.valueOf(referenceValuesList[19])))));
        else if (text.equals("دينار بحريني")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(String.valueOf(referenceValuesList[20])))));
        else if (text.equals("دولار أسترالي")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(String.valueOf(referenceValuesList[21])))));
        else if (text.equals("ريـال سعودي")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(String.valueOf(referenceValuesList[22])))));
        else if (text.equals("كرونا سويدية")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(String.valueOf(referenceValuesList[23])))));
        else if (text.equals("يوان صيني")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(String.valueOf(referenceValuesList[24])))));
        else if (text.equals("ليف بلغاري")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(String.valueOf(referenceValuesList[25])))));
        else if (text.equals("راند جنوب أفريقي")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(String.valueOf(referenceValuesList[26])))));
        else if (text.equals("يون كوريا الجنوبية")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(String.valueOf(referenceValuesList[27])))));
        else if (text.equals("دولار هونج كونج")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(String.valueOf(referenceValuesList[28])))));
        else if (text.equals("دولار نيوزيلاندي")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(String.valueOf(referenceValuesList[29])))));
        else if (text.equals("بيسو كولومبي")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(String.valueOf(referenceValuesList[30])))));
        else if (text.equals("بيسو شيلي")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(String.valueOf(referenceValuesList[31])))));
        else if (text.equals("كرونا آيسلندي")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(String.valueOf(referenceValuesList[32])))));
        else if (text.equals("ريـال إيراني")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(String.valueOf(referenceValuesList[33])))));
        else if (text.equals("ريـال عماني")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(String.valueOf(referenceValuesList[34])))));
        else if (text.equals("جنيه مصري")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(String.valueOf(referenceValuesList[35])))));
        else if (text.equals("رينجيت ماليزي")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(String.valueOf(referenceValuesList[36])))));
        else if (text.equals("ريـال برازيلي")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(String.valueOf(referenceValuesList[37])))));
        else if (text.equals("روبية إندونيسية")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(String.valueOf(referenceValuesList[38])))));
        else if (text.equals("روبية موريشيوس")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(String.valueOf(referenceValuesList[39])))));
        else if (text.equals("كونا كرواتية")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(String.valueOf(referenceValuesList[40])))));
        else if (text.equals("روبية هندية")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(String.valueOf(referenceValuesList[41])))));
        else if (text.equals("بيسو أرجنتيني")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(String.valueOf(referenceValuesList[42])))));
        else if (text.equals("يورو")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(String.valueOf(referenceValuesList[43])))));
        else if (text.equals("فرانك سويسري")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(String.valueOf(referenceValuesList[44])))));
        else if (text.equals("بيسو فليبيني")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(String.valueOf(referenceValuesList[45])))));
        else if (text.equals("دينار كويتي")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(String.valueOf(referenceValuesList[46])))));
        else if (text.equals("كورونا تشيكية")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(String.valueOf(referenceValuesList[47])))));
        else if (text.equals("بات تايلاندي")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(String.valueOf(referenceValuesList[48])))));
        else if (text.equals("ليرة تركية")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(String.valueOf(referenceValuesList[49])))));
        else if (text.equals("ين ياباني")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(String.valueOf(referenceValuesList[50])))));
        else if (text.equals("دينار ليبي")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(String.valueOf(referenceValuesList[51])))));
        else if (text.equals("نيو ليو روماني")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(String.valueOf(referenceValuesList[52])))));

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new AlertDialog.Builder(holder.itemView.getContext())
                        .setTitle("تحذير!")
                        .setMessage("هل تريد حذف هذه العملة من المفضلة؟")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                favKeys = loadData(holder.itemView.getContext(),"favKeys");
                                favKeys.remove(position);
                                saveData(holder.itemView.getContext(),favKeys,"favKeys");
                                favValues = loadData(holder.itemView.getContext(),"favValues");
                                favValues.remove(position);
                                saveData(holder.itemView.getContext(),favValues,"favValues");
                                System.out.println(loadData(holder.itemView.getContext(),"favKeys") + "NEW LIST");
                                System.out.println(loadData(holder.itemView.getContext(),"favValues") + "NEW LIST");



                                CalcAdapterFav calcAdapterFav = new CalcAdapterFav(loadData(holder.itemView.getContext(),"favKeys").toArray(new String[0]),loadData(holder.itemView.getContext(),"favValues").toArray(new String[0]),amount,flags,selectedName,selectedImage,recyclerViewAll,favSwitch,AmountText,referenceValuesList,context);
                                calcAdapterFav.notifyDataSetChanged();
                                recyclerViewAll.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));
                                recyclerViewAll.setAdapter(calcAdapterFav);
                            }
                        })
                        .setNegativeButton(android.R.string.no,null)
                        .show();

                return true;
            }
        });
//        holder.fav.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (!buttonView.isChecked()) {
//                    favKeys = loadData(holder.itemView.getContext(),"favKeys");
//                    favKeys.remove(position);
//                    saveData(holder.itemView.getContext(),favKeys,"favKeys");
//                    favValues = loadData(holder.itemView.getContext(),"favValues");
//                    favValues.remove(position);
//                    saveData(holder.itemView.getContext(),favValues,"favValues");
//                    System.out.println(loadData(holder.itemView.getContext(),"favKeys") + "NEW LIST");
//                    System.out.println(loadData(holder.itemView.getContext(),"favValues") + "NEW LIST");
//
//
//
//                    CalcAdapterFav calcAdapterFav = new CalcAdapterFav(loadData(holder.itemView.getContext(),"favKeys").toArray(new String[0]),loadData(holder.itemView.getContext(),"favValues").toArray(new String[0]),amount,flags,selectedName,selectedImage,recyclerViewAll,favSwitch,AmountText,referenceValuesList,context);
//                    calcAdapterFav.notifyDataSetChanged();
//                    recyclerViewAll.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));
//                    recyclerViewAll.setAdapter(calcAdapterFav);
//                }
//            }
//        });


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    selectedName.setText(keysArray[position]);
                    if (keysArray[position].equals("الروبل الروسي")) {
                        selectedImage.setImageResource(flags[0]);
                    } else if (keysArray[position].equals("دولار ترينيدادي")) {
                        selectedImage.setImageResource(flags[1]);
                    } else if (keysArray[position].equals("روبية نيبالية")) {
                        selectedImage.setImageResource(flags[2]);
                    } else if (keysArray[position].equals("ريـال قطري")) {
                        selectedImage.setImageResource(flags[3]);
                    } else if (keysArray[position].equals("كورونا دانيمركية")) {
                        selectedImage.setImageResource(flags[4]);
                    } else if (keysArray[position].equals("بولا بوتسوانية")) {
                        selectedImage.setImageResource(flags[5]);
                    } else if (keysArray[position].equals("روبية سريلانكية")) {
                        selectedImage.setImageResource(flags[6]);
                    } else if (keysArray[position].equals("دولار بوروني")) {
                        selectedImage.setImageResource(flags[7]);
                    } else if (keysArray[position].equals("كورونا نرويجية")) {
                        selectedImage.setImageResource(flags[8]);
                    } else if (keysArray[position].equals("دولار تايواني جديد")) {
                        selectedImage.setImageResource(flags[9]);
                    } else if (keysArray[position].equals("زلوتي بولندي")) {
                        selectedImage.setImageResource(flags[10]);
                    } else if (keysArray[position].equals("جنيه استرليني")) {
                        selectedImage.setImageResource(flags[11]);
                    } else if (keysArray[position].equals("تينج كازاخستاني")) {
                        selectedImage.setImageResource(flags[12]);
                    } else if (keysArray[position].equals("روبية باكستانية")) {
                        selectedImage.setImageResource(flags[13]);
                    } else if (keysArray[position].equals("دولار سينغافوري")) {
                        selectedImage.setImageResource(flags[14]);
                    } else if (keysArray[position].equals("فورينت مجري")) {
                        selectedImage.setImageResource(flags[15]);
                    } else if (keysArray[position].equals("بيسو مكسيكي")) {
                        selectedImage.setImageResource(flags[16]);
                    } else if (keysArray[position].equals("دولار كندي")) {
                        selectedImage.setImageResource(flags[17]);
                    } else if (keysArray[position].equals("بوليفار فنزويلي")) {
                        selectedImage.setImageResource(flags[18]);
                    } else if (keysArray[position].equals("درهم إماراتي")) {
                        selectedImage.setImageResource(flags[19]);
                    } else if (keysArray[position].equals("دينار بحريني")) {
                        selectedImage.setImageResource(flags[20]);
                    } else if (keysArray[position].equals("دولار أسترالي")) {
                        selectedImage.setImageResource(flags[21]);
                    } else if (keysArray[position].equals("ريـال سعودي")) {
                        selectedImage.setImageResource(flags[22]);
                    } else if (keysArray[position].equals("كرونا سويدية")) {
                        selectedImage.setImageResource(flags[23]);
                    } else if (keysArray[position].equals("يوان صيني")) {
                        selectedImage.setImageResource(flags[24]);
                    } else if (keysArray[position].equals("ليف بلغاري")) {
                        selectedImage.setImageResource(flags[25]);
                    } else if (keysArray[position].equals("راند جنوب أفريقي")) {
                        selectedImage.setImageResource(flags[26]);
                    } else if (keysArray[position].equals("يون كوريا الجنوبية")) {
                        selectedImage.setImageResource(flags[27]);
                    } else if (keysArray[position].equals("دولار هونج كونج")) {
                        selectedImage.setImageResource(flags[28]);
                    } else if (keysArray[position].equals("دولار نيوزيلاندي")) {
                        selectedImage.setImageResource(flags[29]);
                    } else if (keysArray[position].equals("بيسو كولومبي")) {
                        selectedImage.setImageResource(flags[30]);
                    } else if (keysArray[position].equals("بيسو شيلي")) {
                        selectedImage.setImageResource(flags[31]);
                    } else if (keysArray[position].equals("كرونا آيسلندي")) {
                        selectedImage.setImageResource(flags[32]);
                    } else if (keysArray[position].equals("ريـال إيراني")) {
                        selectedImage.setImageResource(flags[33]);
                    } else if (keysArray[position].equals("ريـال عماني")) {
                        selectedImage.setImageResource(flags[34]);
                    } else if (keysArray[position].equals("جنيه مصري")) {
                        selectedImage.setImageResource(flags[35]);
                    } else if (keysArray[position].equals("رينجيت ماليزي")) {
                        selectedImage.setImageResource(flags[36]);
                    } else if (keysArray[position].equals("ريـال برازيلي")) {
                        selectedImage.setImageResource(flags[37]);
                    } else if (keysArray[position].equals("روبية إندونيسية")) {
                        selectedImage.setImageResource(flags[38]);
                    } else if (keysArray[position].equals("روبية موريشيوس")) {
                        selectedImage.setImageResource(flags[39]);
                    } else if (keysArray[position].equals("كونا كرواتية")) {
                        selectedImage.setImageResource(flags[40]);
                    } else if (keysArray[position].equals("روبية هندية")) {
                        selectedImage.setImageResource(flags[41]);
                    } else if (keysArray[position].equals("بيسو أرجنتيني")) {
                        selectedImage.setImageResource(flags[42]);
                    } else if (keysArray[position].equals("يورو")) {
                        selectedImage.setImageResource(flags[43]);
                    } else if (keysArray[position].equals("فرانك سويسري")) {
                        selectedImage.setImageResource(flags[44]);
                    } else if (keysArray[position].equals("بيسو فليبيني")) {
                        selectedImage.setImageResource(flags[45]);
                    } else if (keysArray[position].equals("دينار كويتي")) {
                        selectedImage.setImageResource(flags[46]);
                    } else if (keysArray[position].equals("كورونا تشيكية")) {
                        selectedImage.setImageResource(flags[47]);
                    } else if (keysArray[position].equals("بات تايلاندي")) {
                        selectedImage.setImageResource(flags[48]);
                    } else if (keysArray[position].equals("ليرة تركية")) {
                        selectedImage.setImageResource(flags[49]);
                    } else if (keysArray[position].equals("ين ياباني")) {
                        selectedImage.setImageResource(flags[50]);
                    } else if (keysArray[position].equals("دينار ليبي")) {
                        selectedImage.setImageResource(flags[51]);
                    } else if (keysArray[position].equals("نيو ليو روماني")) {
                        selectedImage.setImageResource(flags[52]);
                    }

                    CalcAdapterFav calcAdapterFav = new CalcAdapterFav(loadData(holder.itemView.getContext(), "favKeys").toArray(new String[0]), loadData(holder.itemView.getContext(), "favValues").toArray(new String[0]), amount, flags, selectedName, selectedImage, recyclerViewAll, favSwitch, AmountText, referenceValuesList,context);
                    calcAdapterFav.notifyDataSetChanged();
                    recyclerViewAll.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));
                    recyclerViewAll.setAdapter(calcAdapterFav);

            }
        });

    }

    @Override
    public int getItemCount() {
        return keysArray.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,value;
        ImageView flag;
        CheckBox fav;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.CalcNameAll);
            value = itemView.findViewById(R.id.CalcValueAll);
            flag = itemView.findViewById(R.id.CalcImageAll);
        }
    }

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

    public static List<String> loadData (Context context, String key) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String json = preferences.getString(key,null);
        ArrayList<String> values = new ArrayList<String>();
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
}
