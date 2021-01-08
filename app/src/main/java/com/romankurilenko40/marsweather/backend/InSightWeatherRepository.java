package com.romankurilenko40.marsweather.backend;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.romankurilenko40.marsweather.model.SolWeatherData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Repository class providing retrieving data from web services and saving in local file
 * this class first returns local stored data then updating them after fetching new
 */
public class InSightWeatherRepository {
    private static final String TAG = "InSightWeatherRepo";
    private static InSightWeatherRepository INSTANSE = null;

    private final String JSON_CACHED_FILENAME = "cachedJson";
    private Context mContext;

    private InSightWeatherFetcher mWeatherFetcher;
    private LiveData<List<SolWeatherData>> mWeatherData;


    private InSightWeatherRepository(Application application) {
        mContext = application;
        mWeatherFetcher = new InSightWeatherFetcher();
        mWeatherData = getCachedData();
        loadDataInBackground();
    }

    public static synchronized InSightWeatherRepository getInstance(Application application) {
        if (INSTANSE == null) {
            INSTANSE = new InSightWeatherRepository(application);
        }
        return INSTANSE;
    }

    public LiveData<List<SolWeatherData>> getLastWeather() {
        return mWeatherData;
    }


    private void cacheJsonFile(byte[] jsonBytes) throws IOException {
        try (FileOutputStream outputStream = mContext.openFileOutput(JSON_CACHED_FILENAME, Context.MODE_PRIVATE)) {
            outputStream.write(jsonBytes);
            Log.i(TAG, "saved json in local storage");
        }
    }

    private LiveData<List<SolWeatherData>> getCachedData() {
        LiveData<List<SolWeatherData>> data = new MutableLiveData<>();
        File cachedData = new File(mContext.getFilesDir(), JSON_CACHED_FILENAME);
        try (FileInputStream inputStream = new FileInputStream(cachedData)) {
            byte[] buffer = new byte[inputStream.available()];
            int bytesRead = inputStream.read(buffer, 0, buffer.length);
            if (bytesRead != -1) {
                data = mWeatherFetcher.parseJson(new String(buffer));
                Log.i(TAG, "retrieve data from cache");
            }
        } catch (IOException ioe) {
            Log.e(TAG, "Failed to get data from cache");
        }
        return data;
    }

    private void loadDataInBackground() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    byte[] response = mWeatherFetcher.getResponseBytes();
                    if (response.length > 0) {
                        cacheJsonFile(response);
                    }
                    mWeatherData = mWeatherFetcher.getWeatherData(response);
                    Log.i(TAG, "retrieve data from web service");
                } catch (IOException e) {
                    Log.e(TAG, "Error during loading data: ", e);
                }
            }
        }).start();
    }
}
