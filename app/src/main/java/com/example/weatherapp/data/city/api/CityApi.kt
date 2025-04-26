package com.example.weatherapp.data.city.api

import com.example.weatherapp.data.city.model.CityResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CityApi {

    @GET("search?count=1&language=en&format=json")
    suspend fun getCityInfo(
        @Query("name") cityName: String,
    ) : CityResponse

}