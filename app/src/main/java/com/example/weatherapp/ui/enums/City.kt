package com.example.weatherapp.ui.enums

enum class City  (
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
        fun getApiNames() = entries.map { it.apiName }.toTypedArray()
        fun getDisplayNames() = entries.map { it.displayName }.toTypedArray()
        fun findByApiName(apiName: String)
            = entries.find { it.apiName == apiName }
            ?: entries[0]

        fun default() = IZHEVSK
    }
}