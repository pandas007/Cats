package com.pandasby.cats.di

import com.pandasby.cats.main.CatsPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(presenter: CatsPresenter)
}