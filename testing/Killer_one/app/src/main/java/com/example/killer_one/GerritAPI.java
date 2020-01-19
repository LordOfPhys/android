package com.example.killer_one;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GerritAPI {
    @Headers("Content-Type: text/html")
    @GET("/take_numbers")
    Call<GeoList> getData();

    @POST("/setLocation/")
    Call<RequestBody> setData(@Body RequestLocation requestLocation);
}