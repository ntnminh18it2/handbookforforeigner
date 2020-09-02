package com.example.myapplication.src.Adapter.fragment_home_adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import com.example.myapplication.src.Activity.PlaceActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterRecyclerviewUtilities extends RecyclerView.Adapter<AdapterRecyclerviewUtilities.Viewhodler> {
    private ArrayList<com.example.myapplication.models.menu_reponse.Menu>arrayList;
    private Context context;
    private int layout;
    private int number;

    public AdapterRecyclerviewUtilities(ArrayList<com.example.myapplication.models.menu_reponse.Menu> arrayList, Context context, int layout,int number) {
        this.arrayList = arrayList;
        this.context = context;
        this.layout = layout;
        this.number = number;
    }

    @NonNull
    @Override
    public Viewhodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context,layout,null);
        return new Viewhodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewhodler holder, final int position) {
    holder.mTxtItemUtiliesHompagee.setText(arrayList.get(position).getName());
    Picasso.with(context).load(arrayList.get(position).getIcons()).into(holder.mImgItemUtilitiesHomepage);
    holder.mImgItemUtilitiesHomepage.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, PlaceActivity.class);
            intent.putExtra("id",arrayList.get(position).getId());
            intent.putExtra("name",arrayList.get(position).getName());
            context.startActivity(intent);
            ((AppCompatActivity)context).overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_ride);

        }
    });
    }

    @Override
    public int getItemCount() {
        return number;
    }

    public class Viewhodler extends RecyclerView.ViewHolder{
        private ImageView mImgItemUtilitiesHomepage;
        private TextView mTxtItemUtiliesHompagee;
        public Viewhodler(@NonNull View itemView) {
            super(itemView);
            mImgItemUtilitiesHomepage = itemView.findViewById(R.id.imgItemUtilitiesHomepage);
            mTxtItemUtiliesHompagee = itemView.findViewById(R.id.txtItemUtiliesHompagee);
        }
    }
}
