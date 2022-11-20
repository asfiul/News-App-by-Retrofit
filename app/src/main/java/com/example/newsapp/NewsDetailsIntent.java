package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class NewsDetailsIntent extends AppCompatActivity {




 String title,desc,content,image,url;

    private TextView newsTitleTV, newsSubDescTV, newsContentTV;
    private ImageView newsIV;
    private Button newsDetailBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details_intent);
        title=getIntent().getStringExtra("title");
        desc=getIntent().getStringExtra("desc");
        content=getIntent().getStringExtra("content");
        image=getIntent().getStringExtra("image");
        url=getIntent().getStringExtra("url");

        newsTitleTV=findViewById(R.id.intent_header);
        newsSubDescTV=findViewById(R.id.Sub_intent);
        newsContentTV=findViewById(R.id.full_sub_intent);
        newsIV=findViewById(R.id.image_intent);
        newsDetailBtn=findViewById(R.id.button);

        newsTitleTV.setText(title);
        newsSubDescTV.setText(desc);
        newsContentTV.setText(content);
        Picasso.get().load(image).into(newsIV);
        newsDetailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });



    }
}