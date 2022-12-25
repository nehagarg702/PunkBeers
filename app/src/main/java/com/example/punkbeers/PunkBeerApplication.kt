package com.example.punkbeers

import android.app.Application
import com.example.punkbeers.di.component.ApplicationComponent
import com.example.punkbeers.di.component.DaggerApplicationComponent
import com.example.punkbeers.di.module.ContextModule

class PunkBeerApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerApplicationComponent.builder()
            .contextModule(ContextModule(this)).build()
    }

    companion object {
        @JvmStatic
        lateinit var instance: PunkBeerApplication
        lateinit var appComponent : ApplicationComponent
    }
}