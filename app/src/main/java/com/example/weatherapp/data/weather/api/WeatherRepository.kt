package com.example.weatherapp.data.weather.api

import com.example.weatherapp.data.weather.model.processed.Weather
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