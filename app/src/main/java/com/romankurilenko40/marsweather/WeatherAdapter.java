package com.romankurilenko40.marsweather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.romankurilenko40.marsweather.databinding.WeatherItemViewBinding;
import com.romankurilenko40.marsweather.model.SolWeatherData;

import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherItemHolder> {

    List<SolWeatherData> mWeatherData;
    LayoutInflater mInflater;

    interface OnSolItemClickListener{
        void onSolClick(SolWeatherData solWeatherData, int position);
    }
    private final OnSolItemClickListener mOnSolItemClickListener;

    public WeatherAdapter(Context context, OnSolItemClickListener listener) {
        mInflater = LayoutInflater.from(context);
        mOnSolItemClickListener = listener;
    }

    public class WeatherItemHolder extends RecyclerView.ViewHolder {
        private final WeatherItemViewBinding mBinding;

        public WeatherItemHolder(@NonNull WeatherItemViewBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(SolWeatherData data) {
            String solString = data.getSol() + "";
            mBinding.itemSolText.setText(solString);
            String tempString;
            if (data.getTemperatureData() != null) {
                tempString = data.getTemperatureData().getMinTemp() + ".."
                        + data.getTemperatureData().getMaxTemp() + " \u2103";
            } else {
                tempString = "NaN";
            }
            mBinding.itemTempText.setText(tempString);
        }

    }

    public void setWeatherData(List<SolWeatherData> weatherData) {
        mWeatherData = weatherData;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public WeatherItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        WeatherItemViewBinding viewBinding = WeatherItemViewBinding.inflate(mInflater, parent, false);
        return new WeatherItemHolder(viewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherItemHolder holder, int position) {
        if (mWeatherData != null) {
            SolWeatherData weatherData = mWeatherData.get(position);
            holder.bind(weatherData);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnSolItemClickListener.onSolClick(weatherData, position);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        if (mWeatherData != null) {
            return mWeatherData.size();
        } else {
            return 0;
        }
    }
}
