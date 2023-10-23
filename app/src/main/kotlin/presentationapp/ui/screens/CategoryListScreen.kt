package presentationapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mzaragozaserrano.compose.composables.utils.Recycler
import presentationapp.ui.components.Category
import presentationapp.ui.utils.CategoryType
import presentationapp.ui.utils.createCategoryList

@Composable
fun CategoryListScreen(modifier: Modifier = Modifier, onCategoryClicked: (CategoryType) -> Unit) {
    Recycler(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(all = 16.dp),
        hasLine = false,
        list = createCategoryList(),
        numberCells = 2,
        spaceBetween = 16.dp
    ) { category ->
        Category(modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable { onCategoryClicked(category) },
            imageId = category.imageId,
            textId = category.textId
        )
    }
}

@Preview
@Composable
private fun CategoryListScreenPrev() {
    CategoryListScreen(onCategoryClicked = {})
}