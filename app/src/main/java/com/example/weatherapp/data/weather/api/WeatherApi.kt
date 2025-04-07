package com.example.weatherapp.data.weather.api

import com.example.weatherapp.data.weather.model.raw.WeatherInfo
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("forecast")
    suspend fun getWeather(
        @Query("latitude") lat: Double,
        @Query("longitude") lon: Double,
        @Query("current") current: String?,
        @Query("hourly") hourly: String?,
        @Query("daily") daily: String?,
        @Query("forecast_days") forecastDays: Int = 1,
        @Query("timezone") timezone: String = "auto",
        @Query("wind_speed_unit") windSpeedUnit: String = "ms"
    ): WeatherInfo
}