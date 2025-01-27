package com.mzs.core.presentation.components.compose.backgrounds

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun RoundedEdgeBackground(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    borderColor: Color,
    borderWidth: Dp,
    cornerRadius: Dp,
    content: @Composable ColumnScope.() -> Unit,
) {
    Card(
        shape = RoundedCornerShape(size = cornerRadius),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        border = BorderStroke(color = borderColor, width = borderWidth),
        modifier = modifier,
        content = content
    )
}

@PreviewLightDark
@Composable
private fun RoundedEdgeBackgroundPrev() {
    RoundedEdgeBackground(
        modifier = Modifier.padding(all = 16.dp),
        backgroundColor = Color.White,
        borderColor = Color.Black,
        borderWidth = 1.dp,
        cornerRadius = 12.dp,
        content = {
            Text(
                modifier = Modifier.padding(all = 16.dp),
                color = Color.Black,
                text = "This is a trial text"
            )
        }
    )
}