package com.sxhardha.slocator.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sxhardha.slocator.model.CoroutineDispatchers
import com.sxhardha.slocator.model.Cat
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch


class EntranceFragmentViewModel(
    private val entranceRepository: EntranceRepository,
    private val coroutineDispatchers: CoroutineDispatchers
) : ViewModel() {

    private val _cats = MutableLiveData<List<Cat>>()
    val cats: LiveData<List<Cat>> = _cats

    private val coroutineExceptionHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, t ->
            t.printStackTrace()
        }

    init {
        viewModelScope.launch(coroutineDispatchers.ioDispatchers + coroutineExceptionHandler) {
            val dataFromNetwork = entranceRepository.getAllCats()
            if (dataFromNetwork.isSuccessful) {
                insertToDatabase(dataFromNetwork.body())
            }
        }
    }

    private suspend fun insertToDatabase(body: List<Cat>?) {
        body?.let {
            entranceRepository.insertCats(it)
        }

        val catsFromDb = entranceRepository.selectAllCats()

        _cats.postValue(catsFromDb)
    }
}