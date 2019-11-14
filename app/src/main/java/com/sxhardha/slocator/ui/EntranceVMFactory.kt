package com.sxhardha.slocator.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sxhardha.slocator.model.CoroutineDispatchers


class EntranceVMFactory(
    private val repository: EntranceRepository,
    private val coroutineDispatchers: CoroutineDispatchers
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        EntranceFragmentViewModel(repository, coroutineDispatchers) as T
}