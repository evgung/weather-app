package com.example.weatherapp.data.city.api

import com.example.weatherapp.data.weather.api.NetworkWeatherRepository
import com.example.weatherapp.data.weather.api.WeatherApi
import com.example.weatherapp.data.weather.api.WeatherRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkCityClient : CityClient {
    private val BASE_URL = "https://geocoding-api.open-meteo.com/v1/"

    private val retrofit: Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

    private val cityApi: CityApi by lazy {
        retrofit.create(CityApi::class.java)
    }

    override val repository: CityRepository by lazy {
        NetworkCityRepository(cityApi)
    }
}