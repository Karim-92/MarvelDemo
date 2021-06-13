package com.karim.marveldemo.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Entity
@JsonClass(generateAdapter = true)
@Parcelize

data class CharacterData(
    @PrimaryKey
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "description")
    val description: String,
    @Json(name = "comics")
    @Ignore val comics: Comics?,
    @Json(name = "events")
    @Ignore val events: Events?,
    @Json(name = "modified")
    @Ignore val modified: String,
    @Json(name = "resourceURI")
    @Ignore val resourceURI: String,
    @Json(name = "series")
    @Ignore val series: Series?,
    @Json(name = "stories")
    @Ignore val stories: Stories?,
    @Json(name = "thumbnail")
    @Ignore val thumbnail: Thumbnail?,
    @Json(name = "urls")
    @Ignore val urls: List<Url>?
) : Parcelable {
    val thumbnailUrl
        get() = "${thumbnail?.path}.${thumbnail?.extension}"
    constructor (id: Int, name: String, description: String): this (0, "", "", null, null, "", "", null, null, null, null) {
    }
}

@JsonClass(generateAdapter = true)
@Parcelize
data class Comics(
    @Json(name = "available")
    val available: Int,
    @Json(name = "collectionURI")
    val collectionURI: String,
    @Json(name = "items")
    val items: List<Item>,
    @Json(name = "returned")
    val returned: Int
) : Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class Events(
    @Json(name = "available")
    val available: Int,
    @Json(name = "collectionURI")
    val collectionURI: String,
    @Json(name = "items")
    val items: List<Item>,
    @Json(name = "returned")
    val returned: Int
) : Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class Series(
    @Json(name = "available")
    val available: Int,
    @Json(name = "collectionURI")
    val collectionURI: String,
    @Json(name = "items")
    val items: List<Item>,
    @Json(name = "returned")
    val returned: Int
) : Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class Stories(
    @Json(name = "available")
    val available: Int,
    @Json(name = "collectionURI")
    val collectionURI: String,
    @Json(name = "items")
    val items: List<Item>,
    @Json(name = "returned")
    val returned: Int
) : Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class Thumbnail(
    @Json(name = "extension")
    val extension: String,
    @Json(name = "path")
    val path: String
) : Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class Url(
    @Json(name = "type")
    val type: String,
    @Json(name = "url")
    val url: String
) : Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class Item(
    @Json(name = "name")
    val name: String,
    @Json(name = "resourceURI")
    val resourceURI: String,
    @Json(name = "type")
    val type: String?
) : Parcelable