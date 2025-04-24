package com.example.weatherapp.ui.objects

import android.content.Context
import com.example.weatherapp.R
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.math.roundToInt

class WeatherConverter (
        private val context: Context
) {
        fun pressureTommhg(pressurehPa: Double?): Int?
                = pressurehPa?.let { (it * 0.750062).roundToInt() }


        fun temperatureToString(temperature: Double?): String {
                val temperatureInt = temperature?.roundToInt()
                var string = context.getString(R.string.temperature, temperatureInt)
                if (temperatureInt != null && temperatureInt > 0)
                        string = "+$string"
                return string
        }

        fun isoTimeToTime(time: String?) : String
                = convertIsoTime(time, "yyyy-MM-dd'T'HH:mm", "HH:mm")

        fun isoTimeToDate(time: String?) : String
                = convertIsoTime(time, "yyyy-MM-dd", "dd.MM")

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
            96, 99 -> "Дождь с грозой"
            else -> null
        }

        fun weatherCodeToIconId(weatherCode: Int?, isDay: Int?) : Int
        = when (weatherCode) {
                0, 1 -> if (isDay == 1) R.drawable.ic_clear_day else R.drawable.ic_clear_night
                2 -> if (isDay == 1) R.drawable.ic_cloudy_day else R.drawable.ic_cloudy_night
                3 -> R.drawable.ic_cloudy
                45, 48 -> R.drawable.ic_fog
                51, 53, 55 -> R.drawable.ic_rainy_1
                56, 57 -> R.drawable.ic_snowy_rain_1
                61, 63, 65 -> R.drawable.ic_rainy_2
                66, 67 -> R.drawable.ic_snowy_rain_2
                71, 73, 75 -> R.drawable.ic_snowy_2
                77 -> R.drawable.ic_hail
                80, 81, 82 -> R.drawable.ic_rainy_3
                85, 86 -> R.drawable.ic_snowy_3
                95 -> R.drawable.ic_thunder
                96, 99 -> R.drawable.ic_thunder_rain
                else -> R.drawable.ic_empty
        }

        private fun convertIsoTime(time: String?, patternFrom: String, patternTo: String) : String {
                val sdf = SimpleDateFormat(patternFrom, Locale.getDefault())
                val date = sdf.parse(time)
                val timeFormat = SimpleDateFormat(patternTo, Locale.getDefault())
                return timeFormat.format(date)
        }
}