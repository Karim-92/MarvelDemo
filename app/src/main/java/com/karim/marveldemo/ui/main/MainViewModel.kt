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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val savedStateHandle: SavedStateHandle
) : BindingViewModel() {


    @get:Bindable
    var isLoading: Boolean by bindingProperty(false)
        private set

    @get:Bindable
    var searchIndicator: Boolean = false

    @get: Bindable
    lateinit var searchResults: List<MarvelCharacter>

    private val characterIndex: MutableStateFlow<Int> = MutableStateFlow(0)

    private val characterListFlow = characterIndex.flatMapLatest { page ->
        mainRepository.getMarvelCharacters(
            page = page,
            query = null,
            fromSearch = false,
            onStart = { isLoading = true },
            onComplete = { isLoading = false },
            onError = { Timber.d(" Error Message: $it") }
        )
    }


    @get:Bindable
    val characterData: List<MarvelCharacter> by characterListFlow.asBindingProperty(
        viewModelScope,
        emptyList()
    )

    @FlowPreview
    fun onQueryChanged(query: String) {
        Timber.d("Entered onQueryChanged from ViewModel with query: $query")
        viewModelScope.launch {
            Timber.d("Search flow started.")
            (mainRepository.getMarvelCharacters(
                page = 0,
                query = query,
                fromSearch = true,
                onStart = { isLoading = true },
                onComplete = { isLoading = false },
                onError = { Timber.d(" Error Message: $it") }
            ).debounce(3000)
                .distinctUntilChanged()
                .flowOn(Dispatchers.IO)
                .collect {
                    Timber.d("Search flow collected.")
                    searchResults = emptyList()
                    searchResults = it
                    searchIndicator = true
                    Timber.d("Search Results Value: $searchResults")
                })
            getNextCharacterList()
        }
        searchIndicator=false
    }

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
}