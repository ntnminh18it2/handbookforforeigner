package com.example.myapplication.src.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import com.example.myapplication.models.place_reponse.Place;
import com.example.myapplication.src.Activity.DetailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterLisplace extends RecyclerView.Adapter<AdapterLisplace.Viewhodlere> {
    private ArrayList<Place>mArrayList;
    private Context mContext;
    private int mLayout;
    private View mView;

    public AdapterLisplace(ArrayList<Place> mArrayList, Context mContext, int mLayout) {
        this.mArrayList = mArrayList;
        this.mContext = mContext;
        this.mLayout = mLayout;
    }

    @NonNull
    @Override
    public Viewhodlere onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mView = View.inflate(mContext,mLayout,null);
        return new Viewhodlere(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewhodlere holder, final int position) {
        holder.mTxtIntroduce.setText(mArrayList.get(position).getIntroduce());
        holder.mTxtName.setText(mArrayList.get(position).getName());
        Picasso.with(mContext).load(mArrayList.get(position).getImage()).into(holder.mImage);
        holder.mLinerLayoutItemListPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("id",mArrayList.get(position).getId());
                mContext.startActivity(intent);
                ((AppCompatActivity)mContext).overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_ride);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    public class Viewhodlere extends RecyclerView.ViewHolder{
        private TextView mTxtName;
        private TextView mTxtIntroduce;
        private ImageView mImage;
        private LinearLayout mLinerLayoutItemListPlace;
        public Viewhodlere(@NonNull View itemView) {
            super(itemView);
            mTxtName = itemView.findViewById(R.id.txtNameListPlace);
            mTxtIntroduce = itemView.findViewById(R.id.txtIntroduceListPlace);
            mImage = itemView.findViewById(R.id.imgItemListPlace);
            mLinerLayoutItemListPlace = itemView.findViewById(R.id.linerLayoutItemListPlace);
        }
    }
}
