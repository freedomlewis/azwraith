package com.marcosholgado.core.di

import com.marcosholgado.core.ExpensiveObject
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CoreModule {
    @Provides
    @Singleton
    fun provideExpensiveObject(): ExpensiveObject = ExpensiveObject()
}