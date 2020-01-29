package com.pandasby.cats.di

import android.content.Context
import androidx.room.Room
import com.pandasby.data.db.CatsDatabase
import com.pandasby.data.db.FavoriteCatsDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun getCatsDatabase(context: Context): CatsDatabase =
        Room.databaseBuilder(context, CatsDatabase::class.java, "cats_database").build()

    @Provides
    @Singleton
    fun getFavoriteCatsDao(catsDatabase: CatsDatabase): FavoriteCatsDao =
        catsDatabase.getFavoriteCatsDao()
}