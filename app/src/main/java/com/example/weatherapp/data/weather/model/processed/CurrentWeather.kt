package com.example.weatherapp.data.weather.model.processed

data class CurrentWeather (

    val time                : String? = null,
    val interval            : Int?    = null,
    val temperature         : Int?    = null,
    val relativeHumidity    : Int?    = null,
    val apparentTemperature : Int?    = null,
    val weatherType         : String? = null,
    val pressure            : Int?    = null,
    val windSpeed           : Int?    = null,

)