package com.mzaragozaserrano.compose.buttons

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mzaragozaserrano.presentation.R

@Composable
fun ButtonImage(
    modifier: Modifier = Modifier,
    @StringRes textId: Int,
    @DrawableRes imageId: Int,
    cornerRadius: Dp = 12.dp,
    height: Dp = 100.dp,
    imageSize: Dp = 48.dp,
    onButtonClicked: () -> Unit,
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(cornerRadius),
        modifier = modifier
            .height(height)
            .fillMaxWidth()
            .clip(RoundedCornerShape(cornerRadius))
            .clickable { onButtonClicked() }
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp, CenterVertically),
            horizontalAlignment = CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = stringResource(textId))
            Image(
                modifier = Modifier
                    .width(imageSize)
                    .height(imageSize),
                painter = painterResource(id = imageId),
                contentDescription = null
            )
        }
    }
}

@Preview
@Composable
fun ButtonImageTextPrev() {
    ButtonImage(textId = R.string.button_text_message, imageId = R.drawable.ic_cloud) {
        //TODO("Here the code that is intended to run after the button click will be implemented.")
    }
}