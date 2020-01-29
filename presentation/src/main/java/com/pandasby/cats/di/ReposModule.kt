package com.pandasby.cats.di

import com.pandasby.data.db.FavoriteCatsDao
import com.pandasby.data.repository.CatsRepositoryImpl
import com.pandasby.data.repository.FavoriteCatsRepositoryImpl
import com.pandasby.data.repository.FilesRepositoryImpl
import com.pandasby.data.rest.RestApi
import com.pandasby.data.rest.RestService
import com.pandasby.domain.repository.CatsRepository
import com.pandasby.domain.repository.FavoriteCatsRepository
import com.pandasby.domain.repository.FilesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ReposModule {

    @Provides
    @Singleton
    fun getCatsRepository(restApi: RestApi): CatsRepository = CatsRepositoryImpl(RestService(restApi))

    @Provides
    @Singleton
    fun getFavoriteCatsRepository(favoriteCatsDao: FavoriteCatsDao): FavoriteCatsRepository =
        FavoriteCatsRepositoryImpl(favoriteCatsDao)

    @Provides
    @Singleton
    fun getFilesRepository(): FilesRepository = FilesRepositoryImpl()
}