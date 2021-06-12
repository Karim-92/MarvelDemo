package com.karim.marveldemo.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MarvelResponse(
    @Json(name = "count")
    val count: Int,
    @Json(name = "limit")
    val limit: Int,
    @Json(name = "offset")
    val offset: Int,
    @Json(name = "results")
    val results: List<CharactersData>,
    @Json(name = "total")
    val total: Int
)