package com.pandasby.cats.di

import android.app.Application
import com.pandasby.cats.screens.favorite.FavoriteCatsPresenter
import com.pandasby.cats.screens.main.CatsPresenter
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    RestModule::class,
    DatabaseModule::class,
    ReposModule::class
])
interface AppComponent {

    fun inject(presenter: CatsPresenter)
    fun inject(presenter: FavoriteCatsPresenter)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}