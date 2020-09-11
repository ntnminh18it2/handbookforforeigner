package com.example.myapplication.services;
import com.example.myapplication.models.details_reponse.DataEvaluate;
import com.example.myapplication.models.details_reponse.DetailsReponse;
import com.example.myapplication.models.event_reponse.EventReponse;
import com.example.myapplication.models.menu_reponse.MenuReponse;
import com.example.myapplication.models.place_reponse.Place;
import com.example.myapplication.models.place_reponse.PlaceReponse;
import com.example.myapplication.models.user_reponse.UserReponse;
import com.example.myapplication.models.Utilities;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DataService {
    @FormUrlEncoded
    @POST("rest-api/User/updatePass")
    Call<String>updatePass(@Field("email") String email,@Field("password") String password);

    @FormUrlEncoded
    @POST("rest-api/User/register")
    Call<String>registerAcount(@Field("name") String name,
                               @Field("email") String email,
                               @Field("password") String password);

    @FormUrlEncoded
    @POST("rest-api/User/login")
    Call<UserReponse>postLogin(@Field("name") String name,
                               @Field("password") String password);

    @GET("rest-api/Menu/getDataMenuBottom")
    Call<MenuReponse>getDataMenuBottom();

    @GET("rest-api/Menu/getDataMenuTop")
    Call<MenuReponse>getDataMenuTop();

    @GET("rest-api/Event/getDataEventRanDom")
    Call<EventReponse>getDataEventRanDom();

    @GET("rest-api/Place/getDataPlaceHomeRandom")
    Call<PlaceReponse>getDataPlaceHomeRandom(@Query("id") int id,
                                             @Query("check") int check);

    @GET("rest-api/Ingredient/getDataIngredientIdMenu/id_menu={id}")
    Call<MenuReponse>getDataIngredientIdMenu(@Path("id") int id);

    @GET("rest-api/Place/getDataPlaceIdIngredient")
    Call<PlaceReponse>getDataPlaceIdIngredient(@Query("id") int id);

    @GET("rest-api/Place/getDataPlaceIdPlace")
    Call<DetailsReponse>getDataPlaceIdPlace(@Query("id") int id);

    @FormUrlEncoded
    @POST("rest-api/Evaluate/postLikePlace")
    Call<String>postLikePlace(@Field("id_user") int id_user,
                              @Field("id_place") int id_place,
                              @Field("comment") String comment,
                              @Field("rating") int rating,
                              @Field("like") int like);

    @GET("rest-api/Place/getDataPlaceStrSearch")
    Call<PlaceReponse>getDataPlaceStrSearch(@Query("strSearch") String strSearch);

    @GET("rest-api/User/getNameUser")
    Call<String>getNameUser(@Query("id") int id);

    @GET("rest-api/Menu/getDataMenuAll")
    Call<MenuReponse>getDataMenuAll();

    @GET("rest-api/Place/getDataPlaceIdMenu")
    Call<PlaceReponse>getDataPlaceIdMenu(@Query("id") int id);

    @FormUrlEncoded
    @POST("rest-api/User/updateUser")
    Call<String>updateUser(@Field("id") int id,
                           @Field("name") String name,
                           @Field("email") String email,
                           @Field("phone") String phone,
                           @Field("age") int age,
                           @Field("gender") int gender);

}
