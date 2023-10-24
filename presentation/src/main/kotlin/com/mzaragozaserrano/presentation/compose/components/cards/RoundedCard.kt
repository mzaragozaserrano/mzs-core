package com.mzaragozaserrano.presentation.compose.components.cards

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mzaragozaserrano.presentation.R
import com.mzaragozaserrano.presentation.compose.components.backgrounds.RoundedBackground

@Composable
fun RoundedCard(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.White,
    cornerRadius: Dp = 12.dp,
    shadowElevation: Dp = 10.dp,
    content: @Composable () -> Unit,
) {
    Surface(
        modifier = modifier,
        shadowElevation = shadowElevation,
        shape = RoundedCornerShape(cornerRadius)
    ) {
        RoundedBackground(
            backgroundColor = backgroundColor,
            cornerRadius = cornerRadius
        ) {
            content()
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun RoundedCardPrev() {
    RoundedCard(modifier = Modifier.padding(all = 16.dp)) {
        Text(modifier = Modifier.padding(all = 16.dp), text = stringResource(id = R.string.hello_world))
    }
}