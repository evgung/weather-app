package com.example.weatherapp.data.weatherbycity

import com.example.weatherapp.data.city.api.CityRepository
import com.example.weatherapp.data.weather.api.ForecastType
import com.example.weatherapp.data.weather.api.WeatherRepository
import com.example.weatherapp.data.weather.model.processed.CurrentWeather
import com.example.weatherapp.data.weather.model.processed.DailyWeather
import com.example.weatherapp.data.weather.model.processed.HourlyWeather
import com.example.weatherapp.data.weather.model.raw.WeatherInfo

interface IWeatherByCityRepository {
    suspend fun getWeather(
        cityName: String,
        forecastDays: Int,
        forecastType: ForecastType,
        params: List<String>
    ) : WeatherInfo

    suspend fun getCurrentWeather(
        cityName: String,
        params: List<String>
    ) : CurrentWeather

    suspend fun getHourlyWeather(
        cityName: String,
        params: List<String>
    ) : List<HourlyWeather>

    suspend fun getDailyWeather(
        cityName: String,
        params: List<String>,
        forecastDays: Int
    ) : List<DailyWeather>
}

class WeatherByCityRepository(
    private val weatherRepository: WeatherRepository,
    private val cityRepository: CityRepository
) : IWeatherByCityRepository {

    override suspend fun getWeather(
        cityName: String,
        forecastDays: Int,
        forecastType: ForecastType,
        params: List<String>
    ) : WeatherInfo {
        val city = cityRepository.getCityInfo(cityName)
        val weather = weatherRepository.getWeather(
            city.latitude,
            city.longitude,
            forecastDays,
            forecastType,
            params
        )
        return weather
    }

    override suspend fun getCurrentWeather(
        cityName: String,
        params: List<String>
    ): CurrentWeather {
        val city = cityRepository.getCityInfo(cityName)
        val weather = weatherRepository.getCurrentWeather(
            city.latitude,
            city.longitude,
            params
        )
        return weather
    }

    override suspend fun getHourlyWeather(
        cityName: String,
        params: List<String>
    ): List<HourlyWeather> {
        val city = cityRepository.getCityInfo(cityName)
        val weather = weatherRepository.getHourlyWeather(
            city.latitude,
            city.longitude,
            params
        )
        return weather
    }

    override suspend fun getDailyWeather(
        cityName: String,
        params: List<String>,
        forecastDays: Int
    ): List<DailyWeather> {
        val city = cityRepository.getCityInfo(cityName)
        val weather = weatherRepository.getDailyWeather(
            city.latitude,
            city.longitude,
            params,
            forecastDays
        )
        return weather
    }

}