package com.example.weatherapp.data.weather.api

import com.example.weatherapp.data.weather.model.raw.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("forecast?timezone=auto&forecast_hours=24")
    suspend fun getWeather(
        @Query("latitude") lat: Double,
        @Query("longitude") lon: Double,
        @Query("current") current: String,
        @Query("hourly") hourly: String,
        @Query("daily") daily: String,
        @Query("temperature_unit") temperatureUnit: String,
        @Query("wind_speed_unit") windSpeedUnit: String,
        @Query("forecast_days") forecastDays: Int,
    ): WeatherResponse
}