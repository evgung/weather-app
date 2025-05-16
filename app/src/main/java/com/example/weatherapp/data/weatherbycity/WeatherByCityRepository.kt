package com.example.weatherapp.data.weatherbycity

import com.example.weatherapp.data.city.api.CityRepository
import com.example.weatherapp.data.weather.api.WeatherRepository
import com.example.weatherapp.data.weather.model.processed.CurrentWeather
import com.example.weatherapp.data.weather.model.processed.DailyWeather
import com.example.weatherapp.data.weather.model.processed.HourlyWeather
import com.example.weatherapp.data.weather.model.processed.Weather
import com.example.weatherapp.data.weather.model.raw.WeatherResponse
import com.example.weatherapp.ui.objects.ApiParameters
import com.example.weatherapp.ui.viewmodels.UserPreferences

class WeatherByCityRepository(
    private val weatherRepository: WeatherRepository,
    private val cityRepository: CityRepository
) : IWeatherByCityRepository {

    override suspend fun getWeather(
        params: ApiParameters,
        userPreferences: UserPreferences
    ) : Weather {
        val city = cityRepository.getCityInfo(userPreferences.city.apiName)
        val weather = weatherRepository.getWeather(
            city.latitude,
            city.longitude,
            params,
            userPreferences
        )
        return weather
    }

}