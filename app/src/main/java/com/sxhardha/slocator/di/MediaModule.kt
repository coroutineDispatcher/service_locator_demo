package com.sxhardha.slocator.di

import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object MediaModule {

    @JvmStatic
    @Singleton
    @Provides
    fun providePicasso() = Picasso.get()
}
