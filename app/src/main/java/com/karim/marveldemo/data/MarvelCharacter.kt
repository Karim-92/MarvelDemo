package com.karim.marveldemo.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
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
    @Json(name = "id")
    @PrimaryKey
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "description")
    val description: String,
    @Json(name = "modified")
    val modified: String,
    @Json(name = "resourceURI")
    val resourceURI: String,
    @Json(name = "thumbnail")
    val thumbnail: Thumbnail
) : Parcelable {
    @Ignore
    @IgnoredOnParcel
    @Json(name = "comics")
    val comics: Comics? = null
    @Ignore
    @IgnoredOnParcel
    @Json(name = "events")
    val events: Events? = null
    @Ignore
    @IgnoredOnParcel
    @Json(name = "series")
    val series: Series? = null
    @Ignore
    @IgnoredOnParcel
    @Json(name = "stories")
    val stories: Stories? = null
    @Ignore
    @IgnoredOnParcel
    @Json(name = "urls")
    val urls: List<Url> = emptyList()
    val thumbnailUrl
        get() = "${thumbnail.path}.${thumbnail.extension}"
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
    var extension: String,
    @Json(name = "path")
    var path: String
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
