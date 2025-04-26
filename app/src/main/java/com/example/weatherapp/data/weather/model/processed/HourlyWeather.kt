package com.example.weatherapp.data.weather.model.processed

data class HourlyWeather (

    val time         : String,
    val weatherCode  : Int?     = null,
    val temperature  : Double?  = null,
    val isDay        : Boolean? = null

)