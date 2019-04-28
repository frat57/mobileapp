package com.example.frat5.yazlab2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class news extends AppCompatActivity implements HaberlerAdapter.OnItemClickListener {
    private TextView title;
    private TextView source;
    private TextView likesayisi;
    private TextView dislikesayisi;
    private TextView sayisi;
    private TextView publishedAt;
    private TextView viewNumber;
    private int like_number, dislike_number, View_count;
    private boolean flag = true;
    ImageView imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        title = (TextView) findViewById(R.id.Title);
        source = (TextView) findViewById(R.id.source);
        likesayisi = (TextView) findViewById(R.id.likesayisi);
        dislikesayisi = (TextView) findViewById(R.id.dislikesayisi);
        sayisi = (TextView) findViewById(R.id.view_count);
        imageview = (ImageView) findViewById(R.id.image);
        publishedAt = (TextView) findViewById(R.id.publishedAt);
        Button like_button = (Button) findViewById(R.id.likeButton);
        Button dislike_button = (Button) findViewById(R.id.dislikeButton);
        viewNumber = (TextView) findViewById(R.id.view_count);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String content = intent.getStringExtra("content");
        like_number = intent.getIntExtra("like_number", 0);
        dislike_number = intent.getIntExtra("dislike_number", 0);
        View_count = intent.getIntExtra("View_count", 0);
        String image_link = intent.getStringExtra("Image_link");
        String publish = intent.getStringExtra("date");
        final int id = intent.getIntExtra("id", 0);
        View_count++;
        title.setText(name);
        source.setText(content);
        likesayisi.setText(String.valueOf(like_number));
        dislikesayisi.setText(String.valueOf(dislike_number));
        sayisi.setText(String.valueOf(View_count));
        publishedAt.setText(publish);
        //imageView
        try {
            URL urlConnection = new URL(image_link);
            HttpURLConnection connection = (HttpURLConnection) urlConnection.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            imageview.setImageBitmap(myBitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }


        like_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag == true) {
                    new HttpPostMethod(getResources().getString(R.string.url_prefix) + "like", "id=" + id).postMethod();
                    like_number++;
                    likesayisi.setText(String.valueOf(like_number));
                }
                flag = false;
            }
        });
        dislike_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag == true) {
                    new HttpPostMethod(getResources().getString(R.string.url_prefix) + "dislike", "id=" + id).postMethod();
                    dislike_number++;
                    dislikesayisi.setText(String.valueOf(dislike_number));
                }
                flag = false;
            }
        });

        //vote
        new HttpPostMethod(getResources().getString(R.string.url_prefix) + "view", "id=" + id).postMethod();
        viewNumber.setText(Integer.toString(View_count));

    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, news.class);
    }
}
