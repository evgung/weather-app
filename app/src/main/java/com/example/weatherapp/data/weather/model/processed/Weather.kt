package com.example.weatherapp.data.weather.model.processed

data class Weather(

    val current : CurrentWeather,
    val hourly  : List<HourlyWeather>,
    val daily   : List<DailyWeather>

)
