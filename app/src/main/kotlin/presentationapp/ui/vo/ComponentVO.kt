package presentationapp.ui.vo

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable

data class ComponentVO(@StringRes val nameId: Int, val listItems: List<@Composable () -> Unit>)