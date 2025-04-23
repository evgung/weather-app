package com.example.weatherapp.ui.enums

enum class TemperatureUnit(
    val apiName: String,
    val displayName: String
) {
    CELSIUS("celsius", "°C"),
    FAHRENHEIT("fahrenheit", "°F");

    companion object {
        fun getApiNames() = entries.map { it.apiName }.toTypedArray()
        fun getDisplayNames() = entries.map { it.displayName }.toTypedArray()
        fun findByApiName(apiName: String)
            = entries.find { it.apiName == apiName }
            ?: entries[0]

        fun default() = CELSIUS
    }
}