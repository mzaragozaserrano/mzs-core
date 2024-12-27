package com.mzs.core.presentation.components.compose.cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mzs.core.presentation.components.compose.backgrounds.RoundedEdgeBackground

@Composable
fun RoundedEdgeCard(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    borderColor: Color,
    borderWidth: Dp,
    cornerRadius: Dp,
    shadowElevation: Dp,
    onCardClicked: () -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    Surface(
        modifier = modifier.clickable { onCardClicked() },
        shadowElevation = shadowElevation,
        shape = RoundedCornerShape(size = cornerRadius),
        content = {
            RoundedEdgeBackground(
                backgroundColor = backgroundColor,
                borderColor = borderColor,
                borderWidth = borderWidth,
                cornerRadius = cornerRadius,
                content = content
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun RoundedCardPrev() {
    RoundedEdgeCard(
        modifier = Modifier.padding(all = 16.dp),
        backgroundColor = Color.White,
        borderColor = Color.LightGray,
        borderWidth = 1.dp,
        cornerRadius = 12.dp,
        shadowElevation = 10.dp,
        onCardClicked = { /*Here will go the action when clicking on the card*/ },
        content = {
            Text(
                modifier = Modifier.padding(all = 16.dp),
                color = Color.Black,
                text = "This is a trial text"
            )
        }
    )
}