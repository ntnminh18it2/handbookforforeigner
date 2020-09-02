package com.example.myapplication.src.Fragment.home_fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.R;
import com.example.myapplication.models.place_reponse.Place;
import com.example.myapplication.src.Adapter.fragment_home_adapter.AdapterBanerMain;
import com.rd.PageIndicatorView;

import java.util.ArrayList;

public class Banner_Fragment extends Fragment {
    private Handler handler;
    private Runnable runnable;
    private int mCurrenItem;
    private ViewPager mViewpagerBanerMain;
    private View mView;
    private PageIndicatorView mPageIndicatorViewBanner;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        init(inflater,container);
        autoSlideViewPager();
        return mView;
    }

    private void autoSlideViewPager() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                mCurrenItem = mViewpagerBanerMain.getCurrentItem();
                mCurrenItem++;
                if(mCurrenItem >= mViewpagerBanerMain.getAdapter().getCount()){
                    mCurrenItem = 0;
                }
                mViewpagerBanerMain.setCurrentItem(mCurrenItem,true);
                handler.postDelayed(runnable,4500);
            }
        };
        handler.postDelayed(runnable,4500);
    }

    private void init(LayoutInflater inflater,ViewGroup container) {
        mView = inflater.inflate(R.layout.banner_fragment,container,false);
        mViewpagerBanerMain = mView.findViewById(R.id.viewpagerBanerMain);
        mPageIndicatorViewBanner = mView.findViewById(R.id.pageIndicatorViewBanner);
        ArrayList<Place>arrayList = new ArrayList<>();
        arrayList.add(new Place());
        arrayList.add(new Place());
        arrayList.add(new Place());
        arrayList.add(new Place());
        arrayList.add(new Place());
        arrayList.get(0).setImage("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTpETZG-kfA0wK3S6abltjyKPVgSr7Ew84-o3Ho5chOk7rziPxY&usqp=CAU");
        arrayList.get(1).setImage("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQ0KpUTfRWeotxNn7SthRTYS46TurKtY3ThFMLIurKrvOf3XRqE&usqp=CAU");
        arrayList.get(2).setImage("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRKqTrNehgGRlz7n4DDVZU2RHx4a0B_1_sMOFmxOstyufwTZDC_&usqp=CAU");
        arrayList.get(3).setImage("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRg6F4deahjiwyEWVKxON3uFfcUmvR9YSVHtYWMP9RVXAW3q1R2&usqp=CAU");
        arrayList.get(4).setImage("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTDXdB55V0aTHiPFDzXEymorKilOmWn6VTLoa7xFjmiUFIxPN9W&usqp=CAU");
        AdapterBanerMain adapter = new AdapterBanerMain(mView.getContext(),arrayList,R.layout.layout_baner_homepage);
        mViewpagerBanerMain.setAdapter(adapter);
        mPageIndicatorViewBanner.setViewPager(mViewpagerBanerMain);
    }
}
