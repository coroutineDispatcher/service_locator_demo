package com.sxhardha.slocator

class EntranceRepository(
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
