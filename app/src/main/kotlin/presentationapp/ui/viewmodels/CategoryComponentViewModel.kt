package presentationapp.ui.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import presentationapp.ui.utils.CategoryType
import presentationapp.ui.utils.getSerializableCategory
import presentationapp.ui.utils.navigation.NavArg
import javax.inject.Inject

@HiltViewModel
class CategoryComponentViewModel @Inject constructor(savedStateHandle: SavedStateHandle) : ViewModel() {

    private val id = savedStateHandle.get<Int>(key = NavArg.Category.key) ?: 0

    private val _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = UiState(category = id.getSerializableCategory())
        }
    }

    data class UiState(val category: CategoryType? = null)

}