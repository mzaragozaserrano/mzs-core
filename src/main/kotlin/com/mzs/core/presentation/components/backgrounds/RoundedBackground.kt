package com.mzs.core.presentation.components.backgrounds

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.thecocktailapp.core.presentation.compose.components.texts.NormalText

@Composable
fun RoundedBackground(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    cornerRadius: Dp,
    content: @Composable ColumnScope.() -> Unit,
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        shape = RoundedCornerShape(size = cornerRadius),
        content = content
    )
}

@Preview
@Composable
private fun RoundedBackgroundPrev() {
    RoundedBackground(
        modifier = Modifier.padding(all = 16.dp),
        backgroundColor = Color.White,
        cornerRadius = 12.dp
    ) {
        NormalText(
            modifier = Modifier.padding(all = 16.dp),
            color = Color.Black,
            text = "This is a trial text"
        )
    }
}