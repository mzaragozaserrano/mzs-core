package com.mzs.core.presentation.components.view

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mzs.core.presentation.components.compose.images.ResourceImage
import com.mzs.core.presentation.utils.extensions.conditional

@Composable
fun MenuDrawerItem(
    modifier: Modifier = Modifier,
    @DrawableRes iconId: Int,
    iconTint: Color,
    testTag: String? = null,
    textColor: Color,
    @StringRes titleId: Int,
    onItemClicked: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onItemClicked() }
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .conditional(condition = testTag) { tag -> testTag(tag = tag) },
        horizontalArrangement = Arrangement.spacedBy(space = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ResourceImage(iconId = iconId, iconTint = iconTint, size = 24.dp)
        Text(color = textColor, text = stringResource(id = titleId))
    }
}

/*
@Preview
@Composable
private fun MenuDrawerItemPrev() {
    MenuDrawerItem(
        iconId = R.drawable.core_ic_warning,
        iconTint = MaterialTheme.colorScheme.onPrimary,
        textColor = MaterialTheme.colorScheme.onPrimary,
        titleId =
    ) {
        //Here will go the action when clicking on the button
    }
}*/
