package com.example.weatherapp.data.weather.model.processed

import kotlin.math.roundToInt

object WeatherConverter {
        fun pressureTommhg(pressurehPa: Double?): Int? {
                return pressurehPa?.let { (it * 0.750062).roundToInt() }
        }

    fun weatherCodeToString(weatherCode: Int?) : String?
        = when(weatherCode) {
            0 -> "Ясно"
            1 -> "Преимущественно ясно"
            2 -> "Переменная облачность"
            3 -> "Пасмурно"
            45, 48 -> "Туман"
            51, 53, 55 -> "Изморось"
            56, 57 -> "Ледяная изморось"
            61, 63, 65 -> "Дождь"
            66, 67 -> "Ледяной дождь"
            71, 73, 75 -> "Снег"
            77 -> "Град"
            80, 81, 82 -> "Ливень"
            85, 86 -> "Снегопад"
            95 -> "Гроза"
            96, 99 -> "Гроза с градом"
            else -> null
        }
}