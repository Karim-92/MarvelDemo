package com.karim.marveldemo.ui.main

import androidx.annotation.MainThread
import androidx.databinding.Bindable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.karim.marveldemo.data.MarvelCharacter
import com.karim.marveldemo.repository.MainRepository
import com.skydoves.bindables.BindingViewModel
import com.skydoves.bindables.asBindingProperty
import com.skydoves.bindables.bindingProperty
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import timber.log.Timber
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val savedStateHandle: SavedStateHandle
) : BindingViewModel() {

    private var queryString: String?=null

    @get:Bindable
    var isLoading: Boolean by bindingProperty(false)
        private set

    private val characterIndex: MutableStateFlow<Int> = MutableStateFlow(0)

    private var characterListFlow = characterIndex.flatMapLatest { page ->
        mainRepository.getMarvelCharacters(
            page = page,
            query = queryString,
            onStart = { isLoading = true },
            onComplete = { isLoading = false },
            onError = { Timber.d(" Error Message: $it" ) }
        )
    }

    @get:Bindable
    val characterData: List<MarvelCharacter> by characterListFlow.asBindingProperty(
        viewModelScope,
        emptyList()
    )

    @get:Bindable
    var toastMessage: String? by bindingProperty(null)
        private set


    init {
        Timber.d("init MainViewModel")
    }

    @MainThread
    fun getNextCharacterList() {
        if (!isLoading) {
            characterIndex.value++
        }
    }

    fun onQuery(query: String): Boolean {
//        TODO("Implement query fetching")
        return true
    }

}