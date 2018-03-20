package com.example.guest.openweather.models;



public class DailyForecast {
    private String tempMin;
    private String tempMax;
    private String description;
    private String date;


    public DailyForecast(String tempMin, String tempMax, String description, String date)   {
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.description = description;
        this.date = date;
    }

    public String getTempMin() {
        return tempMin;
    }

    public String getTempMax() {
        return tempMax;
    }

    public String getDescription() {
        return description;
    }
    public String getDate() {
        return date;
    }
}
