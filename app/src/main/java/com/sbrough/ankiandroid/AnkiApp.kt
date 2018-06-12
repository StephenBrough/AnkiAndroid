package com.sbrough.ankiandroid

import android.app.Application
import timber.log.Timber

class AnkiApp : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        appContext = this
    }

    companion object {
        lateinit var appContext: Application
    }
}
