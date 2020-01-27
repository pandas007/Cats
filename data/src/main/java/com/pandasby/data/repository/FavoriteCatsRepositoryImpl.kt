package com.pandasby.data.repository

import com.pandasby.data.db.FavoriteCat
import com.pandasby.data.db.FavoriteCatsDao
import com.pandasby.domain.entity.FavoriteCatEntity
import com.pandasby.domain.repository.FavoriteCatsRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class FavoriteCatsRepositoryImpl @Inject constructor(private val favoriteCatsDao: FavoriteCatsDao)
    : FavoriteCatsRepository {

    override fun getFavoriteCatsListFlowable(): Flowable<List<FavoriteCatEntity>> =
        favoriteCatsDao.getFavoriteCatList().map {
                cats -> cats.map { FavoriteCatEntity(it.id, it.url) }
        }

    override fun addFavoriteCatToDatabase(favoriteCatEntity: FavoriteCatEntity): Completable =
        Completable.fromAction {
            favoriteCatsDao.addFavoriteCat(FavoriteCat(favoriteCatEntity.id, favoriteCatEntity.url))
        }

}