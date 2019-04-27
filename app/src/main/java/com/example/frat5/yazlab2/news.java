package com.example.frat5.yazlab2;

import android.content.Intent;
import android.media.Image;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class news extends AppCompatActivity implements HaberlerAdapter.OnItemClickListener{
    private TextView title;
    private TextView source;
    private TextView likesayisi;
    private TextView dislikesayisi;
    private TextView sayisi;
    private int like_number,disslike_number,View_count;
    private boolean flag = true;
    ImageView imageview;
    ArrayList<Haberler> haberlerArrayList;
    private String URL="api/10news/1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        title = (TextView)findViewById(R.id.Title);
        source = (TextView)findViewById(R.id.source);
        likesayisi = (TextView)findViewById(R.id.likesayisi);
        dislikesayisi = (TextView)findViewById(R.id.dislikesayisi);
        sayisi = (TextView)findViewById(R.id.view_count);
        imageview = (ImageView)findViewById(R.id.img);
        Button like_button = (Button)findViewById(R.id.likeButton);
        Button dislike_button = (Button)findViewById(R.id.dislikeButton);

        Intent intent= getIntent();
        String name =  intent.getStringExtra("name");
        String content =  intent.getStringExtra("content");
        like_number =  intent.getIntExtra("like_number",0);
        disslike_number =  intent.getIntExtra("disslike_number",0);
        View_count =  intent.getIntExtra("View_count",0);
        View_count++;
        title.setText(name);
        source.setText(content);
        likesayisi.setText(String.valueOf(like_number));
        dislikesayisi.setText(String.valueOf(disslike_number));
        sayisi.setText(String.valueOf(View_count));

        like_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag == true ){
                     like_number++;
                    likesayisi.setText(String.valueOf(like_number));
                }
                flag = false;
            }
        });
        dislike_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag == true ){
                      disslike_number++;
                    dislikesayisi.setText(String.valueOf(disslike_number));
                }
                flag = false;
            }
        });
       // LoadJSON();
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent (this,news.class);
    }
    public void LoadJSON(){
        HttpHandler httpHandler = new HttpHandler();
        String jsonString = httpHandler.makeServiceCall(URL);

        Log.d("JSON_RESPONSE",jsonString);
        if(jsonString !=  null ){
            try {
                JSONArray array = new JSONArray(jsonString);
                for(int i=0; i< array.length(); i++) {
                    JSONObject object = array.getJSONObject(i);
                    if(  object.getString("name").equals("spor")) {
                        int id =  object.getInt("id");
                        String title = object.getString("name");
                        String content = object.getString("content");
                        String type = object.getString("type");
                        String image_link = object.getString("image_link");
                        int like_number = object.getInt("like_number");
                        int dislike_number =  object.getInt("dislike_number");
                        int view_count = object.getInt("view_count");
                        Haberler haberlerim = new Haberler(id,like_number,dislike_number,
                                view_count,title,content,type,image_link);
                        haberlerArrayList.add (haberlerim);
                    }}

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else {
            Log.d("JSON_RESPONSE","Sayfa kaynağı boş");
        }
    }
}
