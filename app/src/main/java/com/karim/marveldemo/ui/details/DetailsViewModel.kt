package com.karim.marveldemo.ui.details

import androidx.databinding.Bindable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.karim.marveldemo.data.MarvelCharacter
import com.karim.marveldemo.repository.DetailsRepository
import com.skydoves.bindables.BindingViewModel
import com.skydoves.bindables.asBindingProperty
import com.skydoves.bindables.bindingProperty
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import timber.log.Timber

class DetailsViewModel  @AssistedInject constructor(
    detailRepository: DetailsRepository,
    @Assisted private val characterId: Int
) : BindingViewModel() {

    private val characterIndex: MutableStateFlow<Int> = MutableStateFlow(0)

    private var characterInfoFlow = characterIndex.flatMapLatest {
        detailRepository.getCharacterData(
            characterId = characterId,
            onComplete = { isLoading = false },
            onError = { Timber.d("Error has occurred while retrieving data from repo to viewmodel.") }
        )
    }

    @get:Bindable
    val characterData: MarvelCharacter? by characterInfoFlow.asBindingProperty(viewModelScope, null)

    @get:Bindable
    var isLoading: Boolean by bindingProperty(false)
        private set

    @dagger.assisted.AssistedFactory
    interface AssistedFactory {
        fun create(characterId: Int): DetailsViewModel
    }

    companion object {
        fun provideFactory(
            assistedFactory: AssistedFactory,
            characterId: Int
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return assistedFactory.create(characterId) as T
            }
        }
    }
}