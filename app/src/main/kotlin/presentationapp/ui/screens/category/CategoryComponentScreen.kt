package presentationapp.ui.screens.category

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mzaragozaserrano.presentation.compose.components.utils.Recycler
import presentationapp.ui.components.Component
import presentationapp.ui.utils.createComponentList
import presentationapp.ui.viewmodels.CategoryComponentViewModel

@Composable
fun CategoryComponentScreen(
    modifier: Modifier = Modifier,
    viewModel: CategoryComponentViewModel = hiltViewModel(),
) {

    val state by viewModel.state.collectAsState()
    state.category?.let { category ->
        Recycler(
            modifier = modifier.background(MaterialTheme.colorScheme.background),
            list = category.createComponentList()
        ) { component ->
            Component(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp),
                component = component
            )
        }
    }

}