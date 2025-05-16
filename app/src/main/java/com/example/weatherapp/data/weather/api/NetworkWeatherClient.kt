package com.example.weatherapp.data.weather.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkWeatherClient : WeatherClient {
    private val BASE_URL = "https://api.open-meteo.com/v1/"

    private val retrofit: Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

    private val weatherApi: WeatherApi by lazy {
        retrofit.create(WeatherApi::class.java)
    }

    override val repository: WeatherRepository by lazy {
        NetworkWeatherRepository(weatherApi)
    }
}