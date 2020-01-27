package com.pandasby.domain.business

import com.pandasby.domain.entity.FavoriteCatEntity
import com.pandasby.domain.executors.PostExecutionThread
import com.pandasby.domain.repository.FavoriteCatsRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class FavoriteCatsInteractor @Inject constructor(
    postExecutionThread: PostExecutionThread,
    private val favoriteCatsRepository: FavoriteCatsRepository
) : BaseInteractor(postExecutionThread) {

    fun getFavoriteCatsList(): Flowable<List<FavoriteCatEntity>> =
        favoriteCatsRepository.getFavoriteCatsListFlowable()
            .subscribeOn(threadExecution)
            .observeOn(postExecutionThread)

    fun addFavoriteCat(favoriteCatEntity: FavoriteCatEntity): Completable =
        favoriteCatsRepository.addFavoriteCatToDatabase(favoriteCatEntity)
            .subscribeOn(threadExecution)
            .observeOn(postExecutionThread)
}