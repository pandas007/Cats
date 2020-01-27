package com.pandasby.domain.repository

import com.pandasby.domain.entity.FavoriteCatEntity
import io.reactivex.Completable
import io.reactivex.Flowable

interface FavoriteCatsRepository {

    fun getFavoriteCatsListFlowable(): Flowable<List<FavoriteCatEntity>>
    fun addFavoriteCatToDatabase(favoriteCatEntity: FavoriteCatEntity): Completable
}