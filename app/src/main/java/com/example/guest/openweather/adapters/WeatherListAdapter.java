package com.example.guest.openweather.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guest.openweather.R;
import com.example.guest.openweather.models.DailyForecast;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Guest on 3/20/18.
 */

public class WeatherListAdapter extends RecyclerView.Adapter<WeatherListAdapter.WeatherViewHolder> {
    private ArrayList<DailyForecast> mWeather = new ArrayList<>();
    private Context mContext;

    public WeatherListAdapter(Context context, ArrayList<DailyForecast> weathers) {
        mContext = context;
        mWeather = weathers;
    }

    @Override
    public WeatherListAdapter.WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_list_item, parent, false);
        WeatherViewHolder viewHolder = new WeatherViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(WeatherListAdapter.WeatherViewHolder holder, int position) {
        holder.bindWeather(mWeather.get(position));
    }

    @Override
    public int getItemCount() {
        return mWeather.size();
    }


    public class WeatherViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.TempMin)
        TextView mTempMin;
        @BindView(R.id.TempMax) TextView mTempMax;
        @BindView(R.id.Description) TextView mDescription;
        @BindView(R.id.Date) TextView mDate;

        private Context mContext;

        public WeatherViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindWeather(DailyForecast dailyForecast) {
            mTempMin.setText(dailyForecast.getTempMin());
            mTempMax.setText(dailyForecast.getTempMax());
            mDescription.setText(dailyForecast.getDescription());
            mDate.setText(dailyForecast.getDate());
        }
    }
}
