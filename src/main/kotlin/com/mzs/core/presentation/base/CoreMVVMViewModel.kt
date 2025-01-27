package com.mzs.core.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class CoreMVVMViewModel<State> : ViewModel() {

    protected abstract fun createInitialState(): State

    protected fun onEmitNavigation(element: Any?) {
        viewModelScope.launch {
            _navigationCompose.send(element = element)
        }
    }

    protected fun onUpdateUiState(update: State.() -> State) {
        _uiState.update { it.update() }
    }

    private val _uiState by lazy { MutableStateFlow(value = createInitialState()) }
    val uiState = _uiState.asStateFlow()

    private val _navigationCompose = Channel<Any?>(capacity = Channel.BUFFERED)
    val navigationCompose: Flow<Any?> = _navigationCompose.receiveAsFlow()

    fun getViewModelState(): State = _uiState.value

}