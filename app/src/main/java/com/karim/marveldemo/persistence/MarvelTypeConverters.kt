package com.karim.marveldemo.persistence

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.karim.marveldemo.data.Thumbnail
import com.squareup.moshi.Moshi
import javax.inject.Inject

@ProvidedTypeConverter
class MarvelTypeConverters @Inject constructor(
    private val moshi: Moshi
) {
    // Thumbnail Type Converter
    @TypeConverter
    fun thumbToString(thumbnail: Thumbnail): String {
        return "${thumbnail.path}.${thumbnail.extension}"
    }

    @TypeConverter
    fun stringToThumb(data: String): Thumbnail {
        return Thumbnail(
            path = data.substringBeforeLast("."),
            extension = data.substringAfterLast(".")
        )
    }

}