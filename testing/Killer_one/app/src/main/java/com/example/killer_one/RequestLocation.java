package com.example.killer_one;

import com.google.gson.annotations.SerializedName;

public class RequestLocation {

    @SerializedName("lat")
    public String lat;

    @SerializedName("lontitude")
    public String lontitude;

    public RequestLocation(String lat, String lontitude) {
        this.lat = lat;
        this.lontitude = lontitude;
    }
}
