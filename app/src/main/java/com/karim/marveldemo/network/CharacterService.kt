package com.karim.marveldemo.network

import com.karim.marveldemo.BuildConfig.PUB_API_KEY
import com.karim.marveldemo.data.GenericResponse
import com.karim.marveldemo.data.MarvelResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterService {

    @GET("/v1/public/characters")
    suspend fun getRemoteCharacters(
        @Query("limit") limit: Int = 10,
        @Query("offset") offset: Int = 0,
        @Query("apikey") apiKey: String = PUB_API_KEY,
        @Query("nameStartsWith") nameString: String? =null,
        @Query("ts") timeStamp: String,
        @Query("hash") hash: String,
        @Query("orderBy") orderBy: String = "name"
    ): ApiResponse<MarvelResponse>

    @GET("/v1/public/characters/{characterId}/events")
    suspend fun getRemoteEvents(
        @Path("characterId") characterId: Int,
        @Query("apikey") apiKey: String = PUB_API_KEY,
        @Query("ts") timeStamp: String,
        @Query("hash") hash: String,
    ): ApiResponse<GenericResponse>

    @GET("/v1/public/characters/{characterId}/comics")
    suspend fun getRemoteComics(
        @Path("characterId") characterId: Int,
        @Query("apikey") apiKey: String = PUB_API_KEY,
        @Query("ts") timeStamp: String,
        @Query("hash") hash: String,
    ): ApiResponse<GenericResponse>

    @GET("/v1/public/characters/{characterId}/stories")
    suspend fun getRemoteStories(
        @Path("characterId") characterId: Int,
        @Query("apikey") apiKey: String = PUB_API_KEY,
        @Query("ts") timeStamp: String,
        @Query("hash") hash: String,
    ): ApiResponse<GenericResponse>

    @GET("/v1/public/characters/{characterId}/series")
    suspend fun getRemoteSeries(
        @Path("characterId") characterId: Int,
        @Query("apikey") apiKey: String = PUB_API_KEY,
        @Query("ts") timeStamp: String,
        @Query("hash") hash: String,
    ): ApiResponse<GenericResponse>

}