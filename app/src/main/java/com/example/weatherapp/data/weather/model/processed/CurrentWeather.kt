package com.example.weatherapp.data.weather.model.processed

data class CurrentWeather (

    var time                : String? = null,
    var interval            : Int?    = null,
    var temperature         : Int?    = null,
    var relativeHumidity    : Int?    = null,
    var apparentTemperature : Int?    = null,
    var weatherType         : String? = null,
    var pressure            : Int?    = null

)