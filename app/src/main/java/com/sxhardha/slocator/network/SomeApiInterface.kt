package com.sxhardha.slocator.network

import com.sxhardha.slocator.model.Cat
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SomeApiInterface {

    @GET("search")
    suspend fun getListOfCats(
        @Query("limit") limit: Int = 10,
        @Query("mime_types") type: String = "jpg"
    ): Response<List<Cat>>
}