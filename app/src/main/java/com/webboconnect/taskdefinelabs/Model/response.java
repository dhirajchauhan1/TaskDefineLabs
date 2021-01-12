package com.webboconnect.taskdefinelabs.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class response {
    @SerializedName("venues")
    private List<venues> venues;

    public List<com.webboconnect.taskdefinelabs.Model.venues> getVenues() {
        return venues;
    }

    public void setVenues(List<com.webboconnect.taskdefinelabs.Model.venues> venues) {
        this.venues = venues;
    }
}

