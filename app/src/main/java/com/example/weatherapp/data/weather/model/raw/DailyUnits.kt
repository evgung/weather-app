package com.example.weatherapp.data.weather.model.raw

import com.google.gson.annotations.SerializedName

data class DailyUnits(

    @SerializedName("time"               ) val time             : String,
    @SerializedName("weather_code"       ) val weatherCode      : String? = null,
    @SerializedName("temperature_2m_max" ) val maxTemperature   : String? = null,
    @SerializedName("temperature_2m_min" ) val minTemperature   : String? = null

)
