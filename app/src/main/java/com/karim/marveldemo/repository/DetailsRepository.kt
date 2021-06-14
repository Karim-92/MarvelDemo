package com.karim.marveldemo.repository

import androidx.annotation.WorkerThread
import com.karim.marveldemo.data.MarvelCharacter
import com.karim.marveldemo.mapper.ErrorResponseMapper
import com.karim.marveldemo.network.CharacterClient
import com.karim.marveldemo.persistence.CharactersDao
import com.skydoves.sandwich.*
import com.skydoves.whatif.whatIfNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import javax.inject.Inject

class DetailsRepository @Inject constructor(
    private val characterClient: CharacterClient,
    private val charactersDao: CharactersDao
) {

    @WorkerThread
    fun getCharacterData(characterId: Int, onComplete: () -> Unit, onError: (String?) -> Unit) =
        flow<MarvelCharacter> {
            var marvelCharacter = charactersDao.getCharacterData(characterId)
            if (marvelCharacter == null) {
                val response = characterClient.getRemoteCharacterData(characterId)
                response.suspendOnSuccess {
                    data.whatIfNotNull { response ->
                        marvelCharacter = response.data.results[0]
                        charactersDao.insertCharacter(marvelCharacter!!)
                        emit(response.data.results[0])
                    }
                }
                    .onError {
                        /** maps the [ApiResponse.Failure.Error] to the [PokemonErrorResponse] using the mapper. */
                        map(ErrorResponseMapper) { onError("[Code: $code]: $message") }
                    }
                    // handles the case when the API request gets an exception response.
                    // e.g., network connection error.
                    .onException { onError(message) }
            } else {
                emit(marvelCharacter!!)
            }
        }.onCompletion { onComplete() }.flowOn(Dispatchers.IO)
}
