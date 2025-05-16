package com.example.weatherapp.data.city.api

import com.example.weatherapp.data.city.model.CityInfo

interface CityRepository {
    suspend fun getCityInfo(
        cityName: String
    ) : CityInfo
}