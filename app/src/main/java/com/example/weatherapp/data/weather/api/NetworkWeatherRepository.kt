package com.example.weatherapp.data.weather.api

import com.example.weatherapp.data.weather.model.processed.CurrentWeather
import com.example.weatherapp.data.weather.model.processed.DailyWeather
import com.example.weatherapp.data.weather.model.processed.HourlyWeather
import com.example.weatherapp.data.weather.model.processed.WeatherConverter
import com.example.weatherapp.data.weather.model.raw.WeatherInfo
import com.example.weatherapp.ui.UserParameters
import kotlin.math.roundToInt

enum class ForecastType {
    CURRENT, HOURLY, DAILY
}

interface WeatherRepository {

    suspend fun getWeather(
        lat: Double,
        lon: Double,
        forecastDays: Int,
        forecastType: ForecastType,
        params: List<String>
    ) : WeatherInfo

    suspend fun getCurrentWeather(
        lat: Double,
        lon: Double,
        params: List<String>
    ) : CurrentWeather

    suspend fun getHourlyWeather(
        lat: Double,
        lon: Double,
        params: List<String>
    ) : List<HourlyWeather>

    suspend fun getDailyWeather(
        lat: Double,
        lon: Double,
        params: List<String>,
        forecastDays: Int
    ) : List<DailyWeather>

}

class NetworkWeatherRepository(
    private val weatherApi: WeatherApi
) : WeatherRepository {

    override suspend fun getWeather(
        lat: Double,
        lon: Double,
        forecastDays: Int,
        forecastType: ForecastType,
        params: List<String>
    ): WeatherInfo = when (forecastType) {

        ForecastType.CURRENT -> weatherApi.getWeather(
            lat,
            lon,
            params.joinToString(","),
            null,
            null,
            forecastDays
        )

        ForecastType.HOURLY -> weatherApi.getWeather(
            lat,
            lon,
            null,
            params.joinToString(","),
            null,
            forecastDays
        )

        ForecastType.DAILY -> weatherApi.getWeather(
            lat,
            lon,
            null,
            null,
            params.joinToString(","),
            forecastDays
        )
    }

    override suspend fun getCurrentWeather(
        lat: Double,
        lon: Double,
        params: List<String>
    ): CurrentWeather {
        val weather = getWeather(lat, lon, 1, ForecastType.CURRENT, params).current
        return CurrentWeather(
            weather?.time,
            weather?.interval,
            weather?.temperature?.roundToInt(),
            weather?.relativeHumidity,
            weather?.apparentTemperature?.roundToInt(),
            WeatherConverter.weatherCodeToString(weather?.weatherCode),
            WeatherConverter.pressureTommhg(weather?.pressureMsl)
        )
    }

    override suspend fun getHourlyWeather(
        lat: Double,
        lon: Double,
        params: List<String>
    ): List<HourlyWeather> {
        val weather = getWeather(lat, lon, 1, ForecastType.HOURLY, params).hourly
        val result: MutableList<HourlyWeather> = mutableListOf()
        if (weather == null)
            return result

        weather.times!!.forEachIndexed { index, hour ->
            result.add(HourlyWeather(
                hour,
                weather.temperatures?.get(index)?.roundToInt(),
                WeatherConverter.weatherCodeToString(weather.weatherCodes?.get(index))
            ))
        }

        return result
    }

    override suspend fun getDailyWeather(
        lat: Double,
        lon: Double,
        params: List<String>,
        forecastDays: Int
    ): List<DailyWeather> {
        val weather = getWeather(lat, lon, forecastDays, ForecastType.DAILY, params).daily
        val result: MutableList<DailyWeather> = mutableListOf()
        if (weather == null)
            return result

        weather.days!!.forEachIndexed { index, day ->
            result.add(DailyWeather(
                day,
                WeatherConverter.weatherCodeToString(weather.weatherCodes?.get(index)),
                weather.maxTemperatures?.get(index)?.roundToInt(),
                weather.minTemperatures?.get(index)?.roundToInt(),
            ))
        }

        return result
    }

}