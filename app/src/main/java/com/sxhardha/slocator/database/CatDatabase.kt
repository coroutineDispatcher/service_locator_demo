package com.sxhardha.slocator.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sxhardha.slocator.model.Cat


@Database(entities = [Cat::class], version = 1)
abstract class CatDatabase : RoomDatabase() {
    abstract fun catDao(): CatDAO
}