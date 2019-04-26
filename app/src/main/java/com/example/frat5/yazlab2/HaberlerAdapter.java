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

public class HaberlerAdapter extends RecyclerView.Adapter <HaberlerAdapter.HaberlerViewHolder> {
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

    public HaberlerAdapter(Context context, ArrayList<Haberler> haberlerArrayList) {
        this.context = context;
        this.haberlerArrayList = haberlerArrayList;
    }

    private TextView view_count;


    @NonNull
    @Override
    public HaberlerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.carditem,null);
        return  new HaberlerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HaberlerViewHolder haberlerViewAdapter, int i) {
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
   public  class HaberlerViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView title,content;
        public HaberlerViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.img);
            title = itemView.findViewById(R.id.title);
            content = itemView.findViewById(R.id.source);
            publishedAt = itemView.findViewById(R.id.publishedAt);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    
                }
            });
        }
    }
}
