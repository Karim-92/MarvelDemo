package com.karim.marveldemo.network

import com.karim.marveldemo.data.MarvelResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HeroesService {

    @GET("/v1/public/characters")
    suspend fun getCharacters(
        @Query("limit") limit: Int = 10,
        @Query("offset") offset: Int=0
    ) : Response<MarvelResponse>
}