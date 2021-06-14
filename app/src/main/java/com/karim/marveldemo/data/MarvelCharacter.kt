package com.karim.marveldemo.data

import android.os.Parcelable
import androidx.room.*
import com.karim.marveldemo.persistence.MarvelTypeConverters
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Entity
@JsonClass(generateAdapter = true)
@Parcelize
@TypeConverters(MarvelTypeConverters::class)
data class MarvelCharacter(
    var page: Int = 0,
    @PrimaryKey
    @field:Json(name = "id")val id: Int,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "description")
    val description: String,
    @field:Json(name = "modified")
    val modified: String,
    @field:Json(name = "resourceURI")
    val resourceURI: String,
    @field:Json(name = "thumbnail")
    val thumbnail: Thumbnail,
    @field:Json(name = "comics")
    @Embedded(prefix = "comics_")val comics: Comics,
    @field:Json(name = "events")
    @Embedded(prefix = "events_")val events: Events,
    @field:Json(name = "series")
    @Embedded(prefix = "series_")val series: Series,
    @field:Json(name = "stories")
    @Embedded(prefix = "stories_") val stories: Stories
) : Parcelable {
    @Ignore
    @IgnoredOnParcel
    @field:Json(name = "urls")
    val urls: List<Url> = emptyList()
    val thumbnailUrl
        get() = "${thumbnail.path}.${thumbnail.extension}"
}

@JsonClass(generateAdapter = true)
@Parcelize
@TypeConverters(MarvelTypeConverters::class)
data class Comics(
    @field:Json(name = "available")
    val available: Int,
    @field:Json(name = "collectionURI")
    val collectionURI: String,
    @field:Json(name = "items")
    val items: List<Item>,
    @field:Json(name = "returned")
    val returned: Int
) : Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class Events(
    @field:Json(name = "available")
    val available: Int,
    @field:Json(name = "collectionURI")
    val collectionURI: String,
    @field:Json(name = "items")
    val items: List<Item>,
    @field:Json(name = "returned")
    val returned: Int
) : Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class Series(
    @field:Json(name = "available")
    val available: Int,
    @field:Json(name = "collectionURI")
    val collectionURI: String,
    @field:Json(name = "items")
    val items: List<Item>,
    @field:Json(name = "returned")
    val returned: Int
) : Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class Stories(
    @field:Json(name = "available")
    val available: Int,
    @field:Json(name = "collectionURI")
    val collectionURI: String,
    @field:Json(name = "items")
    val items: List<Item>,
    @field:Json(name = "returned")
    val returned: Int
) : Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class Thumbnail(
    @field:Json(name = "extension")
    var extension: String,
    @field:Json(name = "path")
    var path: String
) : Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class Url(
    @field:Json(name = "type")
    val type: String,
    @field:Json(name = "url")
    val url: String
) : Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class Item(
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "resourceURI")
    val resourceURI: String,
    @field:Json(name = "type")
    val type: String?
) : Parcelable
