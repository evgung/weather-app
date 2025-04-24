package com.example.weatherapp.data.weather.model.processed

data class HourlyWeather (
    val time         : String?  = null,
    val weatherCode  : Int?     = null,
    val temperature  : Double?  = null,
    val isDay        : Int?     = null
)