package com.marcosholgado

import com.marcosholgado.core.CoreComponentHolder
import com.marcosholgado.core.di.CoreComponentProvider
import com.marcosholgado.core.di.CoreComponent
import com.marcosholgado.core.di.DaggerCoreComponent
import com.marcosholgado.daggerplayground.di.DaggerAppComponent
import com.marcosholgado.mymodule.Entry
import dagger.android.DaggerApplication
import dagger.android.AndroidInjector

class MyApplication : DaggerApplication(), CoreComponentProvider {

    private lateinit var coreComponent: CoreComponent

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent
            .builder()
            .application(this)
            .coreComponent(provideCoreComponent())
            .build()
    }

    override fun provideCoreComponent(): CoreComponent {
        if (!this::coreComponent.isInitialized) {
            coreComponent = DaggerCoreComponent
                .builder()
                .build()
        }
        return coreComponent
    }

    override fun onCreate() {
        super.onCreate()
        CoreComponentHolder.coreComponent = coreComponent
        Entry.onCreate()
    }
}