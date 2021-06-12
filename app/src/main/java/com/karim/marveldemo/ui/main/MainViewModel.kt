package com.karim.marveldemo.ui.main

import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import com.karim.marveldemo.init.LiveCoroutinesViewModel
import com.karim.marveldemo.repository.MainRepository
import com.skydoves.bindables.bindingProperty
import dagger.hilt.android.lifecycle.HiltViewModel
import org.w3c.dom.CharacterData
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val savedInstanceHandle: SavedStateHandle) : LiveCoroutinesViewModel() {

    val characterData: LiveData<List<CharacterData>>


    @get:Bindable
    var isLoading: Boolean by bindingProperty(false)
        private set

    @get:Bindable
    var toast: String? by bindingProperty(null)
        private set


    init{

        characterData = launchOnViewModelScope {
            mainRepository.getMarvelCharacters(disposables) { toast = it}
        }
    }
}