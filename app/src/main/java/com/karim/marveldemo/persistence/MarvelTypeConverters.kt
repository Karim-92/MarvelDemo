package com.karim.marveldemo.persistence

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.karim.marveldemo.data.Thumbnail
import timber.log.Timber
import javax.inject.Inject

@ProvidedTypeConverter
class MarvelTypeConverters @Inject constructor(
) {
    @TypeConverter
    fun thumbToString(thumbnail: Thumbnail): String {
        return "${thumbnail.path}.${thumbnail.extension}"
    }

    @TypeConverter
    fun stringToThumb(data: String): Thumbnail {
        Timber.d("FQN: ${data}")
        val thumb: Thumbnail=Thumbnail(path = data.substringBeforeLast("."),
                                        extension = data.substringAfterLast("."))
        Timber.d("Image Path: ${thumb.path}, Image Ext: ${thumb.extension}")
        return thumb
    }
}