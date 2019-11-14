package com.sxhardha.slocator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class EntranceVMFactory(
    private val repository: EntranceRepository,
    private val dispatchers: Dispatchers
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        EntranceFragmentViewModel(repository, dispatchers) as T
}