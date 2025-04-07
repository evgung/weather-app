package com.example.weatherapp.data.city.api

import com.example.weatherapp.data.city.model.CityInfo
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface CityApi {

    @GET("city")
    suspend fun getCityInfo(
        @Query("name") cityName: String,
        @Header("X-Api-Key") apiKey: String
    ) : List<CityInfo>

}