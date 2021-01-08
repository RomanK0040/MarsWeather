package com.romankurilenko40.marsweather;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;


import com.romankurilenko40.marsweather.backend.InSightWeatherRepository;
import com.romankurilenko40.marsweather.model.SolWeatherData;

import java.util.List;

public class WeatherViewModel extends AndroidViewModel {

    private final MutableLiveData<List<SolWeatherData>> mLastWeatherData = new MutableLiveData<>();

    private Observer<List<SolWeatherData>> mWeatherObserver = new Observer<List<SolWeatherData>>() {
        @Override
        public void onChanged(List<SolWeatherData> solWeatherData) {
            mLastWeatherData.setValue(solWeatherData);
        }
    };

    public WeatherViewModel(@NonNull Application application) {
        super(application);
        InSightWeatherRepository weatherRepository = InSightWeatherRepository.getInstance(application);
        weatherRepository.getLastWeather().observeForever(mWeatherObserver);
    }

    public LiveData<List<SolWeatherData>> getLastWeatherData() {
        return mLastWeatherData;
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        mLastWeatherData.removeObserver(mWeatherObserver);
    }
}
