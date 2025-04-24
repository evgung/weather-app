package com.example.weatherapp.data.weather.model.processed

data class HourlyWeather (
    var time         : String? = null,
    var weatherCode  : Int?    = null,
    var temperature  : Double? = null,
)