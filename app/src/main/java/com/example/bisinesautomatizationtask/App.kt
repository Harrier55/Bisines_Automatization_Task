package com.example.bisinesautomatizationtask

import android.app.Application
import com.example.bisinesautomatizationtask.di.mainModule
import com.example.datasourse.di.dataModule
import com.example.feature_stores.di.storesModule
import com.example.dress_testing.di.dressModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        myAppInstance = this

        /** init koin **/
        startKoin {
            androidContext(this@App)
            androidLogger()
            modules(listOf(
                mainModule,
                dressModule,
                dataModule,
                storesModule
            ))
        }
    }

    companion object {
        lateinit var myAppInstance: App
            private set
    }
}