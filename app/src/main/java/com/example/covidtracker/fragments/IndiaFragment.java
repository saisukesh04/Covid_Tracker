package com.example.covidtracker.fragments;

import android.graphics.Movie;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.covidtracker.Count;
import com.example.covidtracker.R;
import com.example.covidtracker.Results;
import com.example.covidtracker.data.RetrofitObjectAPI;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.covidtracker.MainActivity.baseURL;

public class IndiaFragment extends Fragment {

    public IndiaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_india, container, false);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitObjectAPI service = retrofit.create(RetrofitObjectAPI.class);
        Call<Results> worldCountCall, indiaCountCall;

        worldCountCall = service.getWorldStats();
        indiaCountCall = service.getIndiaStats();

        worldCountCall.enqueue(new Callback<Results>() {
            @Override
            public void onResponse(Call<Results> call, Response<Results> response) {
                assert response.body() != null;
                List<Count> worldStats = response.body().getResults();
                Log.i("Info World cases: ", worldStats.get(0).getTotal_cases());
            }

            @Override
            public void onFailure(Call<Results> call, Throwable t) {
                Log.i("info: Error: ", t.getMessage());
            }
        });

        indiaCountCall.enqueue(new Callback<Results>() {
            @Override
            public void onResponse(Call<Results> call, Response<Results> response) {
                assert response.body() != null;
                List<Count> indiaStats = response.body().getCountrydata();
                Log.i("Info India cases: ", indiaStats.get(0).getTotal_cases());
            }

            @Override
            public void onFailure(Call<Results> call, Throwable t) {
                Log.i("info: Error: ", t.getMessage());
            }
        });

        return view;
    }
}