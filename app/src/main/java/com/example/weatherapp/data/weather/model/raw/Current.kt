package com.example.weatherapp.data.weather.model.raw

import com.google.gson.annotations.SerializedName


data class Current (

  @SerializedName("time"                 ) var time                : String? = null,
  @SerializedName("interval"             ) var interval            : Int?    = null,
  @SerializedName("temperature_2m"       ) var temperature         : Double? = null,
  @SerializedName("relative_humidity_2m" ) var relativeHumidity    : Int?    = null,
  @SerializedName("apparent_temperature" ) var apparentTemperature : Double? = null,
  @SerializedName("weather_code"         ) var weatherCode         : Int?    = null,
  @SerializedName("pressure_msl"         ) var pressureMsl         : Double? = null,
  @SerializedName("wind_speed_10m"       ) var windSpeed           : Double? = null

)