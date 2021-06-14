package com.karim.marveldemo.hiltdi

import com.karim.marveldemo.network.CharacterClient
import com.karim.marveldemo.persistence.CharactersDao
import com.karim.marveldemo.repository.DetailsRepository
import com.karim.marveldemo.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideMainRepository(
        characterClient: CharacterClient,
        charactersDao: CharactersDao
    ): MainRepository {
        return MainRepository(characterClient, charactersDao)
    }

    @Provides
    @ViewModelScoped
    fun provideDetailsRepository(
        characterClient: CharacterClient,
        charactersDao: CharactersDao
    ): DetailsRepository {
        return DetailsRepository(characterClient, charactersDao)
    }

}