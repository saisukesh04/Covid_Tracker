package com.example.covidtracker;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import com.example.covidtracker.data.RetrofitObjectAPI;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.covidtracker.ui.main.SectionsPagerAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences modeSetting;
    private SharedPreferences.Editor modeEdit;
    private boolean nightMode;

    private ProgressDialog loadingDialog;

    public static final String baseURL = "https://api.covid19india.org/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        modeSetting = getSharedPreferences("ModeSetting",0);
        modeEdit = modeSetting.edit();
        nightMode = modeSetting.getBoolean("NightMode",false);

        if(nightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        loadingDialog = new ProgressDialog(this);
        getJsonData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_mode:
                if(!nightMode) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    nightMode = true;
                    Toast.makeText(this, "Dark Mode On", Toast.LENGTH_SHORT).show();
                    modeEdit.putBoolean("NightMode",true);
                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    nightMode = false;
                    Toast.makeText(this, "Dark Mode Off", Toast.LENGTH_SHORT).show();
                    modeEdit.putBoolean("NightMode",false);
                }
                modeEdit.apply();
                return true;

            default: return false;
        }
    }

    private void getJsonData() {
        ConnectivityManager cm = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);

        assert cm != null;
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        if (isConnected) {
            loadingDialog.setMessage("\tLoading...");
            loadingDialog.setCancelable(true);
            loadingDialog.show();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            RetrofitObjectAPI service = retrofit.create(RetrofitObjectAPI.class);
            Call<Statewise> allCount;

            allCount = service.getCountJson();

            Log.i("Info: ", "OMG00");
            allCount.enqueue(new Callback<Statewise>() {
                @Override
                public void onResponse(Call<Statewise> call, Response<Statewise> response) {
                    assert response.body() != null;
                    String allStates = response.body().getStatewise();
                    loadingDialog.dismiss();
                }

                @Override
                public void onFailure(Call<Statewise> call, Throwable t) {
                    Log.i("info: Error: ", t.getMessage());
                }
            });
        } else {
            Toast.makeText(this, "Please check your INTERNET connection!", Toast.LENGTH_LONG).show();
        }
    }
}