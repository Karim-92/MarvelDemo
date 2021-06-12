package com.karim.marveldemo.network

import com.karim.marveldemo.data.MarvelResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterService {

    @GET("/v1/public/characters")
    suspend fun getRemoteCharacters(
        @Query("limit") limit: Int = 10,
        @Query("offset") offset: Int=0
    ) : ApiResponse<MarvelResponse>
}