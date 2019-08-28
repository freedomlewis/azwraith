package com.marcosholgado.mymodule.di

import com.marcosholgado.core.di.CoreComponent
import com.marcosholgado.mymodule.Entry
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component(
    modules = [AppModule::class,
        ActivityBindingModule::class,
        AndroidSupportInjectionModule::class
    ],
    dependencies = [CoreComponent::class]
)
@ModuleScope
interface AppComponent : AndroidInjector<Entry> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun entry(application: Entry): Builder

        fun coreComponent(coreComponent: CoreComponent): Builder
        fun build(): AppComponent
    }
}