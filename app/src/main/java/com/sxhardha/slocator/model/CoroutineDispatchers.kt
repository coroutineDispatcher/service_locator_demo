package com.sxhardha.slocator.model

import kotlinx.coroutines.CoroutineDispatcher


data class CoroutineDispatchers(
    val mainDispatcher: CoroutineDispatcher,
    val ioDispatchers: CoroutineDispatcher
)