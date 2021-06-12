package com.karim.marveldemo.network

import com.karim.marveldemo.PAGING_SIZE
import com.karim.marveldemo.data.MarvelResponse
import com.skydoves.sandwich.ApiResponse
import javax.inject.Inject

class CharacterClient @Inject constructor(
    private val characterService: CharacterService
) {
    suspend fun getRemoteMarvelCharacters(
        paging: Int
    ): ApiResponse<MarvelResponse> = characterService.getRemoteCharacters(
        limit = PAGING_SIZE,
        offset = paging * PAGING_SIZE
    )

}