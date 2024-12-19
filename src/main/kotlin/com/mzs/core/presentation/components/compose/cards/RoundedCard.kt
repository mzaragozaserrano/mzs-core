package com.mzs.core.presentation.components.compose.cards

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
import com.mzs.core.presentation.components.compose.backgrounds.RoundedBackground

@Composable
fun RoundedCard(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    cornerRadius: Dp,
    shadowElevation: Dp,
    content: @Composable () -> Unit,
) {
    Surface(
        modifier = modifier,
        shadowElevation = shadowElevation,
        shape = RoundedCornerShape(size = cornerRadius)
    ) {
        RoundedBackground(
            backgroundColor = backgroundColor,
            cornerRadius = cornerRadius
        ) {
            content()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RoundedCardPrev() {
    RoundedCard(
        modifier = Modifier.padding(all = 16.dp),
        backgroundColor = Color.White,
        cornerRadius = 12.dp,
        shadowElevation = 10.dp
    ) {
        Text(
            modifier = Modifier.padding(all = 16.dp),
            color = Color.Black,
            text = "This is a trial text"
        )
    }
}