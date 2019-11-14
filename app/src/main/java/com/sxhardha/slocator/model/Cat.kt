package com.sxhardha.slocator.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "cats")
@JsonClass(generateAdapter = false)
data class Cat(
    @Json(name = "id")
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @Json(name = "url")
    @ColumnInfo(name = "url")
    val url: String
)
