package com.example.myapplication.src.Fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.models.place_reponse.Place;
import com.example.myapplication.models.place_reponse.PlaceReponse;
import com.example.myapplication.services.APIServices;
import com.example.myapplication.services.DataService;
import com.example.myapplication.src.Adapter.AdapterLisplace;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Search_Fragment extends Fragment {
    private View mView;
    private EditText edttimkim;
    private RecyclerView mRecyclerviewListPlace;
    private String text = "a";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_search,container,false);
        anhxa();
        getDataSearch(text);
        listenerSearch();
        return mView;
    }

    private void listenerSearch() {
        edttimkim.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                text = edttimkim.getText().toString();
                getDataSearch(text);
            }
        });
    }
    private void getDataSearch(String text){
        DataService dataService = APIServices.getService();
        Call<PlaceReponse>call = dataService.getDataPlaceStrSearch(text);
        call.enqueue(new Callback<PlaceReponse>() {
            @Override
            public void onResponse(Call<PlaceReponse> call, Response<PlaceReponse> response) {
                Log.d("AAA","search: "+response.toString());
                if(response.isSuccessful()){
                    ArrayList<Place>arrayList = (ArrayList<Place>) response.body().getData();
                    AdapterLisplace adapter = new AdapterLisplace(arrayList, mView.getContext(),R.layout.item_listplace);
                    mRecyclerviewListPlace.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<PlaceReponse> call, Throwable t) {
                Log.d("AAA","errSearch: "+t.toString());
            }
        });
    }

    private void anhxa() {
        mRecyclerviewListPlace = mView.findViewById(R.id.recyclerviewListSearch);
        mRecyclerviewListPlace.setHasFixedSize(true);
        mRecyclerviewListPlace.setLayoutManager(new GridLayoutManager(mView.getContext(),1));
        mRecyclerviewListPlace.setNestedScrollingEnabled(false);

        edttimkim = mView.findViewById(R.id.edttimkim);
    }

}
