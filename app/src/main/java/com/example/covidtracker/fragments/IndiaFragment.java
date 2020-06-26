package com.example.covidtracker.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.covidtracker.classes.Count;
import com.example.covidtracker.R;
import com.example.covidtracker.classes.Results;
import com.example.covidtracker.data.RetrofitObjectAPI;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class IndiaFragment extends Fragment {

    @BindView(R.id.world_total_count) TextView world_total_count;
    @BindView(R.id.world_recovered_count) TextView world_recovered_count;
    @BindView(R.id.world_death_count) TextView world_death_count;
    @BindView(R.id.india_total_count) TextView india_total_count;
    @BindView(R.id.india_recovered_count) TextView india_recovered_count;
    @BindView(R.id.india_death_count) TextView india_death_count;

    public final String baseURL = "https://api.thevirustracker.com/";

    public IndiaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_india, container, false);

        ButterKnife.bind(this, view);

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
                world_total_count.setText(worldStats.get(0).getTotal_cases());
                world_recovered_count.setText(worldStats.get(0).getTotal_recovered());
                world_death_count.setText(worldStats.get(0).getTotal_deaths());
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
                india_total_count.setText(indiaStats.get(0).getTotal_cases());
                india_recovered_count.setText(indiaStats.get(0).getTotal_recovered());
                india_death_count.setText(indiaStats.get(0).getTotal_deaths());
            }

            @Override
            public void onFailure(Call<Results> call, Throwable t) {
                Log.i("info: Error: ", t.getMessage());
            }
        });

        return view;
    }
}