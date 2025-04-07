package com.example.weatherapp.data.weather.model.processed

data class HourlyWeather (
    var time         : String? = null,
    var temperature  : Int?    = null,
    var weather      : String? = null
)