package com.example.weatherapp.data.weather.model.raw

import com.google.gson.annotations.SerializedName

data class DailyUnits(

    @SerializedName("time"               ) var time             : String? = null,
    @SerializedName("weather_code"       ) var weatherCode      : String? = null,
    @SerializedName("temperature_2m_max" ) var maxTemperature   : String? = null,
    @SerializedName("temperature_2m_min" ) var minTemperature   : String? = null

)
