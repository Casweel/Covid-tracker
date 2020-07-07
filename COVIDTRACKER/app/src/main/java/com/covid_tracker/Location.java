package com.covid_tracker;

import com.google.gson.annotations.SerializedName;

public class Location {
    @SerializedName("coordinates")
    private Coordinates coordinates;

    @SerializedName("id")
    private int id;

    @SerializedName("country")
    private String country;

    @SerializedName("latest")
    private Latest latest;

    @SerializedName("province")
    private String province;

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Latest getLatest() {
        return latest;
    }

    public int getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }
}

