package com.mzs.core.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

abstract class CoreMVIViewModel<State, Intent, Action, Result> : ViewModel() {

    private val _mutableViewState by lazy { MutableStateFlow(createInitialState()) }
    val mutableViewState: StateFlow<State> get() = _mutableViewState.asStateFlow()

    val intentFlow = MutableSharedFlow<Intent>()

    init {
        handleIntent()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun handleIntent() {
        viewModelScope.launch {
            intentFlow
                .map { it.mapToAction() }
                .flatMapLatest { processAction(it) }
                .collect { collectState(it.mapToState()) }
        }
    }

    protected abstract fun createInitialState(): State
    protected abstract fun Intent.mapToAction(): Action
    protected abstract suspend fun processAction(action: Action): Flow<Result>
    protected abstract fun Result.mapToState(): State

    private fun collectState(state: State) {
        _mutableViewState.value = state
    }

}