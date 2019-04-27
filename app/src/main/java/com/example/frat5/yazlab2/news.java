package com.example.frat5.yazlab2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class news extends AppCompatActivity implements HaberlerAdapter.OnItemClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent (this,news.class);
    }
}
