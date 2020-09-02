package com.example.myapplication.src.Fragment.home_fragment;

import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.models.menu_reponse.Menu;
import com.example.myapplication.models.menu_reponse.MenuReponse;
import com.example.myapplication.src.Adapter.fragment_home_adapter.AdapterRecyclerviewUtilities;
import com.example.myapplication.services.APIServices;
import com.example.myapplication.services.DataService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Utilities_Fragment extends Fragment {
    private TextView txt;
    private View mView;
    private RecyclerView mRecyclerview;
    private TextView txtSeemore;
    private boolean check;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.layout_all,container,false);
        init();
        getDataUtilities();
        return mView;
    }

    private void getDataUtilities() {
        DataService dataService = APIServices.getService();
        Call<MenuReponse> callback = dataService.getDataMenuBottom();
        callback.enqueue(new Callback<MenuReponse>() {
            @Override
            public void onResponse(Call<MenuReponse> call, Response<MenuReponse> response) {
                Log.d("AAA","getDataMenuBottom: "+response.toString());
                if(response.isSuccessful()){
                    Log.d("AAA","getDataMenuBottom: "+response.body().getMessage());
                    List<Menu>list = response.body().getData();
                    Collections.shuffle(list);
                    setItemRecyclerviewUtiliesFragment((ArrayList)list);
                }
            }

            @Override
            public void onFailure(Call<MenuReponse> call, Throwable t) {
                Log.d("AAA","errGetDataMenuBottom: "+t.toString());
            }
        });
    }

    private void setItemRecyclerviewUtiliesFragment(final ArrayList<Menu> arrayList) {
        check = false;
        AdapterRecyclerviewUtilities adapter = new AdapterRecyclerviewUtilities(arrayList,mView.getContext(),
                R.layout.item_utilities_homepage,6);
        mRecyclerview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        txtSeemore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check = !check;
                if(check == false){
                    AdapterRecyclerviewUtilities adapter = new AdapterRecyclerviewUtilities(arrayList,mView.getContext(),
                            R.layout.item_utilities_homepage,6);
                    mRecyclerview.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }else{
                    txtSeemore.setText("collapse");
                    AdapterRecyclerviewUtilities adapter = new AdapterRecyclerviewUtilities(arrayList,mView.getContext(),
                            R.layout.item_utilities_homepage,arrayList.size());
                    mRecyclerview.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void init() {
        txtSeemore = mView.findViewById(R.id.txtSeemore);
        txt = mView.findViewById(R.id.txtTitleImageHomePage);
        txt.setPaintFlags(txt.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        txt.setText("Utilities");
        mRecyclerview = mView.findViewById(R.id.recyclerviewImageHompage);
        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setLayoutManager(new GridLayoutManager(mView.getContext(),3));
        mRecyclerview.setNestedScrollingEnabled(false);
    }
}
