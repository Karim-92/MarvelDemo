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
    @PrimaryKey
    @field:Json(name = "id")val id: Int,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "description")
    val description: String,
    @field:Json(name = "thumbnail")
    val thumbnail: Thumbnail
) : Parcelable {
    val thumbnailUrl
        get() = "${thumbnail.path}.${thumbnail.extension}"

    @Transient
    @Ignore
    @IgnoredOnParcel
    var comics: List<GenericResource> = emptyList()
    @Transient
    @Ignore
    @IgnoredOnParcel
    var events: List<GenericResource> = emptyList()
    @Transient
    @Ignore
    @IgnoredOnParcel
    var stories: List<GenericResource> = emptyList()
    @Transient
    @Ignore
    @IgnoredOnParcel
    var series: List<GenericResource> = emptyList()
}
@JsonClass(generateAdapter = true)
@Parcelize
data class Thumbnail(
    @field:Json(name = "extension")
    var extension: String,
    @field:Json(name = "path")
    var path: String
) : Parcelable