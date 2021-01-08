package com.romankurilenko40.marsweather.model;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class SolWeatherData {
    private int sol;
    @SerializedName("Last_UTC") private String mLastUTC;
    @SerializedName("Season") private String mSeason;
    @SerializedName("AT") private TemperatureData mTemperatureData;
    @SerializedName("HWS") private WindSpeedData mWindSpeedData;
    @SerializedName("PRE") private PressureData mPressureData;
    @SerializedName("WD") private WindDirectionData mWindDirectionData;


    public int getSol() {
        return sol;
    }

    public void setSol(int sol) {
        this.sol = sol;
    }

    public TemperatureData getTemperatureData() {
        return mTemperatureData;
    }

    public String getLastUTC() {
        return mLastUTC;
    }

    public void setLastUTC(String lastUTC) {
        mLastUTC = lastUTC;
    }

    public String getSeason() {
        return mSeason;
    }

    public void setSeason(String season) {
        mSeason = season;
    }

    public void setTemperatureData(TemperatureData temperatureData) {
        mTemperatureData = temperatureData;
    }

    public WindSpeedData getWindSpeedData() {
        return mWindSpeedData;
    }

    public void setWindSpeedData(WindSpeedData windSpeedData) {
        mWindSpeedData = windSpeedData;
    }

    public PressureData getPressureData() {
        return mPressureData;
    }

    public void setPressureData(PressureData pressureData) {
        mPressureData = pressureData;
    }

    public WindDirectionData getWindDirectionData() {
        return mWindDirectionData;
    }

    public void setWindDirectionData(WindDirectionData windDirectionData) {
        mWindDirectionData = windDirectionData;
    }

    @Override
    public String toString() {
        return "SolWeatherData{" +
                "sol=" + sol + ", " + mSeason + ", " + mLastUTC + ",\n" +
                "mTemperatureData=" + mTemperatureData + ",\n" +
                "mWindSpeedData=" + mWindSpeedData + ",\n" +
                "mPressureData=" + mPressureData + ",\n" +
                "mWindDirectionData=" + mWindDirectionData +
                '}';
    }

    /**
     * Formatting to received string contains UTC date like "yyyy-MM-dd'T'HH:mm:ss.SSSZ" into pattern "EEE, MMM d, ''yy"
     * @return - string like: Wed, Jul 4, '01
     */
    public String getFormattedDate() {
        TimeZone utc = TimeZone.getTimeZone("UTC");
        SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        sourceFormat.setTimeZone(utc);
        SimpleDateFormat destFormat = new SimpleDateFormat("EEE, MMM d, ''yy", Locale.US);
        destFormat.setTimeZone(utc);
        try {
            Date convertedDate = sourceFormat.parse(mLastUTC);
            return destFormat.format(convertedDate);
        } catch (ParseException pe) {
            return mLastUTC;
        }
    }
}
