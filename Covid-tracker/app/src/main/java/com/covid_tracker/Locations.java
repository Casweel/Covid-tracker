package com.covid_tracker;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Locations {
    @SerializedName("latest")
    private Latest latest;

    @SerializedName("locations")
    private ArrayList<Location> locations;

    public Latest getLatest() {
        return latest;
    }

    public ArrayList<Location> getLocations() {
        return locations;
    }
}