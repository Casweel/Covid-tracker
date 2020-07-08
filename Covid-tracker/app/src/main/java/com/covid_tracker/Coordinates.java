package com.covid_tracker;

import com.google.gson.annotations.SerializedName;

public class Coordinates {
    @SerializedName("latitude")
    private String latitude;

    @SerializedName("longitude")
    private String longitude;

    public double getLatitude() {
        return Double.parseDouble(latitude);
    }

    public double getLongitude() {
        return Double.parseDouble(longitude);
    }
}