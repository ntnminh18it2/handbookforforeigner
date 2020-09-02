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
import com.example.myapplication.models.menu_reponse.Menu;
import com.example.myapplication.src.Activity.PlaceActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterRecyclerviewMenu extends RecyclerView.Adapter<AdapterRecyclerviewMenu.ViewHodler>{
    private Context context;
    private ArrayList<Menu>arrayList;
    private int layout;
    private View mView;

    public AdapterRecyclerviewMenu(Context context, ArrayList<Menu> arrayList, int layout) {
        this.context = context;
        this.arrayList = arrayList;
        this.layout = layout;
    }

    @NonNull
    @Override
    public ViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mView = View.inflate(context,layout,null);
        return new ViewHodler(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodler holder, final int position) {
        holder.txtNameDining_Entertainment.setText(arrayList.get(position).getName());
        Picasso.with(mView.getContext()).load(arrayList.get(position).getIcons()).into(holder.imgDining_Entertainment);
        holder.imgDining_Entertainment.setOnClickListener(new View.OnClickListener() {
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
        return arrayList.size();
    }

    class ViewHodler extends RecyclerView.ViewHolder{
        TextView txtNameDining_Entertainment;
        ImageView imgDining_Entertainment;
        public ViewHodler(@NonNull View itemView) {
            super(itemView);
            txtNameDining_Entertainment = itemView.findViewById(R.id.txtDining_Entertainment);
            imgDining_Entertainment = itemView.findViewById(R.id.imgDining_Entertainment);
        }
    }

}
