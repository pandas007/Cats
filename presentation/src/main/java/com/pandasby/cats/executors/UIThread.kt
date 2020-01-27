package com.pandasby.cats.executors

import com.pandasby.domain.executors.PostExecutionThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

class UIThread : PostExecutionThread {

    override fun getScheduler(): Scheduler = AndroidSchedulers.mainThread()
}