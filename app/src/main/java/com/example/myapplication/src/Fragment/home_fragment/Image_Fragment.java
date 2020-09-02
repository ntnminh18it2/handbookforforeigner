package com.example.myapplication.src.Fragment.home_fragment;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import com.example.myapplication.src.Activity.ListImageviewActivity;

public class Image_Fragment extends Fragment {
    private View mView;
    private TextView mTxtTitleImageHomePage;
    private RecyclerView mRecyclerviewImageHompage;
    private TextView mTxtSeemore;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.layout_all,container,false);
        init();
      //  getDataImageRandom();
        listenerSeemore();
        return mView;
    }

    private void listenerSeemore() {
        mTxtSeemore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ListImageviewActivity.class));
                ((AppCompatActivity)mView.getContext()).overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_ride);

            }
        });
    }

//    private void getDataImageRandom() {
//        DataService dataService = APIServices.getService();
//        Call<List<Place>>callback = dataService.getDataImageRandom();
//        callback.enqueue(new Callback<List<Place>>() {
//            @Override
//            public void onResponse(Call<List<Place>> call, Response<List<Place>> response) {
//                Log.d("AAA","getDataImageHomepage: "+response.toString());
//                if(response.isSuccessful()){
//                    ArrayList<Place>arrayList = (ArrayList<Place>) response.body();
//                    if(arrayList.size()>0){
//                        AdapterRecyclerviewImage adapter = new AdapterRecyclerviewImage(mView.getContext(),R.layout.item_image_homepage,
//                                arrayList);
//                        mRecyclerviewImageHompage.setHasFixedSize(true);
//                        StaggeredGridLayoutManager gridLayoutManager =
//                                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL);
//                        mRecyclerviewImageHompage.setLayoutManager(gridLayoutManager);
//                        mRecyclerviewImageHompage.setAdapter(adapter);
//                        adapter.notifyDataSetChanged();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Place>> call, Throwable t) {
//
//            }
//        });
//    }

    private void init() {
        mTxtSeemore = mView.findViewById(R.id.txtSeemore);
        mTxtTitleImageHomePage = mView.findViewById(R.id.txtTitleImageHomePage);
        mTxtTitleImageHomePage.setText("Imageview");
        mTxtTitleImageHomePage.setPaintFlags(mTxtTitleImageHomePage.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        mRecyclerviewImageHompage = mView.findViewById(R.id.recyclerviewImageHompage);
        mRecyclerviewImageHompage.setNestedScrollingEnabled(false);
    }
}
