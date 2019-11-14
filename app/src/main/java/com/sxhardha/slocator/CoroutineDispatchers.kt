package com.sxhardha.slocator

import kotlinx.coroutines.CoroutineDispatcher


data class CoroutineDispatchers(
    val mainDispatcher: CoroutineDispatcher,
    val ioDispatchers: CoroutineDispatcher
)