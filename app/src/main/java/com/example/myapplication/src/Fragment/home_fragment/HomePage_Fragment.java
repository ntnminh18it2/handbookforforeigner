package com.example.myapplication.src.Fragment.home_fragment;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.models.menu_reponse.Menu;
import com.example.myapplication.models.menu_reponse.MenuReponse;
import com.example.myapplication.services.APIServices;
import com.example.myapplication.services.DataService;
import com.example.myapplication.src.Adapter.fragment_home_adapter.AdapterRecyclerviewMenu;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePage_Fragment extends Fragment {
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private View mView;
    private RecyclerView mRecyclerviewMenuHomePage;
    private AppBarLayout mAppBarLayout;
    private  RelativeLayout mRelativeLayout;
    private DrawerLayout drawerlayout;
    private ImageView imgDrawer,imgSearch;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         mView = inflater.inflate(R.layout.home_page_fragment,container,false);
         init();
         getDataMenuTop();
         onClicked();
         openDrawer();
        return mView;
    }

    private void openDrawer() {
        drawerlayout = mView.findViewById(R.id.drawerlayout);
        imgDrawer = mView.findViewById(R.id.imgDrawer);
        imgDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerlayout.openDrawer(GravityCompat.START);
            }
        });
    }


    private void onClicked() {

    }


    private void getDataMenuTop() {
        DataService dataService = APIServices.getService();
        Call<MenuReponse> callback = dataService.getDataMenuTop();
        callback.enqueue(new Callback<MenuReponse>() {
            @Override
            public void onResponse(Call<MenuReponse> call, Response<MenuReponse> response) {
                Log.d("AAA","getDataMenuTop"+ response.toString());
                if(response.isSuccessful()){
                    ArrayList<Menu> arrayMenuTop = (ArrayList<Menu>) response.body().getData();
                    if(arrayMenuTop.size() > 0 ){
                        Collections.shuffle(arrayMenuTop);
                        AdapterRecyclerviewMenu adapter = new AdapterRecyclerviewMenu(mView.getContext(),arrayMenuTop,
                                R.layout.item_menu_homepage);
                        setRecyclerView();
                        mRecyclerviewMenuHomePage.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<MenuReponse> call, Throwable t) {
                Log.d("AAA","errGetDataMenuTop" + t.toString());
            }
        });
    }

    private void setRecyclerView(){
        mRecyclerviewMenuHomePage = mView.findViewById(R.id.recyclerviewMenuHomePage);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mView.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerviewMenuHomePage.setLayoutManager(linearLayoutManager);
        mRecyclerviewMenuHomePage.setHasFixedSize(true);
        mRecyclerviewMenuHomePage.setItemViewCacheSize(20);
    }

    private void init() {
        mCollapsingToolbarLayout = mView.findViewById(R.id.collapsingtoolbarlayout);
        //Expanded set màu mở rộng

        mCollapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.white));
        //set màu thu hẹp
        mCollapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.white));
        mCollapsingToolbarLayout.setCollapsedTitleGravity(Gravity.CENTER);
        mRelativeLayout = mView.findViewById(R.id.relativeLayout);

        mAppBarLayout = mView.findViewById(R.id.appBarLayout);
        LinearLayout linearLayout = mView.findViewById(R.id.linerLayout);
        animationLinerlayout(linearLayout);
        listennerAppbarLayout(mAppBarLayout);
        animationAppBarDown(mAppBarLayout);

    }

    private void listennerAppbarLayout(final AppBarLayout mAppBarLayout) {
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.BaseOnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                if(Math.abs(i) >= appBarLayout.getTotalScrollRange()){
                    mRelativeLayout.setVisibility(View.GONE);
                    mAppBarLayout.setBackgroundColor(getResources().getColor(R.color.blue));

                }else {
                    mRelativeLayout.setVisibility(View.VISIBLE);
                    mAppBarLayout.setBackgroundColor(getResources().getColor(R.color.colortransfrom));

                }
            }
        });
    }

    private void animationAppBarDown(final AppBarLayout appBar){
        new CountDownTimer(2000, 1) {
            public void onTick(long millisUntilFinished) {
                appBar.setTranslationY(-appBar.getHeight());
            }

            public void onFinish() {
                appBar.animate()
                        .translationY(0)
                        .setDuration(2000).start();
            }
        }.start();
    }
    private void animationLinerlayout(final LinearLayout linearLayout){
        new CountDownTimer(1000, 1) {
            public void onTick(long millisUntilFinished) {
                linearLayout.setTranslationY(linearLayout.getHeight());
            }

            public void onFinish() {
                linearLayout.animate()
                        .translationY(0)
                        .setDuration(4000).start();
            }
        }.start();
    }

}
