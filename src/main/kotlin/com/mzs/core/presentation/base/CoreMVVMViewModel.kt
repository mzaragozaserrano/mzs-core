package com.mzs.core.presentation.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

abstract class CoreMVVMViewModel<State> : ViewModel() {

    private val _uiState by lazy { MutableStateFlow(value = createInitialState()) }
    val uiState = _uiState.asStateFlow()

    protected abstract fun createInitialState(): State

    protected fun onUpdateUiState(update: State.() -> State) {
        _uiState.update { it.update() }
    }

    fun getViewModelState(): State = _uiState.value

}