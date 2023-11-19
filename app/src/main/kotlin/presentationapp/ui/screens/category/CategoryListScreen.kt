package presentationapp.ui.screens.category

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mzaragozaserrano.presentation.compose.components.buttons.MinimalButton
import com.mzaragozaserrano.presentation.compose.components.utils.Recycler
import presentationapp.ui.utils.CategoryType
import presentationapp.ui.utils.createCategoryList

@Composable
fun CategoryListScreen(modifier: Modifier = Modifier, onCategoryClicked: (CategoryType) -> Unit) {

    Recycler(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.background)
            .padding(all = 16.dp),
        hasLine = false,
        list = createCategoryList(),
        numberCells = 3,
        spaceBetween = 16.dp
    ) { category ->
        MinimalButton(
            modifier = Modifier.fillMaxSize(),
            iconId = category.imageId,
            iconTint = MaterialTheme.colorScheme.onBackground,
            textColor = MaterialTheme.colorScheme.onBackground,
            textId = category.textId
        ) {
            onCategoryClicked(category)
        }
    }

}

@Preview
@Composable
private fun CategoryListScreenPrev() {
    CategoryListScreen(onCategoryClicked = {})
}