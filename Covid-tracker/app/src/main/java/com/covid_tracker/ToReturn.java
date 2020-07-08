package com.covid_tracker;

import android.content.Context;

import com.google.android.gms.maps.GoogleMap;

import java.util.ArrayList;

public class ToReturn {
    private Latest latest;
    private ArrayList<Location> location;
    private GoogleMap mMap;
    private Context context;

    ToReturn(Latest latest, ArrayList<com.covid_tracker.Location> location, Context context, GoogleMap mMap) {
        this.latest = latest;
        this.location = location;
        this.context = context;
        this.mMap = mMap;
    }

    public Latest getLatest() {
        return latest;
    }

    public ArrayList<com.covid_tracker.Location> getLocation() {
        return location;
    }

    public void setLatest(Latest latest) {
        this.latest = latest;
    }

    public void setLocation(ArrayList<com.covid_tracker.Location> location) {
        this.location = location;
    }

    public GoogleMap getmMap() {
        return mMap;
    }

    public Context getContext() {
        return context;
    }
}