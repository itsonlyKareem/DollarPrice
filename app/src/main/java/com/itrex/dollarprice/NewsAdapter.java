package com.itrex.dollarprice;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private List<NewsModel> list;

    public NewsAdapter(List<NewsModel> list) {
        this.list = list;

    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_row,parent,false);
        return new ViewHolder(rowItem);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {

        holder.myText1.setText(list.get(position).getTitle());
        System.out.println(list.get(position).getTopic());
        holder.myText2.setText(list.get(position).getTopic());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myText1, myText2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            myText1 = itemView.findViewById(R.id.newsTopic);
            myText2 = itemView.findViewById(R.id.newsInfo);

        }

        @Override
        public void onClick(View v) {

            Intent intent = new Intent(v.getContext(),NewsPage.class);
            intent.putExtra("title", this.myText1.getText());
            intent.putExtra("info", this.myText2.getText());
            v.getContext().startActivity(intent);

        }
    }
}
