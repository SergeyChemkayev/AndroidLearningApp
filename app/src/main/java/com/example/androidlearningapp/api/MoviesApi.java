package com.example.androidlearningapp.api;

import com.example.androidlearningapp.ResultList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MoviesApi {
    String BASE_URL = "http://www.mocky.io/v2/";

    @GET("57cffac8260000181e650041")
    Call<ResultList> movies();
}
