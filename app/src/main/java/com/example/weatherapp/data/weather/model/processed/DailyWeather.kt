package com.example.weatherapp.data.weather.model.processed

data class DailyWeather (

    var day            : String? = null,
    var weather        : String? = null,
    var maxTemperature : Int?    = null,
    var minTemperature : Int?    = null

)