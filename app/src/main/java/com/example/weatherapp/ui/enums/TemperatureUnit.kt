package com.example.weatherapp.ui.enums

enum class TemperatureUnit(
    val apiName: String,
    val displayName: String
) {
    CELSIUS("celsius", "°C"),
    FAHRENHEIT("fahrenheit", "°F");
}