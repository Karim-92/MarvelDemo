package com.karim.marveldemo.repository

import androidx.lifecycle.MutableLiveData
import com.karim.marveldemo.PAGING_SIZE
import com.karim.marveldemo.data.CharactersData
import com.karim.marveldemo.network.CharacterClient
import com.karim.marveldemo.persistence.CharactersDao
import com.skydoves.sandwich.ResponseDataSource
import com.skydoves.sandwich.disposables.CompositeDisposable
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.onSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class MainRepository constructor(
    private val characterClient: CharacterClient,
    private val  charactersDataSource : ResponseDataSource<List<CharactersData>>,
    private val charactersDao : CharactersDao) : Repository{


    init{
        Timber.d("Main repository Injected")
    }

    suspend fun getMarvelCharacters(disposable: CompositeDisposable,
                                    error: (String?) -> Unit
    ) = withContext(Dispatchers.IO) {

        val liveData = MutableLiveData<List<CharactersData>>()
        val characters=charactersDao.getAllCharactersFromDb()
        if(characters.isEmpty()){
            val response = characterClient.getRemoteMarvelCharacters(PAGING_SIZE)
            response
                .onSuccess {
                if(data!=null) {
                    liveData.postValue(this.data!!.results)
                    charactersDao.insertCharactersList(data!!.results)
                }
            }.onError {
                    TODO("Handle Error Codes")
                    Timber.d("Error occurred")
                }
                .onException {
                    TODO("Handle exceptions")
                    Timber.d("Exception Error")
                }
        }
        liveData.apply { postValue(characters) }
    }
}