package com.webboconnect.taskdefinelabs.network;


import com.webboconnect.taskdefinelabs.Model.data;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("search")
    Call<data> getData(
            @Field("ll") String ll,
            @Field("oauth_token") String oauth_token,
            @Field("v") String v
    );

}
