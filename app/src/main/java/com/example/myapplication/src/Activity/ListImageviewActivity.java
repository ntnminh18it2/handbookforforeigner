package com.example.myapplication.src.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.myapplication.R;

public class ListImageviewActivity extends AppCompatActivity {
    private RecyclerView mRecyclerviewListImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_imageview);
        init();
       // getData();
    }

    private void init() {
        mRecyclerviewListImage = findViewById(R.id.recyclerviewListImage);
        mRecyclerviewListImage.setHasFixedSize(true);
        mRecyclerviewListImage.setLayoutManager(new GridLayoutManager(getApplicationContext(),3));
    }

//    private void getData() {
//        DataService dataService = APIServices.getService();
//        Call<List<Place>>callback = dataService.getDataImageAll();
//        callback.enqueue(new Callback<List<Place>>() {
//            @Override
//            public void onResponse(Call<List<Place>> call, Response<List<Place>> response) {
//                Log.d("AAA","getDataImageAll: "+response.toString());
//                if(response.isSuccessful()){
//                    ArrayList<Place>arrayList = (ArrayList<Place>) response.body();
//                    if(arrayList.size() >0){
//                        Collections.shuffle(arrayList);
//                        AdapterRecyclerviewImage adapter = new AdapterRecyclerviewImage(getBaseContext(),R.layout.item_image_homepage,arrayList);
//                        mRecyclerviewListImage.setAdapter(adapter);
//                        adapter.notifyDataSetChanged();
//                    }
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Place>> call, Throwable t) {
//                Log.d("AAA","errGetDataImageAll: "+t.toString());
//            }
//        });
//    }
}
