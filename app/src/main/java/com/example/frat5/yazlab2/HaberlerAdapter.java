package com.example.frat5.yazlab2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HaberlerAdapter extends RecyclerView.Adapter<HaberlerAdapter.HaberlerViewHolder> {
    Context context;
    ArrayList<Haberler> haberlerArrayList;
    LayoutInflater layoutInflater;
    private OnItemClickListener onItemClickListener;
    private TextView title;
    private TextView content;
    private ImageView image;
    private TextView publishedAt;
    private TextView like_number;
    private TextView disslike_number;
    private OnItemClickListener MonItemClickListener;

    public HaberlerAdapter(Context context, ArrayList<Haberler> haberlerArrayList, OnItemClickListener OnItemClickListener) {
        this.context = context;
        this.haberlerArrayList = haberlerArrayList;
        this.MonItemClickListener = onItemClickListener;
    }

    private TextView view_count;


    @NonNull
    @Override
    public HaberlerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.carditem, null);
        return new HaberlerViewHolder(view, MonItemClickListener);
    }

    @Override
    public void onBindViewHolder(HaberlerViewHolder haberlerViewHolder, final int i) {
        final Haberler haberler = haberlerArrayList.get(i);
        haberlerViewHolder.title.setText(haberler.getName());
        haberlerViewHolder.content.setText(haberler.getContent());
        haberlerViewHolder.publishedAt.setText(haberler.getDate());
        new ImageLoadTask(haberler.getImage_link(), haberlerViewHolder.imageView).execute();

        haberlerViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(), news.class);
                intent.putExtra("id", haberler.getId());
                intent.putExtra("name", haberler.getName());
                intent.putExtra("content", haberler.getContent());
                intent.putExtra("like_number", haberler.getLike_number());
                intent.putExtra("dislike_number", haberler.getDislike_number());
                intent.putExtra("View_count", haberler.getView_count());
                intent.putExtra("Image_link", haberler.getImage_link());
                intent.putExtra("date", haberler.getDate());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return haberlerArrayList.size();
    }

    public void setOnItemClickListener(HaberlerAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    public interface OnItemClickListener {
        void onItemClick(int position);

    }

    public class HaberlerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView title, content, publishedAt;
        OnItemClickListener onItemClickListener;

        public HaberlerViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img);
            title = itemView.findViewById(R.id.title);
            content = itemView.findViewById(R.id.source);
            publishedAt = itemView.findViewById(R.id.publishedAt);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(getAdapterPosition());
        }
    }


}
