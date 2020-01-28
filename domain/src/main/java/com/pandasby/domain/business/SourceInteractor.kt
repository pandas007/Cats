package com.pandasby.domain.business

import com.pandasby.domain.entity.Source
import com.pandasby.domain.executors.PostExecutionThread
import com.pandasby.domain.repository.SourceRepository
import javax.inject.Inject

class SourceInteractor @Inject constructor(
    postExecutionThread: PostExecutionThread,
    private val sourceRepository: SourceRepository
) : BaseInteractor(postExecutionThread) {

    fun saveSource(source: Source) {
        sourceRepository.saveSource(source)
    }
}