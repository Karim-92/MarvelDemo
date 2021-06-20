package com.karim.marveldemo.network

import com.karim.marveldemo.BuildConfig
import com.karim.marveldemo.data.GenericResponse
import com.karim.marveldemo.data.MarvelResponse
import com.skydoves.sandwich.ApiResponse
import java.math.BigInteger
import java.security.MessageDigest
import javax.inject.Inject

class CharacterClient @Inject constructor(
    private val characterService: CharacterService
) {
    private val time: String = System.currentTimeMillis().toString()
    private val input: String =
        System.currentTimeMillis().toString() + BuildConfig.PRIV_API_KEY + BuildConfig.PUB_API_KEY

    suspend fun getRemoteMarvelCharacters(
        page: Int,
        query: String? = null
    ): ApiResponse<MarvelResponse> = characterService.getRemoteCharacters(
        limit = PAGING_SIZE,
        offset = page * PAGING_SIZE,
        nameString = query,
        timeStamp = time,
        hash = getHash(input)
    )

    suspend fun getRemoteEvents(
        characterId: Int
    ): ApiResponse<GenericResponse> =
        characterService.getRemoteEvents(
            characterId = characterId,
            timeStamp = time,
            hash = getHash(input)
        )

    suspend fun getRemoteComics(
        characterId: Int
    ): ApiResponse<GenericResponse> =
        characterService.getRemoteComics(
            characterId = characterId,
            timeStamp = time,
            hash = getHash(input)
        )

    suspend fun getRemoteStories(
        characterId: Int
    ): ApiResponse<GenericResponse> =
        characterService.getRemoteStories(
            characterId = characterId,
            timeStamp = time,
            hash = getHash(input)
        )

    suspend fun getRemoteSeries(
        characterId: Int
    ): ApiResponse<GenericResponse> =
        characterService.getRemoteSeries(
            characterId = characterId,
            timeStamp = time,
            hash = getHash(input)
        )

    companion object {
        private const val PAGING_SIZE = 10

        fun getHash(msg: String): String {
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1, md.digest(msg.toByteArray())).toString(16).padStart(32, '0')
        }
    }
}