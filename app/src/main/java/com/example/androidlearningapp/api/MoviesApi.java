package com.example.androidlearningapp.api;

import com.example.androidlearningapp.ResultList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MoviesApi {

    @GET("57cffac8260000181e650041")
    Call<ResultList> movies();

}
