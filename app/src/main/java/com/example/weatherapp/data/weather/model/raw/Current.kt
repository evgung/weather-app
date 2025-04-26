package com.example.weatherapp.data.weather.model.raw

import com.google.gson.annotations.SerializedName


data class Current (

  @SerializedName("time"                 ) val time                : String,
  @SerializedName("interval"             ) val interval            : Int,
  @SerializedName("temperature_2m"       ) val temperature         : Double? = null,
  @SerializedName("relative_humidity_2m" ) val relativeHumidity    : Int?    = null,
  @SerializedName("apparent_temperature" ) val apparentTemperature : Double? = null,
  @SerializedName("weather_code"         ) val weatherCode         : Int?    = null,
  @SerializedName("pressure_msl"         ) val pressureMsl         : Double? = null,
  @SerializedName("wind_speed_10m"       ) val windSpeed           : Double? = null,
  @SerializedName("is_day"               ) val isDay               : Int?    = null

)