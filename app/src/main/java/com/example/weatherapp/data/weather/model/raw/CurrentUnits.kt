package com.example.weatherapp.data.weather.model.raw

import com.google.gson.annotations.SerializedName


data class CurrentUnits (

  @SerializedName("time"                 ) val time                : String,
  @SerializedName("interval"             ) val interval            : String,
  @SerializedName("temperature_2m"       ) val temperature         : String? = null,
  @SerializedName("relative_humidity_2m" ) val relativeHumidity    : String? = null,
  @SerializedName("apparent_temperature" ) val apparentTemperature : String? = null,
  @SerializedName("weather_code"         ) val weatherCode         : String? = null,
  @SerializedName("pressure_msl"         ) val pressureMsl         : String? = null

)