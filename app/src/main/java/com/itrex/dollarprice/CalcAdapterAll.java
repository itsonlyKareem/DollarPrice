package com.itrex.dollarprice;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CalcAdapterAll extends RecyclerView.Adapter<CalcAdapterAll.ViewHolder> {
    String[] keysArray;
    String[] valuesArray;
    float amount;
    int[] flags = {R.drawable.russia,R.drawable.trinidad_and_tobago,R.drawable.nepal,R.drawable.qatar,R.drawable.denmark,R.drawable.botswana,R.drawable.sri_lanka,R.drawable.brunei,R.drawable.norway,R.drawable.taiwan,R.drawable.poland,R.drawable.great_britain,R.drawable.kazakhstan,R.drawable.pakistan,R.drawable.singapore,R.drawable.hungary,R.drawable.mexico,R.drawable.canada,R.drawable.venezuela,R.drawable.united_arab_emirates,R.drawable.bahrain,R.drawable.australia,R.drawable.saudi_arabia,R.drawable.sweden,R.drawable.china,R.drawable.bulgaria,R.drawable.south_africa,R.drawable.south_korea,R.drawable.hong_kong,R.drawable.new_zealand,R.drawable.colombia,R.drawable.chile,R.drawable.iceland,R.drawable.iran,R.drawable.oman,R.drawable.egypt,R.drawable.malaysia,R.drawable.brazil,R.drawable.indonesia,R.drawable.mauritania,R.drawable.croatia,R.drawable.india,R.drawable.argentina,R.drawable.euro,R.drawable.switzerland,R.drawable.philippines,R.drawable.kuwait,R.drawable.czech_republic,R.drawable.thailand,R.drawable.turkey,R.drawable.japan,R.drawable.libya,R.drawable.romania};
    TextView selectedName;
    ImageView selectedImage;
    RecyclerView recyclerViewAll;
    Switch favSwitch;
    EditText AmountText;
    Context context;
   List<String> favKeys = new ArrayList<>();
   List<String> favValues = new ArrayList<>();
   List<String> favFlags = new ArrayList<>();
   List<String> FinalFavKeys = new ArrayList<>();

   int[] test = new int[52];

    ArrayList<CalcModel> list = new ArrayList<>();

    public CalcAdapterAll(String[] keysArray, String[] valuesArray, float amount, TextView selectedName, ImageView selectedImage, Context context, RecyclerView recyclerViewAll, Switch favSwitch,EditText AmountText) {
        this.keysArray = keysArray;
        this.valuesArray = valuesArray;
        this.amount = amount;
        this.selectedName = selectedName;
        this.selectedImage = selectedImage;
        this.context = context;
        this.recyclerViewAll = recyclerViewAll;
        this.favSwitch = favSwitch;
        this.AmountText = AmountText;

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
        holder.flag.setImageResource(flags[position]);


        CharSequence text = selectedName.getText();
        if (text.equals("الروبل الروسي")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(valuesArray[0]))));
        else if (text.equals("دولار ترينيدادي")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(valuesArray[1]))));
        else if (text.equals("روبية نيبالية")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(valuesArray[2]))));
        else if (text.equals("ريـال قطري")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(valuesArray[3]))));
        else if (text.equals("كورونا دانيمركية")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(valuesArray[4]))));
        else if (text.equals("بولا بوتسوانية")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(valuesArray[5]))));
        else if (text.equals("روبية سريلانكية")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(valuesArray[6]))));
        else if (text.equals("دولار بوروني")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(valuesArray[7]))));
        else if (text.equals("كورونا نرويجية")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(valuesArray[8]))));
        else if (text.equals("دولار تايواني جديد")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(valuesArray[9]))));
        else if (text.equals("زلوتي بولندي")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(valuesArray[10]))));
        else if (text.equals("جنيه استرليني")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(valuesArray[11]))));
        else if (text.equals("تينج كازاخستاني")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(valuesArray[12]))));
        else if (text.equals("روبية باكستانية")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(valuesArray[13]))));
        else if (text.equals("دولار سينغافوري")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(valuesArray[14]))));
        else if (text.equals("فورينت مجري")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(valuesArray[15]))));
        else if (text.equals("بيسو مكسيكي")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(valuesArray[16]))));
        else if (text.equals("دولار كندي")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(valuesArray[17]))));
        else if (text.equals("بوليفار فنزويلي")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(valuesArray[18]))));
        else if (text.equals("درهم إماراتي")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(valuesArray[19]))));
        else if (text.equals("دينار بحريني")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(valuesArray[20]))));
        else if (text.equals("دولار أسترالي")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(valuesArray[21]))));
        else if (text.equals("ريـال سعودي")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(valuesArray[22]))));
        else if (text.equals("كرونا سويدية")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(valuesArray[23]))));
        else if (text.equals("يوان صيني")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(valuesArray[24]))));
        else if (text.equals("ليف بلغاري")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(valuesArray[25]))));
        else if (text.equals("راند جنوب أفريقي")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(valuesArray[26]))));
        else if (text.equals("يون كوريا الجنوبية")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(valuesArray[27]))));
        else if (text.equals("دولار هونج كونج")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(valuesArray[28]))));
        else if (text.equals("دولار نيوزيلاندي")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(valuesArray[29]))));
        else if (text.equals("بيسو كولومبي")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(valuesArray[30]))));
        else if (text.equals("بيسو شيلي")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(valuesArray[31]))));
        else if (text.equals("كرونا آيسلندي")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(valuesArray[32]))));
        else if (text.equals("ريـال إيراني")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(valuesArray[33]))));
        else if (text.equals("ريـال عماني")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(valuesArray[34]))));
        else if (text.equals("جنيه مصري")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(valuesArray[35]))));
        else if (text.equals("رينجيت ماليزي")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(valuesArray[36]))));
        else if (text.equals("ريـال برازيلي")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(valuesArray[37]))));
        else if (text.equals("روبية إندونيسية")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(valuesArray[38]))));
        else if (text.equals("روبية موريشيوس")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(valuesArray[39]))));
        else if (text.equals("كونا كرواتية")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(valuesArray[40]))));
        else if (text.equals("روبية هندية")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(valuesArray[41]))));
        else if (text.equals("بيسو أرجنتيني")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(valuesArray[42]))));
        else if (text.equals("يورو")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(valuesArray[43]))));
        else if (text.equals("فرانك سويسري")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(valuesArray[44]))));
        else if (text.equals("بيسو فليبيني")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(valuesArray[45]))));
        else if (text.equals("دينار كويتي")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(valuesArray[46]))));
        else if (text.equals("كورونا تشيكية")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(valuesArray[47]))));
        else if (text.equals("بات تايلاندي")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(valuesArray[48]))));
        else if (text.equals("ليرة تركية")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(valuesArray[49]))));
        else if (text.equals("ين ياباني")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(valuesArray[50]))));
        else if (text.equals("دينار ليبي")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(valuesArray[51]))));
        else if (text.equals("نيو ليو روماني")) holder.value.setText(String.valueOf(Float.parseFloat(valuesArray[position]) * amount * (1 / Float.parseFloat(valuesArray[52]))));

//        holder.fav.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (buttonView.isChecked()) {
//                    if (!loadData(holder.itemView.getContext(),"favKeys").contains(keysArray[position])) {
//                        favKeys.clear();
//                        favValues.clear();
//                        favKeys.add(keysArray[position]);
//                        favValues.add(valuesArray[position]);
//                            saveData(context,favKeys,"favKeys");
//                            saveData(context,favValues,"favValues");
//                            saveData(context,favFlags,"favFlags");
//
//                        test[position] = flags[position];
//                        System.out.println(Arrays.toString(test));
//                        System.out.println(favFlags);
//                    }
//
//                }
//            }
//        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (!loadData(holder.itemView.getContext(),"favKeys").contains(keysArray[position])) {
                    favKeys.add(keysArray[position]);
                    favValues.add(valuesArray[position]);
                    saveData(context,favKeys,"favKeys");
                    saveData(context,favValues,"favValues");
                    Toast.makeText(holder.itemView.getContext(), "تمت إضافة العملة إلى المفضلة", Toast.LENGTH_SHORT).show();
                    test[position] = flags[position];
                    System.out.println(Arrays.toString(test));
                    System.out.println(favFlags);
                }
                else if (loadData(holder.itemView.getContext(),"favKeys").contains(keysArray[position])){
                    Toast.makeText(holder.itemView.getContext(), "هذه العملة بالفعل موجودة في المفضلة!", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });




        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedName.setText(keysArray[position]);
                selectedImage.setImageResource(flags[position]);
                CalcAdapterAll calcAdapterAll = new CalcAdapterAll(keysArray,valuesArray,amount,selectedName,selectedImage,context,recyclerViewAll,favSwitch,AmountText);
                calcAdapterAll.notifyDataSetChanged();
                recyclerViewAll.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));
                recyclerViewAll.setAdapter(calcAdapterAll);
            }
        });
    }

    @Override
    public int getItemCount() {
        return flags.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,value;
        ImageView flag;
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

    // to load permanent data from SharedPreferences.
    public static List<String> loadData (Context context,String key) {
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
