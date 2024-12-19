package com.mzs.core.presentation.components.labels

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mzs.core.R
import com.mzs.core.presentation.components.images.ResourceImage

@Composable
fun WavyLabel(
    modifier: Modifier = Modifier,
    buttonBackgroundColor: Color,
    iconBackgroundColor: Color,
    @DrawableRes iconId: Int,
    iconTint: Color,
    text: String,
    textColor: Color,
) {
    val imageSize: Dp = 12.dp
    Card(
        modifier = modifier.clip(RoundedCornerShape(bottomEnd = imageSize, topStart = imageSize)),
        colors = CardDefaults.cardColors(containerColor = buttonBackgroundColor),
        shape = RoundedCornerShape(
            bottomEnd = imageSize,
            topStart = imageSize
        )
    ) {
        Row(
            modifier = Modifier.height(intrinsicSize = IntrinsicSize.Max),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = CenterVertically
        ) {
            Card(
                modifier = Modifier.aspectRatio(ratio = 1f),
                colors = CardDefaults.cardColors(containerColor = iconBackgroundColor),
                shape = RoundedCornerShape(
                    bottomEnd = imageSize,
                    topEnd = imageSize,
                    topStart = imageSize
                )
            ) {
                Box(contentAlignment = Center) {
                    ResourceImage(
                        modifier = Modifier.padding(all = 6.dp),
                        iconTint = iconTint,
                        iconId = iconId,
                        size = imageSize
                    )
                }
            }
            Text(
                modifier = Modifier.padding(horizontal = 12.dp),
                color = textColor,
                text = text,
                textAlign = TextAlign.Center
            )
        }
    }

}

@Preview
@Composable
private fun WavyLabelPrev() {
    WavyLabel(
        buttonBackgroundColor = Color.LightGray,
        iconBackgroundColor = Color.White,
        iconId = R.drawable.core_ic_cloud,
        iconTint = Color.Black,
        textColor = Color.Black,
        text = "Accept"
    )
}