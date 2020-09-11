package com.example.myapplication.src.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.models.menu_reponse.Menu;
import com.example.myapplication.models.place_reponse.Place;
import com.example.myapplication.models.place_reponse.PlaceReponse;
import com.example.myapplication.services.APIServices;
import com.example.myapplication.services.DataService;
import com.example.myapplication.src.Activity.MapActivity;
import com.example.myapplication.src.dialog.LoadingDialog_new;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterMenuMap extends RecyclerView.Adapter<AdapterMenuMap.Viewhodler> {
    private MapActivity context;
    private int layout;
    private ArrayList<Menu>arrayList;
    private String TAG = "AAA";

    public AdapterMenuMap(MapActivity context, int layout, ArrayList<Menu> arrayList) {
        this.context = context;
        this.layout = layout;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Viewhodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context,layout,null);
        return new Viewhodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewhodler holder, final int position) {
        holder.txtTitileMenu.setText(arrayList.get(position).getName());
        Picasso.with(context).load(arrayList.get(position).getIcons()).into(holder.imgIconMenu);
        holder.cardViewMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataPlaeByIdIngredient(arrayList.get(position).getId());
            }
        });
    }

    private void getDataPlaeByIdIngredient(Integer id) {
        DataService dataService = APIServices.getService() ;
        Call<PlaceReponse>call = dataService.getDataPlaceIdMenu(id);
        final Dialog dialog = LoadingDialog_new.showdialog(context);
        dialog.show();

        call.enqueue(new Callback<PlaceReponse>() {
            @Override
            public void onResponse(Call<PlaceReponse> call, Response<PlaceReponse> response) {
                Log.d(TAG, "onResponse: getDataPlaceIdMenu: "+response.toString());
                ArrayList<Place>arrayList = (ArrayList<Place>) response.body().getData();
                dialog.dismiss();
                context.addListMarkerByMenuMap(arrayList);
            }

            @Override
            public void onFailure(Call<PlaceReponse> call, Throwable t) {
                Log.d(TAG, "onFailure: getDataPlaceIdMenu: "+t.toString());
                Toast.makeText(context, t.toString(), Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Viewhodler extends  RecyclerView.ViewHolder{
        private ImageView imgIconMenu;
        private TextView txtTitileMenu;
        private CardView cardViewMenu;
        public Viewhodler(@NonNull View itemView) {
            super(itemView);
            imgIconMenu = itemView.findViewById(R.id.imgIconMenu);
            txtTitileMenu = itemView.findViewById(R.id.txtTitileMenu);
            cardViewMenu = itemView.findViewById(R.id.cardViewMenu);
        }
    }
}
