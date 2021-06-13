package com.karim.marveldemo.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.karim.marveldemo.data.CharacterData
import com.karim.marveldemo.network.CharacterClient
import com.karim.marveldemo.persistence.CharactersDao
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.onSuccess
import com.skydoves.whatif.whatIfNotNull
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val characterClient: CharacterClient,
    private val charactersDao : CharactersDao) : Repository{
    private lateinit var characters : List<CharacterData>
    val liveData = MutableLiveData<List<CharacterData>>()


    init{
        Timber.d("Main repository Injected")
        CoroutineScope(Dispatchers.IO).launch{
            val characters=charactersDao.getAllCharactersFromDb()
            if(characters.isEmpty()) {
                getMarvelCharacters()
            }else{
                liveData.postValue(characters)
            }
        }
    }

    @WorkerThread
    suspend fun getMarvelCharacters(
    ) = withContext(Dispatchers.IO) {
            val response = characterClient.getRemoteMarvelCharacters(0)
            Timber.d("Called from webservice")
            response
                .onSuccess {
                this.response.body().whatIfNotNull {
                    characters= this.response.body()!!.data.results
                    liveData.postValue(characters)
                    charactersDao.insertCharactersList(characters)
                    Timber.d("Call result: ${characters.toString()}")
                }
            }.onError {
                    error(message())
                }
                .onException {
                    error(message())
                }
        liveData.apply { postValue(characters) }
    }
}