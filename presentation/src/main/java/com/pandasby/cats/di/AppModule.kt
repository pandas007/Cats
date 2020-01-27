package com.pandasby.cats.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.pandasby.cats.executors.UIThread
import com.pandasby.data.db.CatsDatabase
import com.pandasby.data.db.FavoriteCatsDao
import com.pandasby.data.repository.CatsRepositoryImpl
import com.pandasby.data.repository.FavoriteCatsRepositoryImpl
import com.pandasby.data.rest.RestApi
import com.pandasby.data.rest.RestService
import com.pandasby.domain.executors.PostExecutionThread
import com.pandasby.domain.repository.CatsRepository
import com.pandasby.domain.repository.FavoriteCatsRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule(private val app: Application) {

    //TODO разбить на модули !!!

    @Provides
    @Singleton
    fun provideContext(): Context = app

    //==============Rest=================

    @Provides
    @Singleton
    fun getGson(): Gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()

    @Provides
    @Singleton
    fun getRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl("https://api.thecatapi.com/v1/")
        .build()

    @Provides
    @Singleton
    fun getRestApi(retrofit: Retrofit): RestApi = retrofit.create(RestApi::class.java)

    @Provides
    @Singleton
    fun getCatsRepository(restApi: RestApi): CatsRepository = CatsRepositoryImpl(RestService(restApi))

    @Provides
    @Singleton
    fun getUiThread(): PostExecutionThread = UIThread()

    //==============Local database=================
    @Provides
    @Singleton
    fun getCatsDatabase(context: Context): CatsDatabase =
        Room.databaseBuilder(context, CatsDatabase::class.java, "cats_database").build()

    @Provides
    @Singleton
    fun getFavoriteCatsDao(catsDatabase: CatsDatabase): FavoriteCatsDao =
        catsDatabase.getFavoriteCatsDao()

    @Provides
    @Singleton
    fun getFavoriteCatsRepository(favoriteCatsDao: FavoriteCatsDao): FavoriteCatsRepository =
        FavoriteCatsRepositoryImpl(favoriteCatsDao)
}