package com.karim.marveldemo.data
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Json

@Entity
@JsonClass(generateAdapter = true)
data class CharacterData(
    @PrimaryKey
    @Json(name = "id")
    val id: Int,
    @Json(name = "comics")
    val comics: Comics,
    @Json(name = "description")
    val description: String,
    @Json(name = "events")
    val events: Events,
    @Json(name = "modified")
    val modified: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "resourceURI")
    val resourceURI: String,
    @Json(name = "series")
    val series: Series,
    @Json(name = "stories")
    val stories: Stories,
    @Json(name = "thumbnail")
    val thumbnail: Thumbnail,
    @Json(name = "urls")
    val urls: List<Url>
)

@JsonClass(generateAdapter = true)
data class Comics(
    @Json(name = "available")
    val available: Int,
    @Json(name = "collectionURI")
    val collectionURI: String,
    @Json(name = "items")
    val items: List<Item>,
    @Json(name = "returned")
    val returned: Int
)

@JsonClass(generateAdapter = true)
data class Events(
    @Json(name = "available")
    val available: Int,
    @Json(name = "collectionURI")
    val collectionURI: String,
    @Json(name = "items")
    val items: List<Item>,
    @Json(name = "returned")
    val returned: Int
)

@JsonClass(generateAdapter = true)
data class Series(
    @Json(name = "available")
    val available: Int,
    @Json(name = "collectionURI")
    val collectionURI: String,
    @Json(name = "items")
    val items: List<Item>,
    @Json(name = "returned")
    val returned: Int
)

@JsonClass(generateAdapter = true)
data class Stories(
    @Json(name = "available")
    val available: Int,
    @Json(name = "collectionURI")
    val collectionURI: String,
    @Json(name = "items")
    val items: List<Item>,
    @Json(name = "returned")
    val returned: Int
)

@JsonClass(generateAdapter = true)
data class Thumbnail(
    @Json(name = "extension")
    val extension: String,
    @Json(name = "path")
    val path: String
)

@JsonClass(generateAdapter = true)
data class Url(
    @Json(name = "type")
    val type: String,
    @Json(name = "url")
    val url: String
)

@JsonClass(generateAdapter = true)
data class Item(
    @Json(name = "name")
    val name: String,
    @Json(name = "resourceURI")
    val resourceURI: String,
    @Json(name = "type")
    val type: String
)