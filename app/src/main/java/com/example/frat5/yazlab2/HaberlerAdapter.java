package com.example.frat5.yazlab2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;



import java.util.ArrayList;

public class HaberlerAdapter extends RecyclerView.Adapter <HaberlerAdapter.HaberlerViewAdapter> {
    Context context;
    ArrayList<Haberler>haberlerArrayList;
    LayoutInflater layoutInflater;
    private OnItemClickListener onItemClickListener;
    private TextView title;
    private TextView content;
    private ImageView image;
    private TextView publishedAt;
    private TextView like_number;
    private TextView disslike_number;
    private TextView view_count;

    public HaberlerAdapter(Context context, ArrayList<Haberler> haberlerArrayList) {
        this.context = context;
        this.haberlerArrayList = haberlerArrayList;
    }

    @NonNull
    @Override
    public HaberlerViewAdapter onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.carditem,null);
        return  new HaberlerViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HaberlerViewAdapter haberlerViewAdapter, int i) {
        Haberler haberler=haberlerArrayList.get(i);
        haberlerViewAdapter.title.setText(haberler.getName());
        haberlerViewAdapter.content.setText(haberler.getContent());
    }
    @Override
    public int getItemCount() {
        return haberlerArrayList.size();
    }
    public void setOnItemClickListener(HaberlerAdapter.OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
public interface OnItemClickListener{
    void onItemClick(View view,int position);
}
    class HaberlerViewAdapter extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView title,content;
        public HaberlerViewAdapter(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imageView);
            title = itemView.findViewById(R.id.title);
            content = itemView.findViewById(R.id.desc);
            publishedAt = itemView.findViewById(R.id.publishedAt);
        }
    }
}
