package com.example.myapplication.src.Adapter.fragment_home_adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.myapplication.R;
import com.example.myapplication.models.place_reponse.Place;
import com.example.myapplication.src.Activity.DetailActivity;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;

public class AdapterBanerMain extends PagerAdapter {
    private RoundedImageView mImgBannerMain;
    private Context context;
    private ArrayList<Place>arrayList;
    private int layout;

    public AdapterBanerMain(Context context,ArrayList<Place>arrayList,int Layout) {
        this.context = context;
        this.arrayList = arrayList;
        this.layout = Layout;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        Collections.shuffle(Collections.singletonList(arrayList));
        View view = View.inflate(context,layout,null);
        mImgBannerMain = view.findViewById(R.id.imgBanerMain);
        mImgBannerMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(arrayList.get(position).getId()!= null){
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("id",arrayList.get(position).getId());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            }
        });
        Picasso.with(context).load(arrayList.get(position).getImage()).into(mImgBannerMain);
        container.addView(view);
        return view;
    }
}
