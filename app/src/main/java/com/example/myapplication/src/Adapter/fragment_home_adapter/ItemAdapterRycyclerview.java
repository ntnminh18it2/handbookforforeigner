package com.example.myapplication.src.Adapter.fragment_home_adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.models.place_reponse.Place;
import com.example.myapplication.src.Activity.DetailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ItemAdapterRycyclerview  extends RecyclerView.Adapter<ItemAdapterRycyclerview.Viewhodlerr> {
    private ArrayList<Place> mArrayList;
    private Context mConText;
    private int mLayout;
    private  View view;
    private int mPosition;

    public ItemAdapterRycyclerview(ArrayList<Place> mArrayList, Context mConText, int mLayout) {
        this.mArrayList = mArrayList;
        this.mConText = mConText;
        this.mLayout = mLayout;
    }

    @NonNull
    @Override
    public Viewhodlerr onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = View.inflate(mConText,mLayout,null);
        return new Viewhodlerr(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewhodlerr holder, final int position) {
        holder.mTextView.setText(mArrayList.get(position).getName());
        try{
            Picasso.with(mConText).load(mArrayList.get(position).getImage()).into(holder.mImageview);
        }catch (Exception e){
            Log.d("AAA",e.getMessage());
        }
        mPosition = position;
       holder.mCart3.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(mConText, DetailActivity.class);
               intent.putExtra("id",mArrayList.get(position).getId());
               mConText.startActivity(intent);
               ((AppCompatActivity)mConText).overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_ride);
           }
       });
    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }


    public class Viewhodlerr extends RecyclerView.ViewHolder{
        private ImageView mImageview;
        private TextView mTextView;
        private CardView mCart3;
      public Viewhodlerr(@NonNull View itemView) {
          super(itemView);
          mTextView = itemView.findViewById(R.id.txtItemDestinationHomepage);
          mImageview = itemView.findViewById(R.id.img);
          mCart3 = itemView.findViewById(R.id.Cart3);
      }
  }

}
