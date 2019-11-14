package com.sxhardha.slocator.di

import android.app.Application
import com.sxhardha.slocator.ui.EntranceFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, DBModule::class, MediaModule::class, DispatchersModule::class])
interface MyComponent {

    fun inject(fragment: EntranceFragment)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): MyComponent
    }
}