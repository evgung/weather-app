package com.example.weatherapp.ui.objects

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.weatherapp.R
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale
import java.util.TimeZone
import kotlin.math.roundToInt

class WeatherConverter (
        private val context: Context
) {
        fun pressureTommhg(pressurehPa: Double?): Int?
                = pressurehPa?.let { (it * 0.750062).roundToInt() }


        fun temperatureToString(temperature: Double?): String {
                //var string = getString(R.string.weather_without_space, temperature, userPrefManager.preferences.tempUnit.displayName)
                var string = context.getString(R.string.temperature, temperature?.roundToInt())
                if (temperature != null && temperature > 0)
                        string = "+$string"
                return string
        }

        fun isoTimeToHHMM(time: String?) : String {
                val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.getDefault())
                val date = sdf.parse(time)
                val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
                return timeFormat.format(date)
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