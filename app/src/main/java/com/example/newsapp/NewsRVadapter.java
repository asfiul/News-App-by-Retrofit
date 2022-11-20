package com.example.newsapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class NewsRVadapter extends RecyclerView.Adapter<NewsRVadapter.ViewHolder> {  //1
    private ArrayList<Articles>articlesArrayList; //3
    private Context context;

    public NewsRVadapter(ArrayList<Articles> articlesArrayList, Context context) { //3-Create constrator
        this.articlesArrayList = articlesArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsRVadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
     /* 5 */
        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.news_main_item,parent,false);
        return new NewsRVadapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsRVadapter.ViewHolder holder, int position) {
    Articles articles=articlesArrayList.get(position);     //6 //
    holder.subTitleTv.setText(articles.getDescription());
    holder.titleTv.setText(articles.getTitle());
        Picasso.get().load(articles.getUrlToImage()).into(holder.newsTv);

        //  7     //

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(context,NewsDetailsIntent.class);
                i.putExtra("title",articles.getTitle());
                i.putExtra("desc",articles.getDescription());
                i.putExtra("content",articles.getContent());
                i.putExtra("image",articles.getUrlToImage());
                i.putExtra("url",articles.getUrl());
                context.startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return articlesArrayList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{   //2
        private TextView titleTv, subTitleTv;    //4
        private ImageView newsTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTv=itemView.findViewById(R.id.news_headerText); //4
            subTitleTv=itemView.findViewById(R.id.news_smallText);
            newsTv=itemView.findViewById(R.id.news_Image);
        }
    }

}
