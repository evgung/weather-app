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
        params: List<String>,
        temperatureUnit: String,
        windSpeedUnit: String,
    ) : WeatherInfo

    suspend fun getCurrentWeather(
        cityName: String,
        params: List<String>,
        temperatureUnit: String,
        windSpeedUnit: String,
    ) : CurrentWeather

    suspend fun getHourlyWeather(
        cityName: String,
        params: List<String>,
        temperatureUnit: String,
        windSpeedUnit: String,
    ) : List<HourlyWeather>

    suspend fun getDailyWeather(
        cityName: String,
        params: List<String>,
        temperatureUnit: String,
        windSpeedUnit: String,
        forecastDays: Int,
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
        params: List<String>,
        temperatureUnit: String,
        windSpeedUnit: String,
    ) : WeatherInfo {
        val city = cityRepository.getCityInfo(cityName)
        val weather = weatherRepository.getWeather(
            city.latitude,
            city.longitude,
            forecastDays,
            forecastType,
            params,
            temperatureUnit,
            windSpeedUnit
        )
        return weather
    }

    override suspend fun getCurrentWeather(
        cityName: String,
        params: List<String>,
        temperatureUnit: String,
        windSpeedUnit: String,
    ): CurrentWeather {
        val city = cityRepository.getCityInfo(cityName)
        val weather = weatherRepository.getCurrentWeather(
            city.latitude,
            city.longitude,
            params,
            temperatureUnit,
            windSpeedUnit
        )
        return weather
    }

    override suspend fun getHourlyWeather(
        cityName: String,
        params: List<String>,
        temperatureUnit: String,
        windSpeedUnit: String,
    ): List<HourlyWeather> {
        val city = cityRepository.getCityInfo(cityName)
        val weather = weatherRepository.getHourlyWeather(
            city.latitude,
            city.longitude,
            params,
            temperatureUnit,
            windSpeedUnit
        )
        return weather
    }

    override suspend fun getDailyWeather(
        cityName: String,
        params: List<String>,
        temperatureUnit: String,
        windSpeedUnit: String,
        forecastDays: Int,
    ): List<DailyWeather> {
        val city = cityRepository.getCityInfo(cityName)
        val weather = weatherRepository.getDailyWeather(
            city.latitude,
            city.longitude,
            params,
            temperatureUnit,
            windSpeedUnit,
            forecastDays
        )
        return weather
    }

}