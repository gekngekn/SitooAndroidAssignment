package com.gekn.sitooandroidassignment

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Application class for Dog Fetcher
 */
@HiltAndroidApp
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {
        instance = this
    }

    companion object {
        var instance: MainApplication? = null
    }

}