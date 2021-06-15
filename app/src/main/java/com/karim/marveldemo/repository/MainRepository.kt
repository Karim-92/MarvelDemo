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
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val characterClient: CharacterClient,
    private val charactersDao: CharactersDao
) : Repository {

    @WorkerThread
    fun getMarvelCharacters(
        page: Int,
        query: String?,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) =
        flow {
            var characters = if(query==null) charactersDao.getAllCharactersFromDb(page) else charactersDao.getQueryCharacters(query)
            if (characters.isEmpty()) {
                val response = if(query==null)characterClient.getRemoteMarvelCharacters(page = page) else characterClient.getRemoteMarvelCharacters(page = page, query)
                response.suspendOnSuccess {
                    data.whatIfNotNull { response ->
                        characters=response.data.results
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
        }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(Dispatchers.IO)

}
