package com.example.bisinesautomatizationtask.core

import android.app.Application
import com.example.bisinesautomatizationtask.data.di.dataModule
import com.example.bisinesautomatizationtask.features.di.homeModule
import com.yandex.mapkit.MapKitFactory
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

const val API_KEY = "81d5d4e5-58fb-4f81-a0e5-7b0858ec79da"

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        /** init maps yandex**/
        MapKitFactory.setApiKey(API_KEY)

        /** init koin **/
        startKoin {
            androidContext(this@App)
            androidLogger()
            modules(listOf(
                homeModule,
                dataModule
            ))
        }
    }
}