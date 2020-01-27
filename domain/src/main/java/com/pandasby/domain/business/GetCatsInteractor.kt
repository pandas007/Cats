package com.pandasby.domain.business

import com.pandasby.domain.executors.PostExecutionThread
import com.pandasby.domain.repository.CatsRepository
import javax.inject.Inject

class GetCatsInteractor @Inject constructor(
    postExecutionThread: PostExecutionThread,
    private val catsRepository: CatsRepository
) : BaseInteractor(postExecutionThread) {

    fun getCatListSingle(limit: Int) = catsRepository.getCatListSingle(limit)
        .subscribeOn(threadExecution)
        .observeOn(postExecutionThread)
}
