package com.example.covidtracker.data;

import com.example.covidtracker.Results;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitObjectAPI {

    @GET("free-api?global=stats")
    Call<Results> getWorldStats();

    @GET("free-api?countryTotal=IN")
    Call<Results> getIndiaStats();
}