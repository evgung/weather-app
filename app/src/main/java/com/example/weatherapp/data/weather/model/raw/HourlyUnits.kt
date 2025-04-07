package com.example.weatherapp.data.weather.model.raw

import com.google.gson.annotations.SerializedName


data class HourlyUnits (

  @SerializedName("time"           ) var time          : String? = null,
  @SerializedName("temperature_2m" ) var temperature   : String? = null,
  @SerializedName("weather_code"   ) var weatherCode   : String? = null

)
