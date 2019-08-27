package com.marcosholgado.mymodule

import com.marcosholgado.core.CoreComponentHolder
import com.marcosholgado.core.di.NuwaInjector
import com.marcosholgado.mymodule.di.DaggerAppComponent
import dagger.android.AndroidInjector


object Entry : NuwaInjector() {
    override fun applicationInjector(): AndroidInjector<out NuwaInjector> {
        return DaggerAppComponent.builder()
            .entry(this)
            .coreComponent(CoreComponentHolder.coreComponent)
            .build()
    }
}