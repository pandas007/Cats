package com.pandasby.cats.di

import android.app.Application
import android.content.Context
import com.pandasby.cats.executors.UIThread
import com.pandasby.domain.executors.PostExecutionThread
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    fun getUiThread(): PostExecutionThread = UIThread()

}