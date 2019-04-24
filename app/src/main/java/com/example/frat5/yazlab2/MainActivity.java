package com.example.frat5.yazlab2;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private CarouselView carouselView;
    private ImageView imageView;
    private Spinner mySpinner;
    private TextView text;
    int[] sampleImages = {R.drawable.at, R.drawable.kedi, R.drawable.kopek, R.drawable.kunduz, R.drawable.sincap};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        imageView = (ImageView) findViewById(R.id.imageView);
        mySpinner = (Spinner) findViewById(R.id.spinner);
        text = (TextView) findViewById(R.id.title);
        new arkaPlan().execute();

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
        protected String doInBackground(String... params) {
            try {
            URL url = null;
            String response = null;
            String parameters = "id=1";
            url = new URL("http://192.168.1.104:8080/api/10news/1");
            //create the connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            String line = "";
            //create your inputsream
            InputStreamReader isr = new InputStreamReader(
                    connection.getInputStream());
            //read in the data from input stream, this can be done a variety of ways
            BufferedReader reader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
                text.setText("Gorkem we did it!!");
                System.out.println("1");
            }
            //get the string version of the response data
            response = sb.toString();
            //do what you want with the data now

            //always remember to close your input and output streams
            isr.close();
            reader.close();
        } catch (IOException e) {
            Log.e("HTTP GET:", e.toString());
        }
            return null;
        }

        }
        protected void onPostExecute(String s){

        }
    }