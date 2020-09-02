package com.example.myapplication.src.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.models.place_reponse.Place;
import com.example.myapplication.models.place_reponse.PlaceReponse;
import com.example.myapplication.src.Adapter.fragment_home_adapter.AdapterBanerMain;
import com.example.myapplication.src.Fragment.Place_FragmentActivity;

import com.example.myapplication.services.APIServices;
import com.example.myapplication.services.DataService;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.rd.PageIndicatorView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaceActivity extends AppCompatActivity {
    private RecyclerView mRecyclerview;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Handler mHandler;
    private Runnable mRunnable;
    private int CurrentItem;
    private ViewPager mViewpager;
    private PageIndicatorView mPageIndicatorView;
    private TextView mTxtTitlePlace;
    private RelativeLayout mRelativeLayoutPlace;
    private AppBarLayout mAppBarLayoutPlace;
    private ImageView mImgBackPlace;
    private ArrayList<Place>arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);
        init();

        Intent intent = getIntent();
        if(intent != null){
           if(intent.hasExtra("id")){
               int id = intent.getIntExtra("id",012);
               String name = intent.getStringExtra("name");
               mTxtTitlePlace.setText(name);
               getDataPlaceHomeRandom(id);

               FragmentManager fragmentManager = getSupportFragmentManager();
               FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
               Place_FragmentActivity fragmentActivity = new Place_FragmentActivity(id);
               fragmentTransaction.add(R.id.liner10,fragmentActivity);
               fragmentTransaction.commit();
           }
        }
    }

    private void getDataPlaceHomeRandom(int id) {
        DataService dataService = APIServices.getService();
        Call<PlaceReponse>callback = dataService.getDataPlaceHomeRandom(id,0);
        callback.enqueue(new Callback<PlaceReponse>() {
            @Override
            public void onResponse(Call<PlaceReponse> call, Response<PlaceReponse> response) {
                Log.d("AAA","getDataPlaceHomeRandom: "+response.toString());
                if(response.isSuccessful()){
                    arrayList = (ArrayList) response.body().getData();
                    if(arrayList.size() >0){
                        ArrayList<Place>arrayImage = new ArrayList<>();
                        int dem=0;
                        for(Place place : arrayList){
                            dem++;
                            arrayImage.add(place);
                            if(dem == 5){
                                break;
                            }
                        }
                        AdapterBanerMain adapterBanerMain = new AdapterBanerMain(getApplicationContext(),arrayImage,R.layout.layout_baner_placeactivity);
                        mViewpager.setAdapter(adapterBanerMain);
                        autoSlide(mViewpager,arrayList);
                        mPageIndicatorView.setViewPager(mViewpager);
                        mPageIndicatorView.setCount(arrayImage.size());
                    }

                }
            }

            @Override
            public void onFailure(Call<PlaceReponse> call, Throwable t) {
                Log.d("AAA","errGetDataPlaceHomeRandom: "+t.toString());
            }
        });

    }


    private void autoSlide(final ViewPager viewPager, final ArrayList<Place>arrayList) {
        mHandler = new Handler();
        mRunnable = new Runnable() {
            @Override
            public void run() {
                CurrentItem = viewPager.getCurrentItem();
                CurrentItem++;
                if(CurrentItem >= viewPager.getAdapter().getCount()){
                    CurrentItem = 0;
                }
                collapsingToolbarLayout.setTitle(arrayList.get(CurrentItem).getName());
                viewPager.setCurrentItem(CurrentItem,true);
                mHandler.postDelayed(mRunnable,4500);
            }
        };
        mHandler.postDelayed(mRunnable,4500);
    }

    private void init() {
        mRelativeLayoutPlace = findViewById(R.id.relativeLayoutPlace);
        mTxtTitlePlace = findViewById(R.id.txtTitlePlace);
        mViewpager = findViewById(R.id.viewPagerPlace);
        mPageIndicatorView = findViewById(R.id.pageIndicatorView);

        collapsingToolbarLayout = findViewById(R.id.collapsingtoolbarlayoutPlace);
        collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.white));
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.white));

        LinearLayout linearLayout = findViewById(R.id.txtbackground);
        animationLinerlayout(linearLayout);

        mAppBarLayoutPlace = findViewById(R.id.appBarLayoutPlace);
        listennerAppbarLayout(mAppBarLayoutPlace);

        mImgBackPlace = findViewById(R.id.imgBackPlace);
        listennerBack();
    }

    private void listennerBack() {
        mImgBackPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void animationLinerlayout(final LinearLayout linearLayout){
        final float alpha = (float) 0.7;
        new CountDownTimer(300,1) {
            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                linearLayout.animate()
                        .alpha(alpha)
                        .setDuration(500).start();
            }
        }.start();
    }
    private void listennerAppbarLayout(final AppBarLayout mAppBarLayout) {
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.BaseOnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                if(Math.abs(i) >= appBarLayout.getTotalScrollRange()){
                    mRelativeLayoutPlace.setVisibility(View.GONE);
                }else {
                    mRelativeLayoutPlace.setVisibility(View.VISIBLE);

                }
            }
        });
    }

}
