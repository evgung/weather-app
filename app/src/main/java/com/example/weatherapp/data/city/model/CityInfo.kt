package com.example.weatherapp.data.city.model

import com.google.gson.annotations.SerializedName

data class CityInfo(

    @SerializedName("id"           ) val id          : Int,
    @SerializedName("name"         ) val name        : String,
    @SerializedName("latitude"     ) val latitude    : Double,
    @SerializedName("longitude"    ) val longitude   : Double,
    @SerializedName("elevation"    ) val elevation   : Int,
    @SerializedName("feature_code" ) val featureCode : String,
    @SerializedName("country_code" ) val countryCode : String,
    @SerializedName("admin1_id"    ) val admin1Id    : Int? = null,
    @SerializedName("admin2_id"    ) val admin2Id    : Int? = null,
    @SerializedName("admin3_id"    ) val admin3Id    : Int? = null,
    @SerializedName("admin4_id"    ) val admin4Id    : Int? = null,
    @SerializedName("timezone"     ) val timezone    : String,
    @SerializedName("population"   ) val population  : Int,
    @SerializedName("postcodes"    ) val postcodes   : List<String> = emptyList(),
    @SerializedName("country_id"   ) val countryId   : Int,
    @SerializedName("country"      ) val country     : String,
    @SerializedName("admin1"       ) val admin1      : String? = null,
    @SerializedName("admin2"       ) val admin2      : String? = null,
    @SerializedName("admin3"       ) val admin3      : String? = null,
    @SerializedName("admin4"       ) val admin4      : String? = null

)
