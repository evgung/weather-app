package com.example.weatherapp.ui.objects

interface ApiParameters {
    val daysCount: Int
    val currentParams: List<String>
    val hourlyParams: List<String>
    val dailyParams: List<String>
}

object DefaultApiParameters : ApiParameters {
    override val daysCount: Int = 7

    override val currentParams: List<String> = listOf(
        "temperature_2m",
        "relative_humidity_2m",
        "apparent_temperature",
        "weather_code",
        "pressure_msl",
        "wind_speed_10m",
        "is_day"
    )

    override val hourlyParams: List<String> = listOf(
        "temperature_2m",
        "weather_code",
        "is_day"
    )

    override val dailyParams: List<String> = listOf(
        "weather_code",
        "temperature_2m_max",
        "temperature_2m_min",
    )
}