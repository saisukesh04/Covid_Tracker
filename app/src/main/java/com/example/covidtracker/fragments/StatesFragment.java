package com.example.covidtracker.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.covidtracker.R;
import com.example.covidtracker.adapter.StatesAdapter;
import com.example.covidtracker.classes.Statewise;
import com.example.covidtracker.data.RetrofitObjectAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StatesFragment extends Fragment {

    public final String baseURL = "https://api.covid19india.org/";
    private RecyclerView state_stats_recycler_view;

    public StatesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_states, container, false);

        state_stats_recycler_view = view.findViewById(R.id.state_stats_recycler_view);

        LinearLayoutManager mLayout = new LinearLayoutManager(getContext());
        state_stats_recycler_view.setHasFixedSize(true);
        state_stats_recycler_view.setLayoutManager(mLayout);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitObjectAPI service = retrofit.create(RetrofitObjectAPI.class);
        Call<Statewise> stateCountCall;

        stateCountCall = service.getAllStatesStats();

        stateCountCall.enqueue(new Callback<Statewise>() {
            @Override
            public void onResponse(Call<Statewise> call, Response<Statewise> response) {
                assert response.body() != null;
                List<Statewise> stateStats = response.body().getStatewise();
                stateStats.remove(0);
                StatesAdapter adapter = new StatesAdapter(getActivity(), stateStats);
                state_stats_recycler_view.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Statewise> call, Throwable t) {
                Log.i("info: Error: ", t.getMessage());
            }
        });

        return view;
    }
}