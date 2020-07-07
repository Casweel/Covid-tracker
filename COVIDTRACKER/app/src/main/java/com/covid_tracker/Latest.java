package com.covid_tracker;

import com.google.gson.annotations.SerializedName;

public class Latest {
    @SerializedName("confirmed")
    private int confirmed;

    @SerializedName("deaths")
    private int deaths;

    public int getConfirmed() {
        return confirmed;
    }

    public int getDeaths() {
        return deaths;
    }
}
