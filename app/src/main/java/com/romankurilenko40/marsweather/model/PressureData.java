package com.romankurilenko40.marsweather.model;

import com.google.gson.annotations.SerializedName;

import java.util.Locale;

public class PressureData {
    @SerializedName("av") private double mAvgPres;
    @SerializedName("ct") private int mRecordsNumber;
    @SerializedName("mn") private double mMinPres;
    @SerializedName("mx") private double mMaxPres;

    public double getAvgPres() {
        return mAvgPres;
    }

    public void setAvgPres(double avgPres) {
        mAvgPres = avgPres;
    }

    public int getRecordsNumber() {
        return mRecordsNumber;
    }

    public void setRecordsNumber(int recordsNumber) {
        mRecordsNumber = recordsNumber;
    }

    public double getMinPres() {
        return mMinPres;
    }

    public void setMinPres(double minPres) {
        mMinPres = minPres;
    }

    public double getMaxPres() {
        return mMaxPres;
    }

    public void setMaxPres(double maxPres) {
        mMaxPres = maxPres;
    }

    @Override
    public String toString() {
        return "PressureData{" +
                "mAvgPres=" + mAvgPres +
                ", mRecordsNumber=" + mRecordsNumber +
                ", mMinPres=" + mMinPres +
                ", mMaxPres=" + mMaxPres +
                '}';
    }

    /**
     * return min and max value divided by '..' in one string
     * @return
     */
    public String getPressureRangeString() {
        return String.format(Locale.US, "%.1f .. %.1f Pa", mMinPres, mMaxPres);
    }
}
