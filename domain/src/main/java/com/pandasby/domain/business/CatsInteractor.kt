package com.pandasby.domain.business

import com.pandasby.domain.executors.PostExecutionThread
import com.pandasby.domain.repository.CatsRepository
import javax.inject.Inject

class CatsInteractor @Inject constructor(
    postExecutionThread: PostExecutionThread,
    private val catsRepository: CatsRepository
) : BaseInteractor(postExecutionThread) {

    fun getCatListObservable(limit: Int) = catsRepository.getCatListObservable(limit)
        .subscribeOn(threadExecution)
        .observeOn(postExecutionThread)
}
