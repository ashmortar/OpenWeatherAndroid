package com.example.guest.openweather.services;
import android.nfc.Tag;
import android.util.Log;

import com.example.guest.openweather.Constants;
import com.example.guest.openweather.models.DailyForecast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;



public class OpenWeatherService {

    public static void getWeather(String location, Callback callback) {

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.WEATHER_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.WEATHER_LOCATION_QUERY_PARAMETER, location);
        urlBuilder.addQueryParameter(Constants.UNITS_QUERY_PARAMETER, "imperial");
        urlBuilder.addQueryParameter(Constants.API_KEY_QUERY_PARAMETER, Constants.WEATHER_TOKEN);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<DailyForecast> processResults(Response response) {
        ArrayList<DailyForecast> dailyForecasts = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            Log.v("tag", jsonData);
            JSONObject weatherJSON = new JSONObject(jsonData);
            JSONArray forcastJSON = weatherJSON.getJSONArray("list");
            for (int i = 0; i < forcastJSON.length(); i++) {
                JSONObject dailyJSON = forcastJSON.getJSONObject(i);
                String tempMin = dailyJSON.getJSONObject("main").getString("temp_min");
                String tempMax = dailyJSON.getJSONObject("main").getString("temp_max");
                String date = dailyJSON.getString("dt");
                String description = dailyJSON.getJSONArray("weather").getJSONObject(0).getString("description");
                DailyForecast df = new DailyForecast(tempMin, tempMax, description, date);
                dailyForecasts.add(df);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dailyForecasts;
    }
}
