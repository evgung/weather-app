package com.example.weatherapp.data.city.api

import com.example.weatherapp.data.city.model.CityInfo

interface CityRepository {
    suspend fun getCityInfo(
        cityName: String
    ) : CityInfo
}

class NetworkCityRepository (
    private val cityApi: CityApi,
    private val apiKey: String,
) : CityRepository {

    override suspend fun getCityInfo(cityName: String): CityInfo {
        return cityApi.getCityInfo(cityName, apiKey).first()
    }

}