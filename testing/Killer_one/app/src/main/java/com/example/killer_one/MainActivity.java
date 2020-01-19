package com.example.killer_one;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


import androidx.appcompat.app.AppCompatActivity;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.StandardSocketOptions;
import java.net.URL;
import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Controller();
    }

    private void Controller() {

        final String BASE_URL = "http://151.248.125.119";
        GerritAPI gerritAPI;

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        gerritAPI = retrofit.create(GerritAPI.class);
        Call<GeoList> call = gerritAPI.getData();
        call.enqueue(new Callback<GeoList>() { // запрос геолокации
            @Override
            public void onResponse(Call<GeoList> call, Response<GeoList> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.d("onSuccess", response.body().length.toString());
                        String jsonresponse = response.body().toString();
                    } else {
                        Log.d("onEmptyResponse", "Returned empty response");
                    }
                }
            }

            @Override
            public void onFailure(Call<GeoList> call, Throwable t) {
            }
        });
        RequestLocation one_one = new RequestLocation();
        one_one.lat = "Axes X";
        one_one.lontitude = "Axes Y";
        Call<RequestBody> callSend = gerritAPI.setData(one_one);
        callSend.enqueue(new Callback<RequestBody>() {
            @Override
            public void onResponse(Call<RequestBody> callSend, Response<RequestBody> response) {
                Log.d("onSend", response.toString());
            }

            @Override
            public void onFailure(Call<RequestBody> call, Throwable t) {
            }
        });
    }
}
