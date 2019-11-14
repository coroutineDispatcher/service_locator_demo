package com.sxhardha.slocator

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CatDAO {
    @Insert
    suspend fun insertCat(cat: Cat)

    @Query("SELECT * FROM cats")
    suspend fun selectAllCats(): List<Cat>

    @Query("DELETE FROM cats")
    fun deleteAllCats()
}