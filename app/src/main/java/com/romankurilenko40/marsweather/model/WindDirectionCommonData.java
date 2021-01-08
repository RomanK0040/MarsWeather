package com.romankurilenko40.marsweather.model;

import com.google.gson.annotations.SerializedName;

public class WindDirectionCommonData {
    @SerializedName("compass_degrees") private double mCompasDegrees;
    @SerializedName("compass_point") private String mCompasPoint;
    @SerializedName("compass_right") private double mCompasRight;
    @SerializedName("compass_up") private double mCompasUp;
    @SerializedName("ct") private int mRecordsNumber;

    public double getCompasDegrees() {
        return mCompasDegrees;
    }

    public void setCompasDegrees(double compasDegrees) {
        mCompasDegrees = compasDegrees;
    }

    public String getCompasPoint() {
        return mCompasPoint;
    }

    public void setCompasPoint(String compasPoint) {
        mCompasPoint = compasPoint;
    }

    public double getCompasRight() {
        return mCompasRight;
    }

    public void setCompasRight(double compasRight) {
        mCompasRight = compasRight;
    }

    public double getCompasUp() {
        return mCompasUp;
    }

    public void setCompasUp(double compasUp) {
        mCompasUp = compasUp;
    }

    public int getRecordsNumber() {
        return mRecordsNumber;
    }

    public void setRecordsNumber(int recordsNumber) {
        mRecordsNumber = recordsNumber;
    }

    @Override
    public String toString() {
        return "WindDirectionCommonData{" +
                "mCompasDegrees=" + mCompasDegrees +
                ", mCompasPoint='" + mCompasPoint + '\'' +
                ", mCompasRight=" + mCompasRight +
                ", mCompasUp=" + mCompasUp +
                ", mRecordsNumber=" + mRecordsNumber +
                '}';
    }
}
