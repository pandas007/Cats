package com.pandasby.domain.business

import com.pandasby.domain.executors.PostExecutionThread
import com.pandasby.domain.executors.ThreadExecution
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

abstract class BaseInteractor {

    protected var postExecutionThread: Scheduler
    protected var threadExecution: Scheduler

    constructor(postExecutionThread: PostExecutionThread) {
        this.postExecutionThread = postExecutionThread.getScheduler()
        this.threadExecution = Schedulers.io()
    }
    constructor(postExecutionThread: PostExecutionThread, threadExecution: ThreadExecution) {
        this.postExecutionThread = postExecutionThread.getScheduler()
        this.threadExecution = Schedulers.from(threadExecution)
    }
}