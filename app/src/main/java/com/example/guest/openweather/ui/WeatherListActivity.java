package com.example.guest.openweather.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.guest.openweather.R;
import com.example.guest.openweather.adapters.WeatherListAdapter;
import com.example.guest.openweather.models.DailyForecast;
import com.example.guest.openweather.services.OpenWeatherService;


import okhttp3.Call;
import okhttp3.Callback;
import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Response;

public class WeatherListActivity extends AppCompatActivity {
    @BindView(R.id.recyclerview) RecyclerView mRecyclerView;

    private WeatherListAdapter mAdapter;
    public static final String TAG = WeatherListActivity.class.getSimpleName();
    public ArrayList<DailyForecast> dailyForecasts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_list);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");

        getWeather(location);
    }

    private void getWeather(String location) {
        final OpenWeatherService weatherService = new OpenWeatherService();

        weatherService.getWeather(location, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                dailyForecasts = weatherService.processResults(response);

                WeatherListActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter = new WeatherListAdapter(getApplicationContext(), dailyForecasts);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(WeatherListActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });

            }

        });
    }

}
