package com.mzs.core.presentation.components.compose.cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mzs.core.presentation.components.compose.backgrounds.RoundedBackground

@Composable
fun RoundedCard(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    cornerRadius: Dp,
    shadowElevation: Dp,
    onCardClicked: () -> Unit,
    content: @Composable ColumnScope.() -> Unit,
) {
    Surface(
        modifier = modifier
            .clip(shape = RoundedCornerShape(size = cornerRadius))
            .clickable { onCardClicked() },
        shadowElevation = shadowElevation,
        shape = RoundedCornerShape(size = cornerRadius),
        content = {
            RoundedBackground(
                backgroundColor = backgroundColor,
                cornerRadius = cornerRadius,
                content = content
            )
        }
    )
}

@PreviewLightDark
@Composable
private fun RoundedCardPrev() {
    RoundedCard(
        modifier = Modifier.padding(all = 16.dp),
        backgroundColor = Color.White,
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