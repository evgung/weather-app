package com.example.weatherapp.data.weather.model.raw

import com.google.gson.annotations.SerializedName


data class WeatherInfo (

  @SerializedName("latitude"              ) var latitude             : Double?       = null,
  @SerializedName("longitude"             ) var longitude            : Double?       = null,
  @SerializedName("generationtime_ms"     ) var generationTimeMs     : Double?       = null,
  @SerializedName("utc_offset_seconds"    ) var utcOffsetSeconds     : Int?          = null,
  @SerializedName("timezone"              ) var timezone             : String?       = null,
  @SerializedName("timezone_abbreviation" ) var timezoneAbbreviation : String?       = null,
  @SerializedName("elevation"             ) var elevation            : Int?          = null,
  @SerializedName("current_units"         ) var currentUnits         : CurrentUnits? = null,
  @SerializedName("current"               ) var current              : Current?      = null,
  @SerializedName("hourly_units"          ) var hourlyUnits          : HourlyUnits?  = null,
  @SerializedName("hourly"                ) var hourly               : Hourly?       = null,
  @SerializedName("daily_units"           ) var dailyUnits           : DailyUnits?   = null,
  @SerializedName("daily"                 ) var daily                : Daily?        = null

)