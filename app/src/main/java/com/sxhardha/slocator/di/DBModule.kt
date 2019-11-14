package com.sxhardha.slocator.di

import android.app.Application
import androidx.room.Room
import com.sxhardha.slocator.database.CatDAO
import com.sxhardha.slocator.database.CatDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DBModule {

    @JvmStatic
    @Provides
    @Singleton
    fun provideDatabase(application: Application): CatDatabase =
        Room.databaseBuilder(
            application,
            CatDatabase::class.java,
            "cat_db"
        ).fallbackToDestructiveMigration().build()

    @JvmStatic
    @Provides
    @Singleton
    fun bindDao(catDatabase: CatDatabase): CatDAO = catDatabase.catDao()

}