package com.example.weatherapp.ui.enums

enum class WindSpeedUnit(
    val apiName: String,
    val displayName: String
) {
    MS("ms", "м/с"),
    KMH("kmh", "км/ч");

    companion object {
        fun getApiNames() = entries.map { it.apiName }.toTypedArray()
        fun getDisplayNames() = entries.map { it.displayName }.toTypedArray()
        fun findByApiName(apiName: String)
            = entries.find { it.apiName == apiName }
            ?: entries[0]

        fun default() = MS
    }
}