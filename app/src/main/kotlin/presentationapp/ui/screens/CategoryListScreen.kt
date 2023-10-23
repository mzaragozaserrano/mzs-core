package presentationapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
        numberCells = 3,
        spaceBetween = 16.dp
    ) { category ->
        Category(modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(12.dp))
            .clickable(
                indication = rememberRipple(
                    color = MaterialTheme.colorScheme.background.copy(alpha = 0.2f)
                ),
                interactionSource = remember { MutableInteractionSource() },
                onClick = {
                    onCategoryClicked(category)
                }
            )
            .aspectRatio(1f),
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