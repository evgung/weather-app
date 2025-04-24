package com.example.weatherapp.data.weather.api

import com.example.weatherapp.data.weather.model.processed.*
import com.example.weatherapp.data.weather.model.raw.*
import com.example.weatherapp.ui.objects.ApiParameters
import com.example.weatherapp.ui.viewmodels.UserPreferences

interface WeatherRepository {
    suspend fun getWeather(
        lat: Double,
        lon: Double,
        params: ApiParameters,
        userPreferences: UserPreferences
    ) : Weather
}

class NetworkWeatherRepository(
    private val weatherApi: WeatherApi
) : WeatherRepository {

    override suspend fun getWeather(
        lat: Double,
        lon: Double,
        params: ApiParameters,
        userPreferences: UserPreferences
    ) : Weather {
        val response = weatherApi.getWeather(
            lat,
            lon,
            params.currentParams.joinToString(","),
            params.hourlyParams.joinToString(","),
            params.dailyParams.joinToString(","),
            userPreferences.tempUnit.apiName,
            userPreferences.windUnit.apiName,
            params.daysCount
        )

        return Weather(
            getCurrentWeather(response.current),
            getHourlyWeather(response.hourly),
            getDailyWeather(response.daily)
        )
    }

    private fun getCurrentWeather(
        weather: Current?
    ): CurrentWeather {
        return CurrentWeather(
            weather?.time,
            weather?.weatherCode,
            weather?.temperature,
            weather?.apparentTemperature,
            weather?.relativeHumidity,
            weather?.pressureMsl,
            weather?.windSpeed
        )
    }

    private fun getHourlyWeather(
        weather: Hourly?
    ): List<HourlyWeather> {
        val result = mutableListOf<HourlyWeather>()

        weather?.times?.forEachIndexed { index, hour ->
            result.add(HourlyWeather(
                hour,
                weather.weatherCodes?.get(index),
                weather.temperatures?.get(index)
            ))
        }

        return result
    }

    private fun getDailyWeather(
        weather: Daily?
    ): List<DailyWeather> {
        val result = mutableListOf<DailyWeather>()

        weather?.days?.forEachIndexed { index, day ->
            result.add(DailyWeather(
                day,
                weather.weatherCodes?.get(index),
                weather.maxTemperatures?.get(index),
                weather.minTemperatures?.get(index),
            ))
        }

        return result
    }

}