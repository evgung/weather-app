package com.example.weatherapp.data.city.model

import com.google.gson.annotations.SerializedName

data class CityInfo(

    @SerializedName("name"       ) var name       : String?  = null,
    @SerializedName("latitude"   ) var latitude   : Double,
    @SerializedName("longitude"  ) var longitude  : Double,
    @SerializedName("country"    ) var country    : String?  = null,
    @SerializedName("population" ) var population : Int?     = null,
    @SerializedName("region"     ) var region     : String?  = null,
    @SerializedName("is_capital" ) var isCapital  : Boolean? = null

)
