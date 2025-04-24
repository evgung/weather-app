package com.example.weatherapp.data.weather.model.processed

data class DailyWeather (

    var day            : String? = null,
    var weatherCode    : Int?    = null,
    var maxTemperature : Double? = null,
    var minTemperature : Double? = null

)