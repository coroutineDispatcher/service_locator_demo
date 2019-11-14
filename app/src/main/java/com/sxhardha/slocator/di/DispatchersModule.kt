package com.sxhardha.slocator.di

import com.sxhardha.slocator.model.CoroutineDispatchers
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
object DispatchersModule {

    @JvmStatic
    @Provides
    @Singleton
    fun provideCoroutineDispatchers(): CoroutineDispatchers = CoroutineDispatchers(
        Dispatchers.Main,
        Dispatchers.IO
    )
}
