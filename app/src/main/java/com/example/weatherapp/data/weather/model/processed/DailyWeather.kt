package com.example.weatherapp.data.weather.model.processed

data class DailyWeather (

    val day            : String,
    val weatherCode    : Int?    = null,
    val maxTemperature : Double? = null,
    val minTemperature : Double? = null

)