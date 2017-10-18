package com.hacknews.hacknews.apitask;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Eshan on 10/18/17.
 */

public interface ApiService
{

    @GET("search")
    Call<ResponseBody> getNews(@Query("query") String category);


}
