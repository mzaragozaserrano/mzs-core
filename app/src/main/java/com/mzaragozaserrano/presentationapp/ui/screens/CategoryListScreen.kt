package com.mzaragozaserrano.presentationapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mzaragozaserrano.compose.composables.utils.Recycler
import com.mzaragozaserrano.presentationapp.ui.components.Category
import com.mzaragozaserrano.presentationapp.ui.utils.CategoryType
import com.mzaragozaserrano.presentationapp.ui.utils.createCategoryList

@Composable
fun CategoryListScreen(modifier: Modifier = Modifier, onCategoryClicked: (CategoryType) -> Unit) {
    Recycler(
        modifier = modifier.background(MaterialTheme.colorScheme.background),
        lineModifier = Modifier.padding(horizontal = 16.dp),
        list = createCategoryList()
    ) { category ->
        Category(category = category, onCategoryClicked = onCategoryClicked)
    }
}