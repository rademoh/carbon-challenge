package com.rabiu.carbonchallenge

import android.app.Application
import com.facebook.stetho.Stetho
import com.rabiu.carbonchallenge.di.repositoryModule
import com.rabiu.carbonchallenge.di.retrofitModule
import com.rabiu.carbonchallenge.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) Stetho.initializeWithDefaults(this)
        startKoin {
            printLogger()
            androidContext(this@App)
            modules(listOf(retrofitModule, repositoryModule, viewModelModule))
        }
    }
}