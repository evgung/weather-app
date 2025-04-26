package com.example.weatherapp.data.weather.model.processed

data class CurrentWeather (

    val time                : String?  = null,
    val weatherCode         : Int?     = null,
    val temperature         : Double?  = null,
    val apparentTemperature : Double?  = null,
    val relativeHumidity    : Int?     = null,
    val pressure            : Double?  = null,
    val windSpeed           : Double?  = null,
    val isDay               : Boolean? = null

)