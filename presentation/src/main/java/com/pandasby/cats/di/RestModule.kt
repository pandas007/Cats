package com.pandasby.cats.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.pandasby.data.rest.RestApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RestModule {

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
}