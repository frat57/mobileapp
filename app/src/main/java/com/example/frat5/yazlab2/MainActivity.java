package com.example.frat5.yazlab2;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {
    HttpHandler httpHandler;
    private CarouselView carouselView;
    private ImageView imageView;
    private Spinner mySpinner;
    private TextView text;
    int[] sampleImages = {R.drawable.at, R.drawable.kedi, R.drawable.kopek, R.drawable.kunduz, R.drawable.sincap};
    final static String URL="http://192.168.1.104:8080/api/10news/1";
    ArrayList<Haberler>haberlerArrayList;
    RecyclerView recyclerView;
    HaberlerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ArrayList <String> names  = new ArrayList<>();
        names.add(0,"AnaSayfa");
        names.add("Gündem");
        names.add("Dünya");
        names.add("Ekonomi");
        names.add("Spor");
        new arkaPlan().execute(URL);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        carouselView = (CarouselView) findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        imageView = (ImageView) findViewById(R.id.imageView);
        mySpinner = (Spinner) findViewById(R.id.spinner);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new HaberlerAdapter(this,haberlerArrayList);
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

    class arkaPlan extends AsyncTask<String,String,String>{
        ArrayList<Haberler>haberlerArrayList = new ArrayList<>();

        protected String doInBackground(String... params) {
            httpHandler = new HttpHandler();
            String jsonString = httpHandler.makeServiceCall(URL);

            Log.d("JSON_RESPONSE",jsonString);
            if(jsonString !=  null){
                try {
                    System.out.println("Burdayim");
                    JSONObject jsonObject = new JSONObject(jsonString);
                    System.out.println("Burdayim2");
                    JSONArray haberler = jsonObject.getJSONArray("news");
                    System.out.println("Burdayim3");
                    //id ler icin hepsini alıyoruz
                    for (int i = 0; i <haberler.length() ; i++) {
                        JSONObject haber = haberler.getJSONObject(i);

                        int id =  haber.getInt("id");
                        String title = haber.getString("name");
                        String content = haber.getString("content");
                        String type = haber.getString("type");
                        String image_link = haber.getString("image_link");
                        int like_number = haber.getInt("like_number");
                        int disslike_number =  haber.getInt("disslike_number");
                        int view_count = haber.getInt("view_count");
                        Haberler haberlerim = new Haberler(id,like_number,disslike_number,
                                view_count,title,content,type,image_link);
                        haberlerArrayList.add (haberlerim);
                            System.out.println(haberlerArrayList.get(i)+"12345");

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else {
                Log.d("JSON_RESPONSE","Sayfa kaynağı boş");
            }
            return null;
        }
        protected void onPostExecute(String data){
        }
    }
    }