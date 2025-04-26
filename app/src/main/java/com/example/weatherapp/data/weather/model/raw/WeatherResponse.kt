package com.example.weatherapp.data.weather.model.raw

import com.google.gson.annotations.SerializedName


data class WeatherResponse (

  @SerializedName("latitude"              ) val latitude             : Double,
  @SerializedName("longitude"             ) val longitude            : Double,
  @SerializedName("generationtime_ms"     ) val generationTimeMs     : Double,
  @SerializedName("utc_offset_seconds"    ) val utcOffsetSeconds     : Int,
  @SerializedName("timezone"              ) val timezone             : String,
  @SerializedName("timezone_abbreviation" ) val timezoneAbbreviation : String,
  @SerializedName("elevation"             ) val elevation            : Int,
  @SerializedName("current_units"         ) val currentUnits         : CurrentUnits? = null,
  @SerializedName("current"               ) val current              : Current?      = null,
  @SerializedName("hourly_units"          ) val hourlyUnits          : HourlyUnits?  = null,
  @SerializedName("hourly"                ) val hourly               : Hourly?       = null,
  @SerializedName("daily_units"           ) val dailyUnits           : DailyUnits?   = null,
  @SerializedName("daily"                 ) val daily                : Daily?        = null

)