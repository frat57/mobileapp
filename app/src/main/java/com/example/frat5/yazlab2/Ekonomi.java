package com.example.frat5.yazlab2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class Ekonomi extends AppCompatActivity {

    private Spinner mySpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ArrayList<String> names  = new ArrayList<>();
        names.add("Ekonomi");
        names.add("AnaSayfa");
        names.add("Gündem");
        names.add("Dünya");
        names.add("Spor");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ekonomi);

        mySpinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(Ekonomi.this, android.R.layout.simple_list_item_1, names);
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).equals("HaberTürleri")){
                    //Home Page
                }
                else{
                    String item = parent.getItemAtPosition(position).toString();
                    if(parent.getItemAtPosition(position).equals("AnaSayfa")){
                        openHome();//Ana Sayfa
                    }
                    if(parent.getItemAtPosition(position).equals("Gündem")){
                        openTrend();//Gundem ler sayfasi
                    }
                    if(parent.getItemAtPosition(position).equals("Dünya")){
                        openWorld();//Dünya sayfasi
                    }
                    if(parent.getItemAtPosition(position).equals("Spor")){
                        openSpor();//Spor sayfasi
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void openTrend(){
        Intent intent =new Intent(this,Trend.class);
        startActivity(intent);
    }
    public void openEkonomi(){
        Intent intent =new Intent(this,Ekonomi.class);
        startActivity(intent);
    }
    public void openWorld(){
        Intent intent =new Intent(this,World.class);
        startActivity(intent);
    }
    public void openSpor(){
        Intent intent =new Intent(this,Spor.class);
        startActivity(intent);
    }
    public void openHome(){
        Intent intent =new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
