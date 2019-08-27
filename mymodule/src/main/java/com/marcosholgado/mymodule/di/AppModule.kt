package com.marcosholgado.mymodule.di

import dagger.Module
import dagger.Provides
import java.util.*

@Module
class AppModule {
    @Provides
    fun provideOtherObject(): Date {
        return Date()
    }
}