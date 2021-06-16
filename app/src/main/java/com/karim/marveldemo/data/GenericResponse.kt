package com.karim.marveldemo.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenericResponse(
@Json(name = "attributionHTML")
val attributionHTML: String,
@Json(name = "attributionText")
val attributionText: String,
@Json(name = "code")
val code: Int,
@Json(name = "copyright")
val copyright: String,
@Json(name = "data")
val data: GenericData,
@Json(name = "etag")
val etag: String,
@Json(name = "status")
val status: String
)

@JsonClass(generateAdapter = true)
data class GenericData(
    @Json(name = "count")
    val count: Int,
    @Json(name = "limit")
    val limit: Int,
    @Json(name = "offset")
    val offset: Int,
    @Json(name = "results")
    val results: List<GenericResource>,
    @Json(name = "total")
    val total: Int
)
