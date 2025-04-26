package com.example.weatherapp.data.city.model

import com.google.gson.annotations.SerializedName

data class CityResponse(

    @SerializedName("results"           ) val results          : List<CityInfo>,
    @SerializedName("generationtime_ms" ) val generationtimeMs : Double

)
