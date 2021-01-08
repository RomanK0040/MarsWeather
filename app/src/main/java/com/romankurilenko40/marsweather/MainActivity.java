package com.romankurilenko40.marsweather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;

import com.romankurilenko40.marsweather.databinding.ActivityMainBinding;
import com.romankurilenko40.marsweather.model.SolWeatherData;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private WeatherViewModel mViewModel;
    ActivityMainBinding mMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = mMainBinding.getRoot();
        setContentView(view);


        mMainBinding.weatherRecyclerView.setLayoutManager(
                new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        WeatherAdapter.OnSolItemClickListener itemClickListener = new WeatherAdapter.OnSolItemClickListener() {
            @Override
            public void onSolClick(SolWeatherData solWeatherData, int position) {
                showAnimation();
                fillMainWeatherView(solWeatherData);
            }
        };
        final WeatherAdapter adapter = new WeatherAdapter(this, itemClickListener);
        mMainBinding.weatherRecyclerView.setAdapter(adapter);


        mViewModel = new ViewModelProvider(this).get(WeatherViewModel.class);
        mViewModel.getLastWeatherData().observe(this, new Observer<List<SolWeatherData>>() {
            @Override
            public void onChanged(List<SolWeatherData> solWeatherData) {
                if (solWeatherData.size() > 0) {
                    fillMainWeatherView(solWeatherData.get(solWeatherData.size() - 1));
                    adapter.setWeatherData(solWeatherData);
                } else {
                    mMainBinding.solText.setText("no any data");
                }
            }
        });


    }

    private void showAnimation() {
        View infoView = mMainBinding.mainDisplayedView;
        float yStart = infoView.getBottom();
        float yEnd = infoView.getTop();
        ObjectAnimator animator = ObjectAnimator
                .ofFloat(infoView, "y", yStart, yEnd)
                .setDuration(300);
        animator.start();
    }

    private void fillMainWeatherView(SolWeatherData weatherData) {
        String solString = weatherData.getSol() + "";
        mMainBinding.solText.setText(solString);
        mMainBinding.utcText.setText(weatherData.getFormattedDate());

        if (weatherData.getTemperatureData() != null) {
            mMainBinding.tempText.setText(weatherData.getTemperatureData().getTemperatureRangeString());
        } else {
            mMainBinding.tempText.setText(R.string.no_data);
        }

        if (weatherData.getPressureData() != null) {
            mMainBinding.presText.setText(weatherData.getPressureData().getPressureRangeString());
        } else {
            mMainBinding.presText.setText(R.string.no_data);
        }

        if (weatherData.getWindSpeedData() != null && weatherData.getWindDirectionData() != null) {
            mMainBinding.windDirText.setText(weatherData.getWindDirectionData().getWindDirectionString());
            mMainBinding.windSpeedText.setText(weatherData.getWindSpeedData().getWindSpeedRangeString());
        } else {
            mMainBinding.windSpeedText.setText(R.string.no_data);
        }
    }

}