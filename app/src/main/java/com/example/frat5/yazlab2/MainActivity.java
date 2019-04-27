package com.example.frat5.yazlab2;

import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements HaberlerAdapter.OnItemClickListener{

    HttpHandler httpHandler;
    private CarouselView carouselView;
    private ImageView imageView;
    private Spinner mySpinner;
    private TextView text;
    int[] sampleImages = {R.drawable.at, R.drawable.kedi, R.drawable.kopek, R.drawable.kunduz, R.drawable.sincap};
    private String URL="api/10news/1";
    ArrayList<Haberler>haberlerArrayList;
    RecyclerView recyclerView;
    HaberlerAdapter adapter;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        String urlPrefix = getResources().getString(R.string.url_prefix);
        URL = urlPrefix + URL;
        ArrayList <String> names  = new ArrayList<>();
        names.add(0,"AnaSayfa");
        names.add("Gündem");
        names.add("Dünya");
        names.add("Ekonomi");
        names.add("Spor");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        carouselView = (CarouselView) findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        imageView = (ImageView) findViewById(R.id.img);
        mySpinner = (Spinner) findViewById(R.id.spinner);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        haberlerArrayList = new ArrayList<Haberler>();

        httpHandler = new HttpHandler();
        String jsonString = httpHandler.makeServiceCall(URL);

        Log.d("JSON_RESPONSE",jsonString);
        if(jsonString !=  null ){
            try {
                JSONArray array = new JSONArray(jsonString);
                for(int i=0; i< array.length(); i++) {
                    JSONObject object = array.getJSONObject(i);
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
                    System.out.println(haberlerArrayList.get(i).getName());
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else {
            Log.d("JSON_RESPONSE","Sayfa kaynağı boş");
        }

        adapter = new HaberlerAdapter(this,haberlerArrayList,this);
        recyclerView.setAdapter(adapter);

        text = (TextView) findViewById(R.id.title);
        Haberler haberler = new Haberler();

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, names);
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
                    if(parent.getItemAtPosition(position).equals("Gündem")){
                        openTrend();//Gundem ler sayfasi
                    }
                    if(parent.getItemAtPosition(position).equals("Dünya")){
                        openWorld();//Dünya sayfasi
                    }
                    if(parent.getItemAtPosition(position).equals("Ekonomi")){
                        openEkonomi();//Ekonomi sayfasi
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
       carouselView.setImageListener (new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(sampleImages[position]);
            }
        });

        carouselView.setImageClickListener(new ImageClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(MainActivity.this, "Clicked item: "+ position, Toast.LENGTH_SHORT).show();
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

    @Override
    public void onItemClick(int position) {
        Log.d("hobbala","onNoteClick: clicked.");
       /* Intent intent = new Intent (this,news.class);
        startActivity(intent);*/
    }

    }