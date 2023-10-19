package com.mzaragozaserrano.presentationapp.ui.screens

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mzaragozaserrano.presentationapp.ui.utils.CategoryType
import com.mzaragozaserrano.presentationapp.ui.utils.getSerializableCategory
import com.mzaragozaserrano.presentationapp.ui.utils.navigation.NavArg
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryComponentViewModel @Inject constructor(savedStateHandle: SavedStateHandle) : ViewModel() {

    private val id = savedStateHandle.get<Int>(NavArg.Category.key) ?: 0

    private val _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = UiState(category = id.getSerializableCategory())
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val category: CategoryType? = null,
    )
}