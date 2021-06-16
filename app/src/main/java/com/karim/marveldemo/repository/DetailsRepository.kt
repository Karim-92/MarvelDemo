package com.karim.marveldemo.repository

import androidx.annotation.WorkerThread
import com.karim.marveldemo.data.MarvelCharacter
import com.karim.marveldemo.mapper.ErrorResponseMapper
import com.karim.marveldemo.network.CharacterClient
import com.karim.marveldemo.persistence.CharactersDao
import com.skydoves.sandwich.map
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import com.skydoves.whatif.whatIfNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject

class DetailsRepository @Inject constructor(
    private val characterClient: CharacterClient,
    private val charactersDao: CharactersDao
): Repository {

    private lateinit var characterDTO: MarvelCharacter


    @WorkerThread
    fun getComics(characterId: Int, onComplete: () -> Unit, onError: (String?) -> Unit) =
        flow{
                characterDTO = charactersDao.getCharacterData(characterId)
                val response = characterClient.getRemoteComics(characterId)
                response.suspendOnSuccess {
                    data.whatIfNotNull { response ->
                        characterDTO.comics  = response.data.results
                        emit(characterDTO.comics)
                    }
                }
                    .onError {
                        map(ErrorResponseMapper) { onError("[Code: $code]: $message") }
                    }
                    .onException { onError(message) }
        }.onCompletion { onComplete() }.flowOn(Dispatchers.IO)

    @WorkerThread
    fun getEvents(characterId: Int, onComplete: () -> Unit, onError: (String?) -> Unit) =
        flow{
            characterDTO = charactersDao.getCharacterData(characterId)
            val response = characterClient.getRemoteEvents(characterId)
            response.suspendOnSuccess {
                data.whatIfNotNull { response ->
                    characterDTO.events  = response.data.results
                    emit(characterDTO.events)
                }
            }
                .onError {
                    map(ErrorResponseMapper) { onError("[Code: $code]: $message") }
                }
                .onException { onError(message) }
        }.onCompletion { onComplete() }.flowOn(Dispatchers.IO)

    @WorkerThread
    fun getStories(characterId: Int, onComplete: () -> Unit, onError: (String?) -> Unit) =
        flow{
            characterDTO = charactersDao.getCharacterData(characterId)
            val response = characterClient.getRemoteStories(characterId)
            response.suspendOnSuccess {
                data.whatIfNotNull { response ->
                    characterDTO.stories  = response.data.results
                    emit(characterDTO.stories)
                }
            }
                .onError {
                    map(ErrorResponseMapper) { onError("[Code: $code]: $message") }
                }
                .onException { onError(message) }
        }.onCompletion { onComplete() }.flowOn(Dispatchers.IO)

    @WorkerThread
    fun getSeries(characterId: Int, onComplete: () -> Unit, onError: (String?) -> Unit) =
        flow{
            characterDTO = charactersDao.getCharacterData(characterId)
            val response = characterClient.getRemoteSeries(characterId)
            response.suspendOnSuccess {
                data.whatIfNotNull { response ->
                    characterDTO.series  = response.data.results
                    emit(characterDTO)
                }
            }
                .onError {
                    map(ErrorResponseMapper) { onError("[Code: $code]: $message") }
                }
                .onException { onError(message) }
        }.onCompletion { onComplete() }.zip(getStories(characterId, onComplete, onError)){ _, stories  ->
            characterDTO.stories=stories
        }.zip(getComics(characterId, onComplete, onError)){ _, comics->
            characterDTO.comics=comics
        }.zip(getEvents(characterId, onComplete, onError)){_, events->
            characterDTO.events=events
        }.flowOn(Dispatchers.IO)

    @WorkerThread
    fun getCharacterData(characterId: Int, onComplete: () -> Unit, onError: (String?) -> Unit)=
        flow{
            getSeries(characterId, onComplete, onError).collect()
            Timber.d("Character DTO Values: ${characterDTO.toString()}")
            emit(characterDTO)
        }.onCompletion { onComplete() }.flowOn(Dispatchers.IO)

}
