package com.example.frat5.yazlab2;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class HaberlerAdapter extends RecyclerView.Adapter {
    private TextView title;
    private TextView content;
    private ImageView image;
    private TextView like_number;
    private TextView disslike_number;
    private TextView view_count;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
