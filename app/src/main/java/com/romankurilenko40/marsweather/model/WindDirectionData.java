package com.romankurilenko40.marsweather.model;

import com.google.gson.annotations.SerializedName;

import java.util.Locale;

public class WindDirectionData {
    @SerializedName("most_common")
    private WindDirectionCommonData mCommonData;

    public WindDirectionCommonData getCommonData() {
        return mCommonData;
    }

    public void setCommonData(WindDirectionCommonData commonData) {
        mCommonData = commonData;
    }

    @Override
    public String toString() {
        return "WindDirectionData{" +
                "mCommonData=" + mCommonData +
                '}';
    }

    public String getWindDirectionString() {
        return mCommonData.getCompasPoint();
    }
}
