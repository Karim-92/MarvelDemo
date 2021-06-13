package com.karim.marveldemo.repository

import androidx.annotation.WorkerThread
import com.karim.marveldemo.network.CharacterClient
import com.karim.marveldemo.persistence.CharactersDao
import com.skydoves.sandwich.message
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
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: () -> Unit
    ) =
        flow {
            var characters = charactersDao.getAllCharactersFromDb(page)
            if (characters.isEmpty()) {
                val response = characterClient.getRemoteMarvelCharacters(page = page)
                response.suspendOnSuccess {
                    data.whatIfNotNull { response ->
                        characters=response.data.results
                        characters.forEach { character -> character.page = page }
                        charactersDao.insertCharactersList(characters)
                        emit(charactersDao.getAllCharactersList(page))
                    }
                }.onError {
                    error(message())
                }.onException {
                    error(message())
                }
            } else {
                emit(charactersDao.getAllCharactersList(page))
            }
        }.onStart { onStart }.onCompletion { onComplete }.flowOn(Dispatchers.IO)

}
