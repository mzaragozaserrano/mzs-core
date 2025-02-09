package com.mzs.core.presentation.components.compose.labels

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mzs.core.R
import com.mzs.core.presentation.components.compose.images.ResourceImage

@Composable
fun WavyLabel(
    modifier: Modifier = Modifier,
    buttonBackgroundColor: Color,
    iconBackgroundColor: Color,
    @DrawableRes iconId: Int,
    iconTint: Color,
    text: String,
    textColor: Color,
    textStyle: TextStyle,
) {
    val imageSize: Dp = 12.dp
    Card(
        modifier = modifier.clip(
            shape = RoundedCornerShape(
                bottomEnd = imageSize,
                topStart = imageSize
            )
        ),
        colors = CardDefaults.cardColors(containerColor = buttonBackgroundColor),
        shape = RoundedCornerShape(
            bottomEnd = imageSize,
            topStart = imageSize
        ),
        content = {
            Row(
                modifier = Modifier.height(intrinsicSize = IntrinsicSize.Max),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = CenterVertically,
                content = {
                    Card(
                        modifier = Modifier.aspectRatio(ratio = 1f),
                        colors = CardDefaults.cardColors(containerColor = iconBackgroundColor),
                        shape = RoundedCornerShape(
                            bottomEnd = imageSize,
                            topEnd = imageSize,
                            topStart = imageSize
                        ),
                        content = {
                            Box(
                                contentAlignment = Center,
                                content = {
                                    ResourceImage(
                                        modifier = Modifier.padding(all = 6.dp),
                                        iconTint = iconTint,
                                        iconId = iconId,
                                        size = imageSize
                                    )
                                }
                            )
                        }
                    )
                    Text(
                        modifier = Modifier.padding(horizontal = 12.dp),
                        color = textColor,
                        fontSize = 14.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        text = text,
                        textAlign = TextAlign.Center,
                        style = textStyle
                    )
                }
            )
        }
    )

}

@PreviewLightDark
@Composable
private fun WavyLabelPrev() {
    WavyLabel(
        buttonBackgroundColor = Color.LightGray,
        iconBackgroundColor = Color.White,
        iconId = R.drawable.core_ic_cloud,
        iconTint = Color.Black,
        text = "Accept",
        textColor = Color.Black,
        textStyle = MaterialTheme.typography.titleSmall
    )
}