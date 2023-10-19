package com.mzaragozaserrano.presentationapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mzaragozaserrano.presentationapp.ui.utils.CategoryType
import com.mzaragozaserrano.presentationapp.ui.utils.createCategoryList

@Composable
fun Category(
    modifier: Modifier = Modifier,
    category: CategoryType,
    onCategoryClicked: (CategoryType) -> Unit,
) {
    Text(
        modifier = modifier
            .clickable { onCategoryClicked(category) }
            .padding(vertical = 16.dp)
            .fillMaxWidth(),
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.onBackground,
        text = stringResource(id = category.id).uppercase(),
        style = MaterialTheme.typography.titleLarge
    )
}

@Preview(showBackground = true)
@Composable
private fun CategoryPrev() {
    Category(category = createCategoryList().first()) {
        //Here will go the action when clicking on each category
    }
}