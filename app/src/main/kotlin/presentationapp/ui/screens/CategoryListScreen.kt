package presentationapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mzaragozaserrano.compose.composables.buttons.WavyButton
import com.mzaragozaserrano.compose.composables.utils.Recycler
import presentationapp.ui.utils.CategoryType
import presentationapp.ui.utils.createCategoryList

@Composable
fun CategoryListScreen(modifier: Modifier = Modifier, onCategoryClicked: (CategoryType) -> Unit) {
    Recycler(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(all = 16.dp),
        list = createCategoryList(),
        spaceBetween = 16.dp,
        hasLine = false
    ) { category ->
        WavyButton(
            modifier = Modifier.fillMaxWidth(),
            buttonBackgroundColor = MaterialTheme.colorScheme.secondaryContainer,
            iconBackgroundColor = MaterialTheme.colorScheme.tertiaryContainer,
            iconId = category.imageId,
            iconTint = MaterialTheme.colorScheme.onTertiaryContainer,
            textId = category.textId,
            textColor = MaterialTheme.colorScheme.onSecondaryContainer
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