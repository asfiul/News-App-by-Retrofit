package com.example.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoryRVadapter extends RecyclerView.Adapter<CategoryRVadapter.Viewholder> {
    private ArrayList<CategoryRVmodel>categoryRVmodels;
    private Context context;

    private CategoryclickInterface categoryclickInterface;

    public CategoryRVadapter(ArrayList<CategoryRVmodel> categoryRVmodels, Context context, CategoryclickInterface categoryclickInterface) {
        this.categoryRVmodels = categoryRVmodels;
        this.context = context;
        this.categoryclickInterface = categoryclickInterface;
    }

    @NonNull
    @Override
    public CategoryRVadapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.categoris_rv_item,parent,false);

        return new CategoryRVadapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryRVadapter.Viewholder holder, int position) {
        CategoryRVmodel categoryRVmodel= categoryRVmodels.get(position);
       holder.categoryText.setText(categoryRVmodel.getCategory());

       Picasso.get().load(categoryRVmodel.getCategoryImageUrl()).into(holder.categoryImage);

       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               categoryclickInterface.onCategoryClick(position);
           }
       });

    }

    @Override
    public int getItemCount() {
        return categoryRVmodels.size();
    }

    public interface CategoryclickInterface{
        void onCategoryClick(int position);
    }

    @Override
    public void unregisterAdapterDataObserver(@NonNull RecyclerView.AdapterDataObserver observer) {
        super.unregisterAdapterDataObserver(observer);
    }

    class Viewholder extends RecyclerView.ViewHolder{
        private TextView categoryText;
        private ImageView categoryImage;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            categoryText=itemView.findViewById(R.id.miniText);
            categoryImage=itemView.findViewById(R.id.miniCategory);
        }
    }

}
