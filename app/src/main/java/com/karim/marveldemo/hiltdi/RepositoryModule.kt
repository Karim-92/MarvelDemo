package com.karim.marveldemo.hiltdi

import com.karim.marveldemo.data.CharactersData
import com.karim.marveldemo.network.CharacterClient
import com.karim.marveldemo.persistence.CharactersDao
import com.karim.marveldemo.repository.MainRepository
import com.skydoves.sandwich.ResponseDataSource
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
      charactersDataSource: ResponseDataSource<List<CharactersData>>,
      charactersDao: CharactersDao
    ): MainRepository {
        return MainRepository(characterClient, charactersDataSource, charactersDao)
    }

}