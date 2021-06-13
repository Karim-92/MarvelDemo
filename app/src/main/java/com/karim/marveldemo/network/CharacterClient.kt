package com.karim.marveldemo.network

import com.karim.marveldemo.PAGING_SIZE
import com.karim.marveldemo.PRIV_API_KEY
import com.karim.marveldemo.PUB_API_KEY
import com.karim.marveldemo.data.MarvelResponse
import com.skydoves.sandwich.ApiResponse
import java.math.BigInteger
import java.security.MessageDigest
import javax.inject.Inject

class CharacterClient @Inject constructor(
    private val characterService: CharacterService
) {
    private val time: String = System.currentTimeMillis().toString()
    private val input: String = System.currentTimeMillis().toString()+ PRIV_API_KEY+ PUB_API_KEY

    suspend fun getRemoteMarvelCharacters(
        paging: Int
    ): ApiResponse<MarvelResponse> = characterService.getRemoteCharacters(
        limit = PAGING_SIZE,
        offset = paging * PAGING_SIZE,
        timeStamp = time,
        hash = getHash(input)
    )

    companion object {

        fun getHash(msg: String): String {
            val md =  MessageDigest.getInstance("MD5")
            return BigInteger(1, md.digest(msg.toByteArray())).toString(16).padStart(32, '0')
        }
    }
}