package com.karim.marveldemo.data

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize


@JsonClass(generateAdapter = true)
@Parcelize
data class MarvelResponse(
    @Json(name = "attributionHTML")
    val attributionHTML: String,
    @Json(name = "attributionText")
    val attributionText: String,
    @Json(name = "code")
    val code: Int,
    @Json(name = "copyright")
    val copyright: String,
    @Json(name = "data")
    val data: Data,
    @Json(name = "etag")
    val etag: String,
    @Json(name = "status")
    val status: String
) : Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class Data(
    @Json(name = "count")
    val count: Int,
    @Json(name = "limit")
    val limit: Int,
    @Json(name = "offset")
    val offset: Int,
    @Json(name = "results")
    val results: List<CharacterData>,
    @Json(name = "total")
    val total: Int
) : Parcelable
