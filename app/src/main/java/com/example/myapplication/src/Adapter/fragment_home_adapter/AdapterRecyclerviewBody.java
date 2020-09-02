package com.example.myapplication.src.Adapter.fragment_home_adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.models.menu_reponse.Menu;
import com.example.myapplication.models.place_reponse.Place;
import com.example.myapplication.models.place_reponse.PlaceReponse;
import com.example.myapplication.src.Activity.ListPlaceActivity;
import com.example.myapplication.src.Activity.PlaceActivity;
import com.example.myapplication.services.APIServices;
import com.example.myapplication.services.DataService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterRecyclerviewBody extends RecyclerView.Adapter<AdapterRecyclerviewBody.Viewhodler> {
    private ArrayList<Menu>mArrayList;
    private Context mContext;
    private int mLayout;
    private View mView;
    private int mCheck;

    public AdapterRecyclerviewBody(ArrayList<Menu> mArrayList, Context mContext, int mLayout,int mCheck) {
        this.mArrayList = mArrayList;
        this.mContext = mContext;
        this.mLayout = mLayout;
        this.mCheck = mCheck;
    }

    @NonNull
    @Override
    public Viewhodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         mView = View.inflate(mContext,mLayout,null);
        return new Viewhodler(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewhodler holder, final int position) {
        holder.mTxt.setText(mArrayList.get(position).getName());
        holder.mTxt.setPaintFlags(holder.mTxt.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        getDataItem(holder.mRecyclerview,mArrayList.get(position).getId());
        holder.mTxtSeemore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCheck == 0){
                    Intent intent = new Intent(mContext, PlaceActivity.class);
                    intent.putExtra("id",mArrayList.get(position).getId());
                    intent.putExtra("name",mArrayList.get(position).getName());
                    mContext.startActivity(intent);
                    ((AppCompatActivity)mContext).overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_ride);

                }else {
                    Intent intent = new Intent(mContext, ListPlaceActivity.class);
                    intent.putExtra("id",mArrayList.get(position).getId());
                    intent.putExtra("name",mArrayList.get(position).getName());
                    mContext.startActivity(intent);
                    ((AppCompatActivity)mContext).overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_ride);
                }
            }
        });
    }

    private void getDataItem(final RecyclerView recyclerView, int id) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        LinearLayoutManager linearLayout = new LinearLayoutManager(mContext);
        linearLayout.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayout);

        DataService dataService = APIServices.getService();
        Call<PlaceReponse>callback = dataService.getDataPlaceHomeRandom(id,mCheck);
        callback.enqueue(new Callback<PlaceReponse>() {
            @Override
            public void onResponse(Call<PlaceReponse> call, Response<PlaceReponse> response) {
                Log.d("AAA","getDataPlaceHomeRandom: "+response.toString());
                if(response.isSuccessful()){
                    ArrayList<Place>arrayList =  (ArrayList) response.body().getData();
                    if(arrayList.size() >0 ){
                        ItemAdapterRycyclerview adapter = new ItemAdapterRycyclerview(arrayList,mContext,R.layout.item__place_homepage);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<PlaceReponse> call, Throwable t) {
                Log.d("AAA","errGetDataPlaceHomeRandom: "+t.toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    public class Viewhodler extends RecyclerView.ViewHolder{
        private TextView mTxt;
        private TextView mTxtSeemore;
        private RecyclerView mRecyclerview;
        public Viewhodler(@NonNull View itemView) {
            super(itemView);
            mTxt = itemView.findViewById(R.id.txtTitleImageHomePage);
            mTxtSeemore = itemView.findViewById(R.id.txtSeemore);
            mRecyclerview = itemView.findViewById(R.id.recyclerviewImageHompage);
        }
    }
}
