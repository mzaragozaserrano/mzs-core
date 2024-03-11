package com.mzs.core.presentation.view.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class MVIViewModel<S, I> : ViewModel() {

    private val initialState: S by lazy { createInitialState() }
    private val _mutableViewState = MutableStateFlow(initialState)

    abstract fun createInitialState(): S

    val mutableViewState: StateFlow<S> get() = _mutableViewState.asStateFlow()

    val intentFlow = MutableSharedFlow<I>()

    open fun handleIntent() {}

    fun collectState(state: S) {
        _mutableViewState.value = state
    }

}