package com.karim.marveldemo.network

import com.karim.marveldemo.PAGING_SIZE
import com.karim.marveldemo.data.MarvelResponse
import retrofit2.Response
import javax.inject.Inject

class HeroesClient @Inject constructor(
    private val heroesService : HeroesService
){
    suspend fun getMarvelCharacters(
        paging : Int): Response<MarvelResponse> = heroesService.getCharacters(
        limit = PAGING_SIZE,
        offset = paging* PAGING_SIZE)
}