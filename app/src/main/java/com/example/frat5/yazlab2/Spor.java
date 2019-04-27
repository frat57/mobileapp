package com.example.frat5.yazlab2;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class Spor extends AppCompatActivity implements HaberlerAdapter.OnItemClickListener{
    private Spinner mySpinner;
    ArrayList<Haberler>haberlerArrayList;
    RecyclerView recyclerView;
    HaberlerAdapter adapter;
    private String URL="api/10news/1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        String urlPrefix = getResources().getString(R.string.url_prefix);
        URL = urlPrefix + URL;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spor);
        ArrayList<String> names  = new ArrayList<>();
        names.add("Spor");
        names.add("AnaSayfa");
        names.add("Gündem");
        names.add("Dünya");
        names.add("Ekonomi");

        mySpinner = (Spinner) findViewById(R.id.spinner);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        haberlerArrayList = new ArrayList<Haberler>();

        LoadJSON();
        adapter = new HaberlerAdapter(this,haberlerArrayList,this);
        recyclerView.setAdapter(adapter);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(Spor.this, android.R.layout.simple_list_item_1, names);
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
                    if(parent.getItemAtPosition(position).equals("Home")){
                        openHome();//Ana Sayfa
                    }
                    if(parent.getItemAtPosition(position).equals("Gündem")){
                        openTrend();//Gundem ler sayfasi
                    }
                    if(parent.getItemAtPosition(position).equals("Dünya")){
                        openWorld();//Dünya sayfasi
                    }
                    if(parent.getItemAtPosition(position).equals("Ekonomi")){
                        openEkonomi();//Ekonomi sayfasi
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
    public void openHome(){
        Intent intent =new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemClick(int position) {

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
                    if(  object.getString("type").equals("spor")) {
                    int id =  object.getInt("id");
                    String title = object.getString("name");
                    String content = object.getString("content");
                    String type = object.getString("type");
                    String image_link = object.getString("image_link");
                    int like_number = object.getInt("like_number");
                    int dislike_number =  object.getInt("dislike_number");
                    int view_count = object.getInt("view_count");
                    String datem = object.getString("date");
                    datem = datem.substring(0, datem.indexOf('T'));
                    Haberler haberlerim = new Haberler(id,like_number,dislike_number,
                            view_count,title,content,type,image_link,datem);
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

