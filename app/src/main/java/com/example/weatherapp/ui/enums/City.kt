package com.example.weatherapp.ui.enums

enum class City  (
    val apiName: String,
    val displayName: String
) {
    YEKATERINBURG("Yekaterinburg", "Екатеринбург"),
    IZHEVSK("Izhevsk", "Ижевск"),
    KAZAN("Kazan", "Казань"),
    MOSCOW("Moscow", "Москва"),
    NOVOSIBIRSK("Novosibirsk", "Новосибирск"),
    SAINT_PETERSBURG("Saint Petersburg", "Санкт-Петербург"),
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