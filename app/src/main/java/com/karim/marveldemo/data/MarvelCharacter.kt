package com.karim.marveldemo.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.karim.marveldemo.persistence.MarvelTypeConverters
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Entity
@JsonClass(generateAdapter = true)
@Parcelize
@TypeConverters(MarvelTypeConverters::class)
data class MarvelCharacter(
    var page: Int = 0,
    @PrimaryKey
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "description")
    val description: String,
    @field:Json(name = "thumbnail")
    val thumbnail: Thumbnail
) : Parcelable {
    val thumbnailUrl
        get() = "${thumbnail.path}.${thumbnail.extension}"
}

@JsonClass(generateAdapter = true)
@Parcelize
data class Thumbnail(
    @field:Json(name = "extension")
    var extension: String,
    @field:Json(name = "path")
    var path: String
) : Parcelable

data class MarvelCharacterDTO(
    var marvelCharacter: MarvelCharacter?,
    var comics: List<GenericResource>,
    var events: List<GenericResource>,
    var stories: List<GenericResource>,
    var series: List<GenericResource>
){
    constructor() : this(null, emptyList(), emptyList(), emptyList(), emptyList())
}