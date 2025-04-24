package com.example.weatherapp.data.weather.model.raw

import com.google.gson.annotations.SerializedName


data class Hourly (

  @SerializedName("time"           ) var times          : List<String>? = null,
  @SerializedName("temperature_2m" ) var temperatures   : List<Double>? = null,
  @SerializedName("weather_code"   ) var weatherCodes   : List<Int>?    = null,
  @SerializedName("is_day"         ) var isDay          : List<Int>?    = null

)