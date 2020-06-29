package com.example.covidtracker.notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.example.covidtracker.classes.Count;
import com.example.covidtracker.classes.Results;
import com.example.covidtracker.data.RetrofitObjectAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.covidtracker.fragments.IndiaFragment.baseURL;

public class AlertReceiver extends BroadcastReceiver {

    public static String WORLD_COUNT;
    public static String INDIA_COUNT;

    @Override
    public void onReceive(Context context, Intent intent) {

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
                WORLD_COUNT = worldStats.get(0).getTotal_cases();
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
                INDIA_COUNT = indiaStats.get(0).getTotal_cases();

                NotificationHelper notificationHelper = new NotificationHelper(context);
                NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
                notificationHelper.getManager().notify(1, nb.build());
            }

            @Override
            public void onFailure(Call<Results> call, Throwable t) {
                Log.i("info: Error: ", t.getMessage());
            }
        });
    }
}