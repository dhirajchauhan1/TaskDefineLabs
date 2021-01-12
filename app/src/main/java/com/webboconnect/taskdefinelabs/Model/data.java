package com.webboconnect.taskdefinelabs.Model;

import com.google.gson.annotations.SerializedName;

public class data {

    @SerializedName("response")
    private response response;

    public com.webboconnect.taskdefinelabs.Model.response getResponse() {
        return response;
    }

    public void setResponse(com.webboconnect.taskdefinelabs.Model.response response) {
        this.response = response;
    }
}
