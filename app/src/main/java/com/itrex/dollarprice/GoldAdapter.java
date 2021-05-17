package com.itrex.dollarprice;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class GoldAdapter extends RecyclerView.Adapter<GoldAdapter.ViewHolder> {
    private List<GoldModel> list;

    public GoldAdapter(List<GoldModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.gold_row,parent,false);
        return new ViewHolder(rowItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.myText1.setText(list.get(position).getName());
        holder.myText2.setText(list.get(position).getPrice());
        holder.myImage.setImageResource(R.drawable.ingot);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView myText1, myText2;
        ImageView myImage;

        public ViewHolder (@NonNull View itemView) {
            super(itemView);
            myText1 = itemView.findViewById(R.id.goldName);
            myText2 = itemView.findViewById(R.id.goldPrice);
            myImage = itemView.findViewById(R.id.goldImage);
        }
    }
}
