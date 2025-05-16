package com.example.weatherapp.data.city.api

import com.example.weatherapp.data.city.model.CityInfo

class NetworkCityRepository (
    private val cityApi: CityApi,
) : CityRepository {

    override suspend fun getCityInfo(cityName: String): CityInfo {
        return cityApi.getCityInfo(cityName).results.first()
    }

}