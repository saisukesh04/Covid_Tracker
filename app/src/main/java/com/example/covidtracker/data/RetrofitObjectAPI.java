package com.example.covidtracker.data;

import com.example.covidtracker.Statewise;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitObjectAPI {

    @GET("data.json")
    Call<Statewise> getCountJson();
}