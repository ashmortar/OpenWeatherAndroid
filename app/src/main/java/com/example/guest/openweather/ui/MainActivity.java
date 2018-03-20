package com.example.guest.openweather.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.guest.openweather.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.getWeatherButton) Button mGetWeatherButton;
    @BindView(R.id.locationEditText) EditText mLocationEditText;
    @BindView(R.id.appNameTextView) TextView mAppNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface amadeus = Typeface.createFromAsset(getAssets(), "fonts/Amadeus.ttf");
        mAppNameTextView.setTypeface(amadeus);

        mGetWeatherButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mGetWeatherButton) {
            String location = mLocationEditText.getText().toString();
            Intent intent = new Intent(MainActivity.this, WeatherListActivity.class);
            intent.putExtra("location", location);
            startActivity(intent);
        }
    }
}
