package com.example.myapplication.src.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.src.Adapter.AdapterMap;
import com.example.myapplication.util.demo;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback{
    private static final String TAG = "MapActivity";
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static  final  String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static int LOCATION_PERMISION_REQUEST_CODE=1234;
    private static final float DEFAUL_ZOOM = 15f;
    //widgets
    private EditText mSeachText;
    private ImageView mImgderection;
    private ImageView mImageGps;
    //var
    private ArrayList<LatLng> listStep;
    private Location mLocationStart;
    private Location mLocationEnd;
    private PolylineOptions polyline;
    private boolean mLocationPermisonGranted = false;
    private GoogleMap mMap;
    //FuseLocationProviderClient là để tương tác với vị trí bằng cách sử dụng nhà cung cấp location provider.
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private RecyclerView recyclerviewMapSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
       anhxa();
        checkLocationPermison();

    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        Toast.makeText(this, "Map is Ready", Toast.LENGTH_SHORT).show();
        Log.d(TAG,"Map is ready");
        mMap = googleMap;
        if(mLocationPermisonGranted){
            getDeviceLocation();//lay vi tri hien tai cua thiet bi
            searchGoogleMap();
            mImageGps.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mLocationPermisonGranted){
                        Log.d(TAG, "onClick: click gps icon");
                        getDeviceLocation();//function lay vi tri hien tai cua nguoi dung
                    }
                }
            });
        }
    }

    private void anhxa() {
        mImgderection = findViewById(R.id.ic_derection);
        mImageGps = findViewById(R.id.ic_gps);
        recyclerviewMapSearch = findViewById(R.id.recyclerviewMapSearch);
        recyclerviewMapSearch.setHasFixedSize(true);
        recyclerviewMapSearch.setLayoutManager(new GridLayoutManager(MapActivity.this,1));
    }

    // function search return về list điaiem
    private void searchGoogleMap() {
        mSeachText = findViewById(R.id.txt_search);
        mSeachText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
               // lay dia chi tu vi tri tren google map
                Log.d(TAG, "geoLacate: leogocating");
                String searchString = mSeachText.getText().toString();
                //  được sử dụng để mã hóa địa lý ngược, tức là lấy địa chỉ từ vị trí trên Google Map.
                Geocoder geocoder = new Geocoder(MapActivity.this);
                List<Address>list = new ArrayList<>();
                try {
                    list = geocoder.getFromLocationName(searchString,10);//phuong thuc tim kiem
                }catch (IOException e){
                    Log.d(TAG, "geoLacate: IOException: "+e.getMessage());
                }

                if(list.size() >0){
                    AdapterMap adapterMap = new AdapterMap(list,MapActivity.this,R.layout.item_recyclerview_map_search);
                    recyclerviewMapSearch.setAdapter(adapterMap);
                    adapterMap.notifyDataSetChanged();
                    Address address = list.get(0);
                    Log.d(TAG, "geoLacate: found a location: "+address.toString());
                }
            }
        });
    }


    private void initMap(){
        Log.d(TAG,"init Map: ");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(MapActivity.this);
    }

    //kiem tra su cho phep google map
    private void checkLocationPermison(){
        Log.d(TAG,"get Location Permison: getting location permison");
        String [] permisons = {Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION};
        if(ContextCompat.checkSelfPermission(this.getApplicationContext(),FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission(this.getApplicationContext(),COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                mLocationPermisonGranted = true;
                initMap();
            }else {
               // Tuy nhiên, nếu quyền đó vẫn chưa được cấp, bạn có thể yêu cầu nó với requestPermissions
                ActivityCompat.requestPermissions(this,permisons,LOCATION_PERMISION_REQUEST_CODE);
            }
        }else{
            ActivityCompat.requestPermissions(this,permisons,LOCATION_PERMISION_REQUEST_CODE);
        }
    }

    //lay vi tri hien tai cua thiet bi
    private void getDeviceLocation(){
        Log.d(TAG, "getDeviceLocation: getting the devices current location");
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        try{
            if(mLocationPermisonGranted){
                final Task location = mFusedLocationProviderClient.getLastLocation();
                //Để xử lý thành công và thất bại trong cùng một người nghe, hãy đính kèm OnCompleteListener
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if(task.isSuccessful()){
                            Log.d(TAG, "onComplete: found location!");
                            if(task.getResult()!=null){
                                Location currentLocation = (Location) task.getResult();
                                mLocationStart = currentLocation;
                                // function di chuyen camera den vi tri hien tai
                                moveCamera(new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude()),DEFAUL_ZOOM,
                                        "My location",false);
                                //danh dau vi tri tren ban do(cham mau xanh)
                                mMap.setMyLocationEnabled(true);
                                //khi su dung setMyLocationEnabled se co vi tri mac dinh tren man hinh
                                // muon thay doi vi tri do thi tat no di
                                mMap.getUiSettings().setMyLocationButtonEnabled(false);

                            }else{
                                Toast.makeText(MapActivity.this, "No find location", Toast.LENGTH_SHORT).show();
                            }

                        }else{
                            Log.d(TAG, "onComplete: current location is null");
                            Toast.makeText(MapActivity.this, "Unable to get current location", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }catch (SecurityException e){
            Log.d(TAG, "getDeviceLocation: SecurityException: "+e.getMessage());
        }
    }

    //di chuyen camera khi lay dc vi tri location
    public void moveCamera(LatLng latLng,float zoom, String title,boolean check){
        Log.d(TAG, "moveCamera: moving the camera to: last: "+latLng.latitude+", lng: "+latLng.longitude);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,zoom));
        // danh dau dia diem
        if(check){
            MarkerOptions options = new MarkerOptions()
                    .position(latLng)
                    .title(title);

            mMap.addMarker(options);
            //mImgderection.setVisibility(View.VISIBLE);
        }
        mImgderection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new demo().executeGetData();
            }
        });

    }

    // lắng nghe sự kiện tính quảng đường đi
  //  private void listenerDerection(final Location mLocationStart, final LatLng latLng) {
    //    Log.d("BBB", "location start: "+mLocationStart.toString());
    //    Log.d("BBB","location end: "+latLng.toString());
//        final AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
//
//            @Override
//            protected Void doInBackground(Void... params) {

                // 227 Nguyễn Văn Cừ : 10.762643, 106.682079
                // Phố đi bộ Nguyễn Huệ : 10.774467, 106.703274
//
//                String request = makeURL("10.762643","106.682079","10.774467","106.703274");
//                GetDirectionsTask task = new GetDirectionsTask(request);
//                String hhh = String.valueOf(task);
//                Log.d("AAA","datta: "+hhh);
//                ArrayList<LatLng> list = task.testDirection();
//                for (LatLng latLng : list) {
//                    listStep.add(latLng);
//                }
//              return null;
//            }
//            @Override
//            protected void onPostExecute(Void result) {
//                // TODO Auto-generated method stub
//                super.onPostExecute(result);
//                Log.d(TAG, "onPostExecute: ");
//                polyline.addAll(listStep);
//                Polyline line = mMap.addPolyline(polyline);
//                line.setColor(Color.BLUE);
//                line.setWidth(5);
//            }
//        };

 //   }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG,"onRequestpermisionsResult: called");
        mLocationPermisonGranted = false;
        if(requestCode == LOCATION_PERMISION_REQUEST_CODE){
            if(grantResults.length > 0 ){
                for (int i =0 ; i < grantResults.length; i++){
                    if(grantResults[i] != PackageManager.PERMISSION_GRANTED){
                        mLocationPermisonGranted = false;
                        Log.d(TAG,"onRequestpermisionsResult: failed");
                        return;
                    }
                }
                Log.d(TAG,"onRequestpermisionsResult: granted");
                mLocationPermisonGranted = true;
                initMap();
            }
        }

    }

    public String makeURL (String sourcelat, String sourcelng, String destlat, String destlng ){
        StringBuilder urlString = new StringBuilder();
        urlString.append("https://maps.googleapis.com/maps/api/directions/json");
        urlString.append("?origin=");// from
        urlString.append(sourcelat);
        urlString.append(",");
        urlString.append(sourcelng);
        urlString.append("&destination=");// to
        urlString.append(destlat);
        urlString.append(",");
        urlString.append(destlng);
        urlString.append("&key="+getResources().getString(R.string.google_apikey_derection));
        return "https://maps.googleapis.com/maps/api/directions/json?origin=10.762643,106.682079&destination=10.774467,106.703274&&key=AIzaSyCTOfSjXjaWexhm1-VHoaIuyHoR4mk0-0g";
    }
}
