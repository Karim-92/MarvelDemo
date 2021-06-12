package com.karim.marveldemo.init

import android.content.Context
import androidx.startup.Initializer
import com.karim.marveldemo.BuildConfig
import timber.log.Timber

class TimberInit : Initializer<Unit> {
    override fun create(context: Context) {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Timber.d("Timber is initialized.")
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}