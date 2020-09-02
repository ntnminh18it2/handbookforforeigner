package com.example.myapplication.src.Fragment.home_fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.models.menu_reponse.Menu;
import com.example.myapplication.models.menu_reponse.MenuReponse;
import com.example.myapplication.services.APIServices;
import com.example.myapplication.services.DataService;
import com.example.myapplication.src.Adapter.fragment_home_adapter.AdapterRecyclerviewBody;

import java.util.ArrayList;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Place_Fragment extends Fragment {
    private View mView;
    private RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.layout_place_activity_fragment,container,false);
        init();
        getDataMenuTop();
        return mView;
    }

    private void init() {
        mRecyclerView = mView.findViewById(R.id.recyclerviewPlaceActivity);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(mView.getContext(),1));
        mRecyclerView.setNestedScrollingEnabled(false);
    }
    private void getDataMenuTop() {
        DataService dataService = APIServices.getService();
        Call<MenuReponse> callback = dataService.getDataMenuTop();
        callback.enqueue(new Callback<MenuReponse>() {
            @Override
            public void onResponse(Call<MenuReponse> call, Response<MenuReponse> response) {
                Log.d("AAA","getDataMenuTop"+ response.toString());
                if(response.isSuccessful()){
                      ArrayList<Menu>arrayList = (ArrayList<Menu>) response.body().getData();
                    if(arrayList.size() > 0 ){
                        Collections.shuffle(arrayList);
                        AdapterRecyclerviewBody adapter = new AdapterRecyclerviewBody(arrayList,mView.getContext(),
                                R.layout.layout_all,0);
                        mRecyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<MenuReponse> call, Throwable t) {
                Log.d("AAA","ErrGetDataMenuPlace"+ t.toString());
            }
        });
    }
}
