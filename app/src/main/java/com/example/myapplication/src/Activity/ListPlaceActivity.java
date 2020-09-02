package com.example.myapplication.src.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.models.place_reponse.Place;
import com.example.myapplication.models.place_reponse.PlaceReponse;
import com.example.myapplication.src.Adapter.AdapterLisplace;
import com.example.myapplication.services.APIServices;
import com.example.myapplication.services.DataService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListPlaceActivity extends AppCompatActivity {
    private TextView mTxtTitleListPlace;
    private RecyclerView mRecyclerviewListPlace;
    private TextView mTxtbackgroundListPlace;
    private TextView mTxtName;
    private ImageView mImgListPlace;
    private Handler handler;
    private Runnable runnable;
    private int Currentitem = 0;
    private ImageView mImgBackDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_place);
        Intent intent = getIntent();
        init();
        listenerBlack();
        if(intent != null){
            if(intent.hasExtra("id") && intent.hasExtra("name")){
                int id = intent.getIntExtra("id",012);
                String name = intent.getStringExtra("name");
                mTxtTitleListPlace.setText(name);
                getDataPlaceIdIngredient(id);
            }
        }
    }

    private void listenerBlack() {
        mImgBackDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void init() {
        mImgBackDetail = findViewById(R.id.imgBackDetail);
        mTxtTitleListPlace = findViewById(R.id.txtTitleListPlace);
        mRecyclerviewListPlace = findViewById(R.id.recyclerviewListPlace);
        mRecyclerviewListPlace.setHasFixedSize(true);
        mRecyclerviewListPlace.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));
        mRecyclerviewListPlace.setNestedScrollingEnabled(false);

        mTxtbackgroundListPlace = findViewById(R.id.txtbackgroundListPlace);
        animationTextviewBackground(mTxtbackgroundListPlace);
        mTxtName = findViewById(R.id.txtNameListPlace);
        mImgListPlace = findViewById(R.id.imgListPlace);
    }

    private void animationTextviewBackground(final TextView mTxtbackgroundListPlace) {
        final float alpha = (float) 0.6;
        new CountDownTimer(300,1){
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                mTxtbackgroundListPlace.animate().alpha(alpha).setDuration(300).start();
            }
        }.start();
    }

    private void getDataPlaceIdIngredient(int id) {
        DataService dataService = APIServices.getService();
        Call<PlaceReponse>callback = dataService.getDataPlaceIdIngredient(id);
        callback.enqueue(new Callback<PlaceReponse>() {
            @Override
            public void onResponse(Call<PlaceReponse> call, Response<PlaceReponse> response) {
                Log.d("AAA","getDataPlaceIdIngredient: "+response.toString());
                if(response.isSuccessful()){
                    ArrayList<Place>arrayList = (ArrayList) response.body().getData();
                   if(arrayList.size() > 0){
                       Collections.shuffle(arrayList);
                       AdapterLisplace adapter = new AdapterLisplace(arrayList,ListPlaceActivity.this,R.layout.item_listplace);
                       mRecyclerviewListPlace.setAdapter(adapter);
                       setHeader(arrayList);
                   }
                }
            }

            @Override
            public void onFailure(Call<PlaceReponse> call, Throwable t) {
                Log.d("AAA","errGetDataPlaceIdIngredient: "+t.toString());
            }
        });
    }

    private void setHeader(final ArrayList<Place> arrayList) {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                mTxtName.setText(arrayList.get(Currentitem).getName());
                Picasso.with(getApplicationContext()).load(arrayList.get(Currentitem).getImage()).into(mImgListPlace);
                onClickedImgBaner(arrayList.get(Currentitem));
                Currentitem++;
                if(Currentitem >= arrayList.size()-1){
                    Currentitem = 0;
                }
                handler.postDelayed(runnable,4500);
            }
        };
        handler.postDelayed(runnable,4500);
    }
    void onClickedImgBaner(final Place place){
        mImgListPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra("id",place.getId());
                intent.putExtra("name",place.getName());
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_ride);
            }
        });
    }
}
