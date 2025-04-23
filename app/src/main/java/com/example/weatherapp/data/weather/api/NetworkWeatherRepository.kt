package com.example.weatherapp.data.weather.api

import com.example.weatherapp.data.weather.model.processed.CurrentWeather
import com.example.weatherapp.data.weather.model.processed.DailyWeather
import com.example.weatherapp.data.weather.model.processed.HourlyWeather
import com.example.weatherapp.data.weather.model.processed.WeatherConverter
import com.example.weatherapp.data.weather.model.raw.WeatherInfo
import com.example.weatherapp.ui.enums.TemperatureUnit
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
        params: List<String>,
        temperatureUnit: String,
        windSpeedUnit: String
    ) : WeatherInfo

    suspend fun getCurrentWeather(
        lat: Double,
        lon: Double,
        params: List<String>,
        temperatureUnit: String,
        windSpeedUnit: String
    ) : CurrentWeather

    suspend fun getHourlyWeather(
        lat: Double,
        lon: Double,
        params: List<String>,
        temperatureUnit: String,
        windSpeedUnit: String
    ) : List<HourlyWeather>

    suspend fun getDailyWeather(
        lat: Double,
        lon: Double,
        params: List<String>,
        temperatureUnit: String,
        windSpeedUnit: String,
        forecastDays: Int,
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
        params: List<String>,
        temperatureUnit: String,
        windSpeedUnit: String
    ): WeatherInfo = when (forecastType) {

        ForecastType.CURRENT -> weatherApi.getWeather(
            lat,
            lon,
            params.joinToString(","),
            null,
            null,
            temperatureUnit,
            windSpeedUnit,
            forecastDays
        )

        ForecastType.HOURLY -> weatherApi.getWeather(
            lat,
            lon,
            null,
            params.joinToString(","),
            null,
            temperatureUnit,
            windSpeedUnit,
            forecastDays
        )

        ForecastType.DAILY -> weatherApi.getWeather(
            lat,
            lon,
            null,
            null,
            params.joinToString(","),
            temperatureUnit,
            windSpeedUnit,
            forecastDays
        )
    }

    override suspend fun getCurrentWeather(
        lat: Double,
        lon: Double,
        params: List<String>,
        temperatureUnit: String,
        windSpeedUnit: String
    ): CurrentWeather {
        val weather = getWeather(
            lat,
            lon,
            1,
            ForecastType.CURRENT,
            params,
            temperatureUnit,
            windSpeedUnit
        ).current

        return CurrentWeather(
            weather?.time,
            weather?.interval,
            weather?.temperature?.roundToInt(),
            weather?.relativeHumidity,
            weather?.apparentTemperature?.roundToInt(),
            WeatherConverter.weatherCodeToString(weather?.weatherCode),
            WeatherConverter.pressureTommhg(weather?.pressureMsl),
            weather?.windSpeed?.roundToInt()
        )
    }

    override suspend fun getHourlyWeather(
        lat: Double,
        lon: Double,
        params: List<String>,
        temperatureUnit: String,
        windSpeedUnit: String
    ): List<HourlyWeather> {
        val weather = getWeather(
            lat,
            lon,
            1,
            ForecastType.HOURLY,
            params,
            temperatureUnit,
            windSpeedUnit
        ).hourly
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
        temperatureUnit: String,
        windSpeedUnit: String,
        forecastDays: Int,
    ): List<DailyWeather> {
        val weather = getWeather(
            lat,
            lon,
            forecastDays,
            ForecastType.DAILY,
            params,
            temperatureUnit,
            windSpeedUnit
        ).daily
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