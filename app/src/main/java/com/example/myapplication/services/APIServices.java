package com.example.myapplication.services;

public class APIServices {
    private static String baseurl="http://192.168.43.229/foreignerhandbook/public/";

    public static DataService getService(){

        return APIRetrofitClient.getClient(baseurl).create(DataService.class);
    }
}
