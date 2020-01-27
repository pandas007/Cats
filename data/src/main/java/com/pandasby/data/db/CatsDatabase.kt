package com.pandasby.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavoriteCat::class], version = 1)
abstract class CatsDatabase : RoomDatabase() {
    abstract fun getFavoriteCatsDao(): FavoriteCatsDao
}