package com.example.weatherapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.data.weather.model.processed.DailyWeather
import com.example.weatherapp.databinding.ItemDailyWeatherBinding
import com.example.weatherapp.ui.objects.WeatherConverter

class DailyWeatherAdapter (
    private val items: List<DailyWeather>,
    private val weatherConverter: WeatherConverter
) : RecyclerView.Adapter<DailyWeatherAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemDailyWeatherBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDailyWeatherBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        with (holder.binding) {
            tvDate.text = weatherConverter.isoTimeToDate(item.day)
            tvMaxTemp.text = weatherConverter.temperatureToString(item.maxTemperature)
            tvMinTemp.text = weatherConverter.temperatureToString(item.minTemperature)
            ivDailyWeather.setImageResource(weatherConverter.weatherCodeToIconId(item.weatherCode, 1))
        }
    }

    override fun getItemCount() = items.size
}