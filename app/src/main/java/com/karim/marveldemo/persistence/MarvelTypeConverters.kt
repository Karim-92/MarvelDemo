package com.karim.marveldemo.persistence

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.karim.marveldemo.data.*
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import timber.log.Timber
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
        Timber.d("FQN: ${data}")
        val thumb: Thumbnail=Thumbnail(path = data.substringBeforeLast("."),
                                        extension = data.substringAfterLast("."))
        Timber.d("Image Path: ${thumb.path}, Image Ext: ${thumb.extension}")
        return thumb
    }

    // Comics Type Converter
    @TypeConverter
    fun comicFromString(value: String): List<Comics> {
        val listType = Types.newParameterizedType(List::class.java, Comics::class.java)
        val adapter: JsonAdapter<List<Comics>> = moshi.adapter(listType)
        return adapter.fromJson(value)?: emptyList()
    }

    @TypeConverter
    fun comicsToString(comics: List<Comics>): String? {
        val listType = Types.newParameterizedType(List::class.java, Comics::class.java)
        val adapter: JsonAdapter<List<Comics>> = moshi.adapter(listType)
        return adapter.toJson(comics)
    }

    // Events Type Converter
    @TypeConverter
    fun eventsFromString(value: String): List<Events>? {
        val listType = Types.newParameterizedType(List::class.java, Events::class.java)
        val adapter: JsonAdapter<List<Events>> = moshi.adapter(listType)
        return adapter.fromJson(value)
    }
    @TypeConverter
    fun eventsToString(events: List<Events>): String? {
        val listType = Types.newParameterizedType(List::class.java, Comics::class.java)
        val adapter: JsonAdapter<List<Events>> = moshi.adapter(listType)
        return adapter.toJson(events)
    }

    // Series Type Converter
    @TypeConverter
    fun seriesFromString(value: String): List<Series>? {
        val listType = Types.newParameterizedType(List::class.java, Series::class.java)
        val adapter: JsonAdapter<List<Series>> = moshi.adapter(listType)
        return adapter.fromJson(value)
    }
    @TypeConverter
    fun seriesToString(series: List<Series>): String? {
        val listType = Types.newParameterizedType(List::class.java, Series::class.java)
        val adapter: JsonAdapter<List<Series>> = moshi.adapter(listType)
        return adapter.toJson(series)
    }

    // Stories Type Converter
    @TypeConverter
    fun storiesFromString(value: String): List<Stories>? {
        val listType = Types.newParameterizedType(List::class.java, Stories::class.java)
        val adapter: JsonAdapter<List<Stories>> = moshi.adapter(listType)
        return adapter.fromJson(value)
    }
    @TypeConverter
    fun storiesToString(stories: List<Stories>): String? {
        val listType = Types.newParameterizedType(List::class.java, Stories::class.java)
        val adapter: JsonAdapter<List<Stories>> = moshi.adapter(listType)
        return adapter.toJson(stories)
    }

    // Item Type Converter
    @TypeConverter
    fun itemFromString(value: String): List<Item>? {
        val listType = Types.newParameterizedType(List::class.java, Item::class.java)
        val adapter: JsonAdapter<List<Item>> = moshi.adapter(listType)
        return adapter.fromJson(value)
    }
    @TypeConverter
    fun itemToString(items: List<Item>): String? {
        val listType = Types.newParameterizedType(List::class.java, Item::class.java)
        val adapter: JsonAdapter<List<Item>> = moshi.adapter(listType)
        return adapter.toJson(items)
    }

}