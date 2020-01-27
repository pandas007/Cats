package com.pandasby.cats.di

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.pandasby.cats.executors.UIThread
import com.pandasby.data.repository.CatsRepositoryImpl
import com.pandasby.data.rest.RestApi
import com.pandasby.data.rest.RestService
import com.pandasby.domain.executors.PostExecutionThread
import com.pandasby.domain.repository.CatsRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule(private val app: Application) {

    @Provides
    @Singleton
    fun provideContext(): Context = app

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
    fun getUiThread(): PostExecutionThread = UIThread()

    @Provides
    @Singleton
    fun getCatsRepository(restApi: RestApi): CatsRepository = CatsRepositoryImpl(RestService(restApi))
}