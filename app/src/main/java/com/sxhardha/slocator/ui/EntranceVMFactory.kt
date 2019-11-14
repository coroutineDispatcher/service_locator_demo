package com.sxhardha.slocator.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sxhardha.slocator.model.CoroutineDispatchers
import javax.inject.Inject
import javax.inject.Provider


class EntranceVMFactory @Inject constructor(
    private val repository: Provider<EntranceRepository>,
    private val coroutineDispatchers: Provider<CoroutineDispatchers>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        EntranceFragmentViewModel(repository.get(), coroutineDispatchers.get()) as T
}