package com.example.myapplication.src.Fragment;

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
import com.example.myapplication.src.Adapter.fragment_home_adapter.AdapterRecyclerviewBody;
import com.example.myapplication.services.APIServices;
import com.example.myapplication.services.DataService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Place_FragmentActivity extends Fragment {
    private RecyclerView mRecyclerviewPlaceActivity;
    private View mView;
    private int id;

    public Place_FragmentActivity(int id) {
        this.id = id;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       mView = inflater.inflate(R.layout.layout_place_activity_fragment,container,false);
       init();
       getData(id);
        return mView;
    }

    private void init() {
        mRecyclerviewPlaceActivity = mView.findViewById(R.id.recyclerviewPlaceActivity);
        mRecyclerviewPlaceActivity.setHasFixedSize(true);
        mRecyclerviewPlaceActivity.setLayoutManager(new GridLayoutManager(mView.getContext(),1));
        mRecyclerviewPlaceActivity.setNestedScrollingEnabled(false);
    }
    private void getData(int id) {
        DataService dataService = APIServices.getService();
        Call<MenuReponse> call = dataService.getDataIngredientIdMenu(id);
        call.enqueue(new Callback<MenuReponse>() {
            @Override
            public void onResponse(Call<MenuReponse> call, Response<MenuReponse> response) {
                Log.d("AAA","getDataPlaceActivity: "+response.toString());
                if(response.isSuccessful()){
                    ArrayList<Menu>arrayList = (ArrayList) response.body().getData();
                    if(arrayList.size() > 0){
                        AdapterRecyclerviewBody adapter = new AdapterRecyclerviewBody(arrayList,mView.getContext(),
                                R.layout.layout_all,1);
                        mRecyclerviewPlaceActivity.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<MenuReponse> call, Throwable t) {
                Log.d("AAA","errGetDataPlaceActivity: "+t.toString());

            }
        });
    }
}
