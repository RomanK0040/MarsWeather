package com.romankurilenko40.marsweather.backend;

import android.net.Uri;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.romankurilenko40.marsweather.model.SolWeatherData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * A class providing methods to retrieve weather report from InSight lander
 * using NASA public API
 */
public class InSightWeatherFetcher {
    private static final String TAG = "InSightWeatherFetcher";
    private static final String API_KEY = "DEMO_KEY";


    private static final Uri ENDPOINT = Uri
            .parse("https://api.nasa.gov/insight_weather/")
            .buildUpon()
            .appendQueryParameter("api_key", API_KEY)
            .appendQueryParameter("feedtype", "json")
            .appendQueryParameter("ver", "1.0")
            .build();

    /**
     * A method that return raw response in binary format according given url
     * @return - binary response data from web server
     * @throws IOException - throws exception if there is no response from server
     */
    public byte[] getResponseBytes() throws IOException {
        String urlSpec = ENDPOINT.toString();
        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException(connection.getResponseMessage() + ": with " + urlSpec);
            }
            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            return out.toByteArray();
        } finally {
            connection.disconnect();
        }
    }

    /**
     * A method that transforms binary response in to collection of objects
     * @param responseBytes - array of received bytes that needs to parse and convert in to java object
     * @return - LiveData containing a List with weather data
     */
    public LiveData<List<SolWeatherData>> getWeatherData(byte[] responseBytes) {
        LiveData<List<SolWeatherData>> solWeatherDataList;

        String jsonString = new String(responseBytes);
        Log.i(TAG, "Received JSON: " + jsonString);
        solWeatherDataList = parseJson(jsonString);

        return solWeatherDataList;
    }


    public LiveData<List<SolWeatherData>> parseJson(String jsonString) {
        List<SolWeatherData> data = new ArrayList<>();
        Gson gson = new Gson();
        try {
            JSONObject json = new JSONObject(jsonString);
            JSONArray solKeys = json.getJSONArray("sol_keys");
            Log.i(TAG, "Keys: " + solKeys);
            for (int i = 0; i < solKeys.length(); i++) {
                String sol_idx = solKeys.get(i).toString();
                JSONObject sol = json.getJSONObject(sol_idx);

                SolWeatherData solWeatherData = gson.fromJson(String.valueOf(sol), SolWeatherData.class);
                solWeatherData.setSol(Integer.parseInt(sol_idx));

                data.add(solWeatherData);
            }
            for (SolWeatherData solWeatherData: data) {
                Log.i(TAG, solWeatherData.toString());
            }
        } catch (JSONException e) {
            Log.e(TAG, "Error during parsing json data: ", e);
        }
        return new MutableLiveData<>(data);
    }
}
