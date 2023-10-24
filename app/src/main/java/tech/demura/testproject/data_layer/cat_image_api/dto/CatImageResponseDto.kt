package tech.demura.testproject.data_layer.cat_image_api.dto

import com.google.gson.annotations.SerializedName

data class CatImageResponseDto(
    @SerializedName("tags") val tags: List<String>,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("updatedAt") val updatedAt: String,
    @SerializedName("mimetype") val mimetype: String,
    @SerializedName("size") val size: Int,
    @SerializedName("_id") val _id: String,
)

//response      {
// "tags":[],
// "createdAt":"2022-06-30T06:05:15.531Z",
// "updatedAt":"2022-10-11T07:52:32.756Z",
// "mimetype":"image/jpeg",
// "size":426168,
// "_id":"LTxlBUdATocntNid"}