package com.mzaragozaserrano.compose.composables.backgrounds

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mzaragozaserrano.compose.composables.texts.NormalText
import com.mzaragozaserrano.presentation.R

@Composable
fun RoundedEdgeBackground(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.White,
    borderColor: Color = Color.Black,
    cornerRadius: Dp = 12.dp,
    strokeBorder: Dp = 1.dp,
    content: @Composable () -> Unit,
) {
    Card(
        shape = RoundedCornerShape(size = cornerRadius),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        border = BorderStroke(color = borderColor, width = strokeBorder),
        modifier = modifier
    ) {
        content()
    }
}

@Preview
@Composable
private fun RoundedEdgeBackgroundPrev() {
    RoundedEdgeBackground(modifier = Modifier.padding(all = 16.dp)) {
        NormalText(
            modifier = Modifier.padding(all = 16.dp),
            color = Color.Black,
            text = stringResource(id = R.string.hello_world)
        )
    }
}