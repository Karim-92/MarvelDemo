package com.karim.marveldemo.hiltdi

import android.app.Application
import androidx.room.Room
import com.karim.marveldemo.persistence.CharactersDao
import com.karim.marveldemo.persistence.CharactersDb
import com.karim.marveldemo.persistence.MarvelTypeConverters
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(
        application: Application,
        typeConverters: MarvelTypeConverters
    ): CharactersDb {
        return Room
            .databaseBuilder(application, CharactersDb::class.java, "Pokedex.db")
            .fallbackToDestructiveMigration()
            .addTypeConverter(typeConverters)
            .build()
    }

    @Provides
    @Singleton
    fun provideCharactersDao(charactersDb: CharactersDb): CharactersDao {
        return charactersDb.charactersDao()
    }
}
