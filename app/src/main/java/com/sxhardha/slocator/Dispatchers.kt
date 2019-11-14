package com.sxhardha.slocator

import kotlinx.coroutines.CoroutineDispatcher


data class Dispatchers(
    val mainDispatcher: CoroutineDispatcher,
    val ioDispatchers: CoroutineDispatcher
)