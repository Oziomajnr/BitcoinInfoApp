package com.ozioma.bitcoininfo

import android.app.Application
import com.ozioma.bitcoininfo.dependencyinjection.AppComponent
import com.ozioma.bitcoininfo.dependencyinjection.DaggerAppComponent

class BitcoinDataApplication : Application() {
    companion object {
        private lateinit var appComponent: AppComponent
        fun getAppComponent(): AppComponent {
            return appComponent
        }
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .build().also {
                it.inject(this)
            }
    }
}