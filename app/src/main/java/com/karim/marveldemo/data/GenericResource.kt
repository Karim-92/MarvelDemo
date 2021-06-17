package com.karim.marveldemo.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class GenericResource(
    @Json(name = "id") val id: Int,
    @Json(name = "title") val title: String,
    @Json(name = "description") val description: String?,
    @Json(name = "thumbnail") val thumbnail: Thumbnail?,
){
    val thumbnailUrl
        get() = "${thumbnail?.path}.${thumbnail?.extension}"
}
