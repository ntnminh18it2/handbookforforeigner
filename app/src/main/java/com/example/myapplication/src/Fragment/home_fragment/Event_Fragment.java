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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.models.event_reponse.Event;
import com.example.myapplication.models.event_reponse.EventReponse;
import com.example.myapplication.src.Adapter.fragment_home_adapter.AdapterRecyclerviewEvent;
import com.example.myapplication.services.APIServices;
import com.example.myapplication.services.DataService;

import java.util.ArrayList;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Event_Fragment extends Fragment {
    private View mView;
    private TextView mTxtEventHomePage;
    private RecyclerView mRecyclerviewEventHompage;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.layout_all,container,false);
        init();
        getDataEvent();
        return mView;
    }

    private void getDataEvent() {
        DataService dataService = APIServices.getService();
        Call<EventReponse> callback = dataService.getDataEventRanDom();
        callback.enqueue(new Callback<EventReponse>() {
            @Override
            public void onResponse(Call<EventReponse> call, Response<EventReponse> response) {
                Log.d("AAA","getDataEventRanDom: "+response.toString());
                if(response.isSuccessful()){
                    ArrayList<Event>arrayEvent = (ArrayList<Event>) response.body().getData();
                    if(arrayEvent.size() > 0){
                        Collections.shuffle(arrayEvent);
                        setItemRecyclerviewEventHomepage(arrayEvent);
                    }
                }
            }

            @Override
            public void onFailure(Call<EventReponse> call, Throwable t) {
                Log.d("AAA","errGetDataEventRanDom: "+t.toString());
            }
        });
    }

    private void setItemRecyclerviewEventHomepage(ArrayList<Event>arrayEvent) {
        AdapterRecyclerviewEvent adapter = new AdapterRecyclerviewEvent(mView.getContext(),R.layout.item_event_homepage,arrayEvent);
        mRecyclerviewEventHompage.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void init() {
        mTxtEventHomePage = mView.findViewById(R.id.txtTitleImageHomePage);
        mTxtEventHomePage.setPaintFlags(mTxtEventHomePage.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        mTxtEventHomePage.setText("Event");

        mRecyclerviewEventHompage = mView.findViewById(R.id.recyclerviewImageHompage);
        mRecyclerviewEventHompage.setHasFixedSize(true);
        mRecyclerviewEventHompage.setNestedScrollingEnabled(false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mView.getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerviewEventHompage.setLayoutManager(layoutManager);
    }
}
