package com.mzaragozaserrano.presentation.compose.components.backgrounds

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
import com.mzaragozaserrano.presentation.R
import com.mzaragozaserrano.presentation.compose.components.texts.NormalText

@Composable
fun RoundedBackground(
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 12.dp,
    backgroundColor: Color = Color.White,
    content: @Composable () -> Unit,
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        shape = RoundedCornerShape(size = cornerRadius)
    ) {
        content()
    }
}

@Preview
@Composable
private fun RoundedBackgroundPrev() {
    RoundedBackground(modifier = Modifier.padding(all = 16.dp)) {
        NormalText(
            modifier = Modifier.padding(all = 16.dp),
            color = Color.Black,
            text = stringResource(id = R.string.hello_world)
        )
    }
}