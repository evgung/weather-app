package com.example.weatherapp.data.weather.model.raw

import com.google.gson.annotations.SerializedName


data class HourlyUnits (

  @SerializedName("time"           ) val time          : String,
  @SerializedName("temperature_2m" ) val temperature   : String? = null,
  @SerializedName("weather_code"   ) val weatherCode   : String? = null

)
