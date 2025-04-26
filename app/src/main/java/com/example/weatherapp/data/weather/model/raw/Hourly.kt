package com.example.weatherapp.data.weather.model.raw

import com.google.gson.annotations.SerializedName


data class Hourly (

  @SerializedName("time"           ) val times          : List<String>,
  @SerializedName("temperature_2m" ) val temperatures   : List<Double>? = null,
  @SerializedName("weather_code"   ) val weatherCodes   : List<Int>?    = null,
  @SerializedName("is_day"         ) val isDay          : List<Int>?    = null

)