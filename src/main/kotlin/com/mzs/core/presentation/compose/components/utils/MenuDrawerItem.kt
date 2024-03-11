package com.mzs.core.presentation.compose.components.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mzs.core.presentation.R
import com.mzs.core.presentation.compose.components.images.ResourceImage
import com.mzs.core.presentation.compose.components.texts.SmallMediumText
import com.mzs.core.presentation.vo.FakeItem
import com.mzs.core.presentation.vo.MenuDrawerItemVO

@Composable
fun <T> MenuDrawerItem(
    iconTint: Color,
    item: MenuDrawerItemVO<T>,
    textColor: Color,
    onClick: (options: T) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(item.drawerOption) }
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(space = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ResourceImage(iconId = item.iconId, iconTint = iconTint, size = 24.dp)
        SmallMediumText(color = textColor, text = stringResource(id = item.titleId))
    }
}

@Preview
@Composable
private fun MenuDrawerItemPrev() {
    MenuDrawerItem(
        iconTint = MaterialTheme.colorScheme.onPrimary,
        item = MenuDrawerItemVO(
            FakeItem.HOME,
            R.drawable.core_ic_menu,
            R.string.core_hello_world
        ),
        textColor = MaterialTheme.colorScheme.onPrimary
    ) {
        //Here will go the action when clicking on the button
    }
}