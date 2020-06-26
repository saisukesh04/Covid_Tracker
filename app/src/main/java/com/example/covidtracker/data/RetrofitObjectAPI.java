package com.example.covidtracker.data;

import com.example.covidtracker.classes.Results;
import com.example.covidtracker.classes.Statewise;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitObjectAPI {

    @GET("free-api?global=stats")
    Call<Results> getWorldStats();

    @GET("free-api?countryTotal=IN")
    Call<Results> getIndiaStats();

    @GET("data.json")
    Call<Statewise> getAllStatesStats();
}