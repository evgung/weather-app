package com.example.weatherapp.data.weather.model.raw

import com.google.gson.annotations.SerializedName


data class CurrentUnits (

  @SerializedName("time"                 ) var time                : String? = null,
  @SerializedName("interval"             ) var interval            : String? = null,
  @SerializedName("temperature_2m"       ) var temperature         : String? = null,
  @SerializedName("relative_humidity_2m" ) var relativeHumidity    : String? = null,
  @SerializedName("apparent_temperature" ) var apparentTemperature : String? = null,
  @SerializedName("weather_code"         ) var weatherCode         : String? = null,
  @SerializedName("pressure_msl"         ) var pressureMsl         : String? = null

)