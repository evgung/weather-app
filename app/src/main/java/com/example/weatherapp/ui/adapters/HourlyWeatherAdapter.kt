package com.example.weatherapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.data.weather.model.processed.HourlyWeather
import com.example.weatherapp.databinding.ItemHourlyWeatherBinding
import com.example.weatherapp.ui.objects.WeatherConverter

class HourlyWeatherAdapter(
    private val items: List<HourlyWeather>,
    private val weatherConverter: WeatherConverter
) : RecyclerView.Adapter<HourlyWeatherAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemHourlyWeatherBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHourlyWeatherBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        with (holder.binding) {
            tvTime.text = weatherConverter.isoTimeToHHMM(item.time)
            tvTemp.text = weatherConverter.temperatureToString(item.temperature)
            tvHourlyWeather.text = weatherConverter.weatherCodeToString(item.weatherCode)
        }
    }

    override fun getItemCount() = items.size
}