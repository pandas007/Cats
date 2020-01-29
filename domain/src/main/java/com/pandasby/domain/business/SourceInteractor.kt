package com.pandasby.domain.business

import com.pandasby.domain.entity.Source
import com.pandasby.domain.executors.PostExecutionThread
import com.pandasby.domain.repository.FilesRepository
import javax.inject.Inject

class SourceInteractor @Inject constructor(
    postExecutionThread: PostExecutionThread,
    private val filesRepository: FilesRepository
) : BaseInteractor(postExecutionThread) {

    fun saveSource(source: Source) {
        filesRepository.saveFile(source)
    }
}