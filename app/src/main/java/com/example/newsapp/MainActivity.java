package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements CategoryRVadapter.CategoryclickInterface {

    private RecyclerView newsRv, categoryRV;
    private ProgressBar progressBar;
    private ArrayList<Articles> articlesArrayList;
    private ArrayList<CategoryRVmodel>categoryRVmodelArrayList;
    private  CategoryRVadapter categoryRVadapter;
    private NewsRVadapter newsRVadapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //a542af682bb543c2bba93f7a6b60edc3

        newsRv=findViewById(R.id.newsRVitem);
        categoryRV=findViewById(R.id.NCategory);
        progressBar=findViewById(R.id.loading);
        articlesArrayList =new ArrayList<>();
        categoryRVmodelArrayList=new ArrayList<>();
        newsRVadapter=new NewsRVadapter(articlesArrayList,this);
        categoryRVadapter=new CategoryRVadapter(categoryRVmodelArrayList,this,this::onCategoryClick);
        newsRv.setLayoutManager(new LinearLayoutManager(this));
        newsRv.setAdapter(newsRVadapter);
        categoryRV.setAdapter(categoryRVadapter);
        getCategories();
        getNews("All");
        newsRVadapter.notifyDataSetChanged();
    }

    private void getCategories(){
        categoryRVmodelArrayList.add(new CategoryRVmodel("All","https://images.unsplash.com/photo-1451187580459-43490279c0fa?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=872&q=80"));
        categoryRVmodelArrayList.add(new CategoryRVmodel("Technology","https://images.unsplash.com/photo-1485827404703-89b55fcc595e?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1470&q=80"));
        categoryRVmodelArrayList.add(new CategoryRVmodel("Science","https://images.unsplash.com/photo-1605810230434-7631ac76ec81?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=870&q=80"));
        categoryRVmodelArrayList.add(new CategoryRVmodel("Sports","https://images.unsplash.com/photo-1612872087720-bb876e2e67d1?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1007&q=80"));
        categoryRVmodelArrayList.add(new CategoryRVmodel("Entertainment","https://images.unsplash.com/photo-1514525253161-7a46d19cd819?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=774&q=80"));
        categoryRVmodelArrayList.add(new CategoryRVmodel("Health","https://images.unsplash.com/photo-1532938911079-1b06ac7ceec7?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1032&q=80"));

        categoryRVadapter.notifyDataSetChanged();
    }

    ///Retrofit Code
    private void getNews(String category){
        progressBar.setVisibility(View.VISIBLE);
        articlesArrayList.clear();
        String categoryURL ="https://newsapi.org/v2/top-headlines?country=us&category="+category+"&apiKey=a542af682bb543c2bba93f7a6b60edc3";
        String url="https://newsapi.org/v2/top-headlines?country=us&apiKey=a542af682bb543c2bba93f7a6b60edc3";
        String BASE_URL="https://newsapi.org/";
        Retrofit retrofit =new Retrofit.Builder()
                .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitApi retrofitApi =retrofit.create(RetrofitApi.class);
        Call<NewsModel> call;
        if (category.equals("All")) {
            call = retrofitApi.getAllNews(url);
        } else {
            call = retrofitApi.getAllNews(categoryURL);
        }
        call.enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel>call,Response<NewsModel> response) {
                NewsModel newsModel =response.body();
                progressBar.setVisibility(View.GONE);
                ArrayList<Articles>articles =newsModel.getArticles();
                for (int i=0; i<articles.size();i++){
                    articlesArrayList.add(new Articles(articles.get(i).getTitle(),articles.get(i).getDescription(),articles.get(i).getUrlToImage(),
                            articles.get(i).getUrl(),articles.get(i).getContent()));
                }

                newsRVadapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Faild to ge tnews",Toast.LENGTH_SHORT).show();
            }

        });


    }

    @Override
    public void onCategoryClick(int position) {
        String category =categoryRVmodelArrayList.get(position).getCategory();
        getNews(category);


    }
}