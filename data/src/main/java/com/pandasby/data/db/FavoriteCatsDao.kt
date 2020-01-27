package com.pandasby.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Flowable

@Dao
interface FavoriteCatsDao {

    @Query("SELECT * FROM favorite_cats")
    fun getFavoriteCatList(): Flowable<List<FavoriteCat>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addFavoriteCat(favoriteCat: FavoriteCat)
}