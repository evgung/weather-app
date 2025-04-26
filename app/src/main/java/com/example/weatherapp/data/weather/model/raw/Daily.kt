package com.example.weatherapp.data.weather.model.raw

import com.google.gson.annotations.SerializedName

data class Daily(

    @SerializedName("time"               ) val days              : List<String>,
    @SerializedName("weather_code"       ) val weatherCodes      : List<Int>?    = null,
    @SerializedName("temperature_2m_max" ) val maxTemperatures   : List<Double>? = null,
    @SerializedName("temperature_2m_min" ) val minTemperatures   : List<Double>? = null

)
