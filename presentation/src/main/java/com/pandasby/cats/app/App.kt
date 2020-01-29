package com.pandasby.cats.app

import android.app.Application
import com.pandasby.cats.di.AppComponent
import com.pandasby.cats.di.DaggerAppComponent

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .application(this)
            .build()
    }

    companion object {
        @JvmStatic
        var appComponent: AppComponent? = null
            private set
    }
}