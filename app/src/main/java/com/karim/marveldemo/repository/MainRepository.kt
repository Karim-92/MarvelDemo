package com.karim.marveldemo.repository

import androidx.annotation.WorkerThread
import com.karim.marveldemo.mapper.ErrorResponseMapper
import com.karim.marveldemo.network.CharacterClient
import com.karim.marveldemo.persistence.CharactersDao
import com.skydoves.sandwich.map
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import com.skydoves.whatif.whatIfNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import timber.log.Timber
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val characterClient: CharacterClient,
    private val charactersDao: CharactersDao
) : Repository {

    @WorkerThread
    fun getMarvelCharacters(
        page: Int,
        query: String?,
        fromSearch: Boolean,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) =
        flow {
            if (fromSearch) {
                val response = characterClient.getRemoteMarvelCharacters(
                    page = page,
                    query
                )
                response.suspendOnSuccess {
                    data.whatIfNotNull { response ->
                        Timber.d("Results from websearch: ${response.data.results}")
                        emit(response.data.results)
                    }
                }.onError {
                    map(ErrorResponseMapper) { onError("[Code: $code]: $message") }
                }.onException { onError(message) }
            } else {
                var characters =
                    charactersDao.getAllCharactersFromDb(page)
                if (characters.isEmpty()) {
                    val response =
                            characterClient.getRemoteMarvelCharacters(page = page)
                    response.suspendOnSuccess {
                        data.whatIfNotNull { response ->
                            characters = response.data.results
                            characters.forEach { character -> character.page = page }
                            charactersDao.insertCharactersList(characters)
                            emit(charactersDao.getAllCharactersList(page))
                        }
                    }.onError {
                        map(ErrorResponseMapper) { onError("[Code: $code]: $message") }
                    }.onException { onError(message) }
                } else {
                    emit(charactersDao.getAllCharactersList(page))
                }
            }
        }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(Dispatchers.IO)


}
