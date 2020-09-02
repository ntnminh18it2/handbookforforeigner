package com.example.myapplication.src.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.models.details_reponse.DataEvaluate;
import com.example.myapplication.models.details_reponse.DetailsReponse;
import com.example.myapplication.models.place_reponse.Place;
import com.example.myapplication.services.APIServices;
import com.example.myapplication.services.DataService;
import com.example.myapplication.src.Adapter.AdapterRecyclerviewComment;
import com.example.myapplication.src.dialog.EvaluateDialog;
import com.example.myapplication.src.dialog.LoadingDialog;
import com.example.myapplication.src.Adapter.AdapterRecyclerviewImage;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    private ImageView mImgBackdetail,imgEvaluate,imgLikeEvaluate,imgMapDetail;
    private TextView mTxtOverView,txtNameDetail,txtRating,txtLike,titleEvaluate,txtMediumRating,mtxtIntroduce,
            mTitleIntroduce,mTxtMapDetail,mTxtTitleOverviewDetail,mTxtTitleImageviewDetail,txtSeemoreComment;
    private RatingBar ratingMedium,ratingBar;
    private RecyclerView mRecyclerviewImageDetail,recyclerviewComment;

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private static final String TAG = "DetailActivity";
    private static final int ERR_DIALOG_REQUEST = 9001;
    private boolean check = false;
    private ArrayList<DataEvaluate>arrayListEvaluate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        init();
        listenerBlack();
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("id")) {
                int id = intent.getIntExtra("id", 012);
                getData(id);
                setOnclicked(id);
            }
        }
    }

    private void setOnclicked(final int id) {
        imgMapDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isServicesOk()) {
                    startActivity(new Intent(getApplicationContext(), MapActivity.class));
                }
            }
        });

        imgEvaluate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MainActivity.checkUserAcount()){
                    EvaluateDialog evaluateDialog = new EvaluateDialog(id);
                    evaluateDialog.show(fragmentManager,"123");
                }else{
                    startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                }
            }
        });

        txtSeemoreComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number = 0;
                check = !check;
                if (check == true) {
                    txtSeemoreComment.setText("Collapse");
                    AdapterRecyclerviewComment adapter = new AdapterRecyclerviewComment(arrayListEvaluate, DetailActivity.this,
                            R.layout.layout_comment_details,arrayListEvaluate.size());
                    adapter.notifyDataSetChanged();
                } else {
                    txtSeemoreComment.setText("Seemore");
                    if(arrayListEvaluate.size() > 6) {
                        number = 6;
                    }else{
                        number = arrayListEvaluate.size();
                        AdapterRecyclerviewComment adapter = new AdapterRecyclerviewComment(arrayListEvaluate, DetailActivity.this,
                                R.layout.layout_comment_details,number);
                        adapter.notifyDataSetChanged();
                    }

                }
                if(arrayListEvaluate != null){

                }
            }
        });

        imgLikeEvaluate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if (!MainActivity.checkUserAcount()) {
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                } else {
                    int idUser = MainActivity.sharedPreferences.getInt("idUser", 0);
                    final LoadingDialog loadingDialog = new LoadingDialog();
                    loadingDialog.show(fragmentManager, "123");
                    if (idUser != 0) {
                        DataService dataService = APIServices.getService();
                        Call<String> callback = dataService.postLikePlace(idUser, id, "null",
                                0, 1);
                        callback.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                if (response.isSuccessful()) {
                                    Log.d("AAA", "postLikePlace: " + response.toString());
                                    Toast.makeText(getApplicationContext(), "Sussces", Toast.LENGTH_SHORT).show();
                                    loadingDialog.dismiss();
                                    finish();
                                    startActivity(getIntent());
                                }
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                                Log.d("AAA", "errPostLikePlace: " + t.toString());
                                Toast.makeText(view.getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                                loadingDialog.dismiss();
                            }
                        });
                    }
                }
            }
        });
    }

    private void setDataEvaluate(ArrayList<DataEvaluate> arrayListEvaluate) {
        if(arrayListEvaluate.size()>0){
            Map<String,Integer> map = numberRatingPlaceIdPlace(arrayListEvaluate);
            Log.d("AAA","totalrating: "+map.get("totalLike"));
            txtRating.setText(map.get("totalRating")+" rating");
            txtLike.setText(map.get("totalLike")+" like");
            txtMediumRating.setText(map.get("mediumRating")+" ");
            ratingMedium.setRating(map.get("mediumRating"));
            ratingBar.setRating(map.get("mediumRating"));
        }
    }


    private void listenerBlack() {
        mImgBackdetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getData(int id) {
        DataService dataService = APIServices.getService();
        Call<DetailsReponse> callback = dataService.getDataPlaceIdPlace(id);
        callback.enqueue(new Callback<DetailsReponse>() {
            @Override
            public void onResponse(Call<DetailsReponse> call, Response<DetailsReponse> response) {
                Log.d("AAA", "getDataPlace: " + response.toString());
                if (response.isSuccessful()) {
                    ArrayList<Place> arrayList = (ArrayList) response.body().getData();
                    arrayListEvaluate = (ArrayList) response.body().getDataEvaluate();
                    Log.d("AAA","total evaluate: "+response.body().getDataEvaluate().size());
                    setDataEvaluate(arrayListEvaluate);
                    setDataRecyclerviewComment(arrayListEvaluate);
                    if (arrayList.size() > 0) {
                        ImageView imgDetail = findViewById(R.id.imgDetail);
                        Picasso.with(getApplicationContext()).load(arrayList.get(0).getImage()).into(imgDetail);
                        mtxtIntroduce.setText(arrayList.get(0).getIntroduce());
                        mTxtOverView.setText(arrayList.get(0).getOverview());
                        txtNameDetail.setText(arrayList.get(0).getName());
                        setDataRecyclerview(arrayList.get(0).getArrayImageView());
                    }
                }

            }

            @Override
            public void onFailure(Call<DetailsReponse> call, Throwable t) {
                Log.d("AAA", "errGetDataPlace: " + t.toString());
            }
        });
    }

    private void setDataRecyclerviewComment(ArrayList<DataEvaluate> arrayListEvaluate) {
        int number = 0;
        if(arrayListEvaluate.size()>0){
            if(arrayListEvaluate.size() > 6){
                number = 6;
            }else{
                number = arrayListEvaluate.size();
            }
            recyclerviewComment.setHasFixedSize(true);
            recyclerviewComment.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));
            AdapterRecyclerviewComment adapter = new AdapterRecyclerviewComment(arrayListEvaluate, DetailActivity.this,
                    R.layout.layout_comment_details,number);
            recyclerviewComment.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }

    }

    private void setDataRecyclerview(String arrayImageView) {
        if (arrayImageView.length() > 0) {
            String[] arr = arrayImageView.split("@");
            ArrayList<Place> arrayList = new ArrayList<>();
            for (String a : arr) {
                Place place = new Place();
                place.setImage(a);
                arrayList.add(place);
            }
            AdapterRecyclerviewImage adapter = new AdapterRecyclerviewImage(getBaseContext(), R.layout.item_image_homepage, arrayList);
            mRecyclerviewImageDetail.setHasFixedSize(true);
            mRecyclerviewImageDetail.setNestedScrollingEnabled(false);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
            linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
            mRecyclerviewImageDetail.setLayoutManager(linearLayoutManager);
            mRecyclerviewImageDetail.setAdapter(adapter);

        }
    }

    private void init() {
        txtSeemoreComment = findViewById(R.id.txtSeemoreComment);
        recyclerviewComment = findViewById(R.id.recyclerviewComment);
        imgMapDetail = findViewById(R.id.imgMapDetail);
        imgLikeEvaluate = findViewById(R.id.imgLikeEvaluate);
        imgEvaluate = findViewById(R.id.imgEvaluate);
        ratingBar = findViewById(R.id.ratingBar);
        ratingMedium = findViewById(R.id.ratingMedium);
        txtMediumRating = findViewById(R.id.txtMediumRating);
        titleEvaluate = findViewById(R.id.titleEvaluate);
        txtLike = findViewById(R.id.txtLike);
        txtRating = findViewById(R.id.txtRating);
        txtNameDetail = findViewById(R.id.txtNameDetail);
        mRecyclerviewImageDetail = findViewById(R.id.recyclerviewImageDetail);
        mTxtTitleImageviewDetail = findViewById(R.id.txtTitleImageviewDetail);
        mTxtOverView = findViewById(R.id.txtOverView);
        mTxtTitleOverviewDetail = findViewById(R.id.txtTitleOverviewDetail);
        mTxtMapDetail = findViewById(R.id.txtMapDetail);
        mtxtIntroduce = findViewById(R.id.txtIntroduce);
        mTitleIntroduce = findViewById(R.id.titleIntroduce);
        mImgBackdetail = findViewById(R.id.imgBackdetail);

        titleEvaluate.setPaintFlags(titleEvaluate.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        mTitleIntroduce.setPaintFlags(mTitleIntroduce.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        mTxtMapDetail.setPaintFlags(mTxtMapDetail.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        mTxtTitleOverviewDetail.setPaintFlags(mTxtTitleOverviewDetail.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        mTxtTitleImageviewDetail.setPaintFlags(mTxtTitleImageviewDetail.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

    }

    public boolean isServicesOk() {
        Log.d(TAG, "isServicesOk: checkking google services version");
        // kieemr tra keets noi cua google co tren android ko
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(DetailActivity.this);
        if (available == ConnectionResult.SUCCESS) {
            // ket noi ban do thanh cong
            Log.d(TAG, "isServicesOk: Google play services is working");
            return true;
        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
            Log.d(TAG, "isServicesOk:an err occured but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(DetailActivity.this, available, ERR_DIALOG_REQUEST);
            dialog.show();
        } else {
            Toast.makeText(this, "we can't make map request", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    Map<String, Integer> numberRatingPlaceIdPlace(List<DataEvaluate> listEvaluate) {
        int _fiveRating = 0;
        int _forRating = 0;
        int _threeRating = 0;
        int _twoRating = 0;
        int _oneRating = 0;
        int _mediumRating = 0;
        int _totalRating = 0;
        int _totalLike = 0;

        for (DataEvaluate element : listEvaluate) {
            if (element.getLike() > 0) {
                _totalLike++;
            }
            if (element.getRating() > 0) {
                _totalRating++;
                switch (element.getRating()) {
                    case 1:
                        _oneRating++;
                        break;
                    case 2:
                        _twoRating++;
                        break;
                    case 3:
                        _threeRating++;
                        break;
                    case 4:
                        _forRating++;
                        break;
                    case 5:
                        _fiveRating++;
                        break;
                }
            }
        }
        try {
            _mediumRating = (_oneRating * 1 +
                    _twoRating * 2 +
                    _threeRating * 3 +
                    _forRating * 4 +
                    _fiveRating * 5) /
                    _totalRating;
        }catch (Exception e){
            _mediumRating = 0;
        }


        Log.d("AAA", "_mediumRating: " + _mediumRating);
        Map<String, Integer> map = new HashMap<String, Integer>();
            map.put("oneRating", _oneRating);
            map.put("twoRating", _twoRating);
            map.put("threeRating", _threeRating);
            map.put("forRating", _forRating);
            map.put("fiveRating", _fiveRating);
            map.put("mediumRating", _mediumRating);
            map.put("totalRating", _totalRating);
            map.put("totalLike", _totalLike);
        return map;
    }
}
