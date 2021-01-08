package com.romankurilenko40.marsweather.model;

import com.google.gson.annotations.SerializedName;

import java.util.Locale;

public class WindSpeedData {
    @SerializedName("av") private double mAvgSpeed;
    @SerializedName("ct") private int mRecordsNumber;
    @SerializedName("mn") private double mMinSpeed;
    @SerializedName("mx") private double mMaxSpeed;

    public double getAvgSpeed() {
        return mAvgSpeed;
    }

    public void setAvgSpeed(double avgSpeed) {
        mAvgSpeed = avgSpeed;
    }

    public int getRecordsNumber() {
        return mRecordsNumber;
    }

    public void setRecordsNumber(int recordsNumber) {
        mRecordsNumber = recordsNumber;
    }

    public double getMinSpeed() {
        return mMinSpeed;
    }

    public void setMinSpeed(double minSpeed) {
        mMinSpeed = minSpeed;
    }

    public double getMaxSpeed() {
        return mMaxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        mMaxSpeed = maxSpeed;
    }

    @Override
    public String toString() {
        return "WindSpeedData{" +
                "mAvgSpeed=" + mAvgSpeed +
                ", mRecordsNumber=" + mRecordsNumber +
                ", mMinSpeed=" + mMinSpeed +
                ", mMaxSpeed=" + mMaxSpeed +
                '}';
    }

    /**
     * return min and max value divided by '..' in one string
     * @return
     */
    public String getWindSpeedRangeString() {
        return String.format(Locale.US, "%.1f .. %.1f m/s", mMinSpeed, mMaxSpeed);
    }
}
