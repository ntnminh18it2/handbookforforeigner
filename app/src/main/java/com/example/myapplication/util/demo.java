package com.example.myapplication.util;

import android.os.AsyncTask;
import android.util.Log;

import com.example.myapplication.models.Directions;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class demo{

    public void executeGetData(){
        new getData().execute("https://maps.googleapis.com/maps/api/directions/json?origin=10.762643,106.682079&destination=10.774467,106.703274&&key=AIzaSyAKjP9U7ClZOc5UCS2EsmE99VHe6xitv-I");
    }

}
class getData extends AsyncTask<String,Void,String> {
    ArrayList<LatLng> ret = new ArrayList<LatLng>();
    @Override
    protected String doInBackground(String... strings) {
        StringBuilder stringBuilder = new
                StringBuilder();
        try {
            URL url = new URL(strings[0]);
            InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            bufferedReader.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.d("BBB", "onPostExecute: "+s);
        Directions results = new Gson().fromJson(s, Directions.class);
        Directions.Route[] routes = results.getRoutes();
//        Directions.Leg[] leg = routes[0].getLegs();
//        Directions.Leg.Step[] steps = leg[0].getSteps();
//        for (Directions.Leg.Step step : steps) {
//            LatLng latlngStart = new LatLng(step.getStart_location().getLat(),
//                    step.getStart_location().getLng());
//
//            LatLng latlngEnd = new LatLng(step.getEnd_location().getLat(),
//                    step.getEnd_location().getLng());
//
//            ret.add(latlngStart);
//            ret.add(latlngEnd);
   //     }
    }
}

