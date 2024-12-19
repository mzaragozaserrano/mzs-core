package com.mzs.core.presentation.components.cards

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mzs.core.presentation.components.backgrounds.RoundedEdgeBackground
import com.thecocktailapp.core.presentation.compose.components.texts.NormalText

@Composable
fun RoundedEdgeCard(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.White,
    borderColor: Color,
    cornerRadius: Dp = 12.dp,
    shadowElevation: Dp = 10.dp,
    strokeBorder: Dp,
    content: @Composable () -> Unit,
) {
    Surface(
        modifier = modifier,
        shadowElevation = shadowElevation,
        shape = RoundedCornerShape(size = cornerRadius)
    ) {
        RoundedEdgeBackground(
            backgroundColor = backgroundColor,
            borderColor = borderColor,
            cornerRadius = cornerRadius,
            strokeBorder = strokeBorder,
        ) {
            content()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RoundedCardPrev() {
    RoundedCard(modifier = Modifier.padding(all = 16.dp)) {
        NormalText(
            modifier = Modifier.padding(all = 16.dp),
            text = "This is a trial text"
        )
    }
}