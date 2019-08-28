package com.marcosholgado.mymodule

import com.marcosholgado.core.CoreComponentHolder
import com.marcosholgado.core.di.AzwraithEntry
import com.marcosholgado.mymodule.di.DaggerAppComponent
import dagger.android.AndroidInjector


object Entry : AzwraithEntry() {
    override fun applicationInjector(): AndroidInjector<out AzwraithEntry> {
        return DaggerAppComponent.builder()
            .entry(this)
            .coreComponent(CoreComponentHolder.coreComponent)
            .build()
    }
}