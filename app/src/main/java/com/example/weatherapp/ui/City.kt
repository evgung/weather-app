package com.example.weatherapp.ui

enum class City(
    val apiName: String,
    val displayName: String
) {
    IZHEVSK("Izhevsk", "Ижевск"),
    MOSCOW("Moscow", "Москва"),
    SAINT_PETERSBURG("Saint Petersburg", "Санкт-Петербург"),
    YEKATERINBURG("Yekaterinburg", "Екатеринбург"),
    NOVOSIBIRSK("Novosibirsk", "Новосибирск"),
    KAZAN("Kazan", "Казань"),
    SOCHI("Sochi", "Сочи");

    companion object {
        fun fromApiName(apiName: String): City? {
            return values().find { it.apiName.equals(apiName, ignoreCase = true) }
        }
    }
}