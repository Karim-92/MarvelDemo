package com.karim.marveldemo.init

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.skydoves.bindables.BindingViewModel
import com.skydoves.sandwich.disposables.CompositeDisposable
import kotlinx.coroutines.Dispatchers

abstract class LiveCoroutinesViewModel : BindingViewModel() {

    val disposables: CompositeDisposable = CompositeDisposable()

    inline fun <T> launchOnViewModelScope(crossinline block: () -> Unit): LiveData<T> {
        return liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            emitSource(block())
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}
