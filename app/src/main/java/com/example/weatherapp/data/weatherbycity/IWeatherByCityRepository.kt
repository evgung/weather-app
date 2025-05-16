package com.example.weatherapp.data.weatherbycity

import com.example.weatherapp.data.weather.model.processed.Weather
import com.example.weatherapp.ui.objects.ApiParameters
import com.example.weatherapp.ui.viewmodels.UserPreferences

interface IWeatherByCityRepository {
    suspend fun getWeather(
        params: ApiParameters,
        userPreferences: UserPreferences
    ) : Weather
}