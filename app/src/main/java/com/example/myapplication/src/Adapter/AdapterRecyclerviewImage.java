package com.example.myapplication.src.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.models.place_reponse.Place;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterRecyclerviewImage extends RecyclerView.Adapter<AdapterRecyclerviewImage.Viewholder>{
    private Context context;
    private int layout;
    private ArrayList<Place>arrayList;

    public AdapterRecyclerviewImage(Context context, int layout, ArrayList<Place> arrayList) {
        this.context = context;
        this.layout = layout;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context,layout,null);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        Picasso.with(context).load(arrayList.get(position).getImage()).into(holder.mImgRecyclerviewHomepage);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        private ImageView mImgRecyclerviewHomepage;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            mImgRecyclerviewHomepage = itemView.findViewById(R.id.imgRecyclerviewHomepage);
        }
    }
}
