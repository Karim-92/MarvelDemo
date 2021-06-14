package com.karim.marveldemo.ui.details

import androidx.databinding.Bindable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.karim.marveldemo.data.MarvelCharacter
import com.karim.marveldemo.repository.DetailsRepository
import com.skydoves.bindables.BindingViewModel
import com.skydoves.bindables.asBindingProperty
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.Flow

class DetailsViewModel  @AssistedInject constructor(
    detailRepository: DetailsRepository,
    @Assisted private val characterId: Int
) : BindingViewModel() {

    private val characterInfoFlow: Flow<MarvelCharacter?> = detailRepository.getCharacterData(
        characterId = characterId,
        onComplete = { },
        onError = { }
    )

    @get:Bindable
    val characterData: MarvelCharacter? by characterInfoFlow.asBindingProperty(viewModelScope, null)

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