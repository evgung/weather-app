package com.example.weatherapp.ui

object ApiParameters {

    val daysCountForDailyWeather = 7

    val currentParams = listOf(
        "temperature_2m",
        "relative_humidity_2m",
        "apparent_temperature",
        "weather_code",
        "pressure_msl",
    )

    val hourlyParams = listOf(
        "temperature_2m",
        "weather_code",
    )

    val dailyParams = listOf(
        "weather_code",
        "temperature_2m_max",
        "temperature_2m_min",
    )

}