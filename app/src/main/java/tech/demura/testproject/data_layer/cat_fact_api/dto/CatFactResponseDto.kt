package tech.demura.testproject.data_layer.cat_fact_api.dto

import com.google.gson.annotations.SerializedName

data class CatFactResponseDto(
    @SerializedName("fact") val fact: String,
    @SerializedName("length") val length: Int
)
