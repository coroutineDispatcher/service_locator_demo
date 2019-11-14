package com.sxhardha.slocator.ui

import com.sxhardha.slocator.database.CatDAO
import com.sxhardha.slocator.model.Cat
import com.sxhardha.slocator.network.SomeApiInterface
import javax.inject.Inject

class EntranceRepository @Inject constructor(
    private val someApiInterface: SomeApiInterface,
    private val catDAO: CatDAO
) {

    suspend fun getAllCats() = someApiInterface.getListOfCats()

    suspend fun insertCats(listOfCats: List<Cat>) {
        listOfCats.forEach {
            catDAO.insertCat(Cat(it.id, it.url))
        }
    }

    suspend fun selectAllCats() = catDAO.selectAllCats()
}
