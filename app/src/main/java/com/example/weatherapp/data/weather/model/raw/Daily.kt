package com.example.weatherapp.data.weather.model.raw

import com.google.gson.annotations.SerializedName

data class Daily(

    @SerializedName("time"               ) var days              : List<String>? = null,
    @SerializedName("weather_code"       ) var weatherCodes      : List<Int>?    = null,
    @SerializedName("temperature_2m_max" ) var maxTemperatures   : List<Double>? = null,
    @SerializedName("temperature_2m_min" ) var minTemperatures   : List<Double>? = null

)
