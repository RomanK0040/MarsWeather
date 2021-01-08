package com.romankurilenko40.marsweather.model;

import com.google.gson.annotations.SerializedName;

import java.util.Locale;

public class TemperatureData {
    @SerializedName("av") private double mAvgTemp;
    @SerializedName("ct") private int mRecordsNumber;
    @SerializedName("mn") private double mMinTemp;
    @SerializedName("mx") private double mMaxTemp;

    public double getAvgTemp() {
        return mAvgTemp;
    }

    public void setAvgTemp(double avgTemp) {
        mAvgTemp = avgTemp;
    }

    public int getRecordsNumber() {
        return mRecordsNumber;
    }

    public void setRecordsNumber(int recordsNumber) {
        mRecordsNumber = recordsNumber;
    }

    public double getMinTemp() {
        return mMinTemp;
    }

    public void setMinTemp(double minTemp) {
        mMinTemp = minTemp;
    }

    public double getMaxTemp() {
        return mMaxTemp;
    }

    public void setMaxTemp(double maxTemp) {
        mMaxTemp = maxTemp;
    }

    @Override
    public String toString() {
        return "TemperatureData{" +
                "mAvgTemp=" + mAvgTemp +
                ", mRecordsNumber=" + mRecordsNumber +
                ", mMinTemp=" + mMinTemp +
                ", mMaxTemp=" + mMaxTemp +
                '}';
    }

    /**
     * return min and max value divided by '..' in one string
     * @return
     */
    public String getTemperatureRangeString() {
        return String.format(Locale.US, "%.1f .. %.1f \u2103", mMinTemp, mMaxTemp);
    }
}
