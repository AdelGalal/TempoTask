package com.tempo.tempotask

import android.app.Application
import com.tempo.tempotask.di.module.appModule
import com.tempo.tempotask.di.module.repoModule
import com.tempo.tempotask.di.module.viewModelModule

import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(appModule, repoModule, viewModelModule))
        }
    }
}