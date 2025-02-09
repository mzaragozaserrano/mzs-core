package com.mzs.core.presentation.components.view

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mzs.core.presentation.components.compose.utils.Adapter
import com.mzs.core.presentation.utils.generic.ItemOrientation
import com.mzs.core.presentation.utils.generic.emptyText
import com.mzs.core.presentation.vo.MenuItemVO
import kotlinx.coroutines.launch

@Composable
fun MenuDrawerContent(
    modifier: Modifier = Modifier,
    modifierScreen: Modifier = Modifier,
    date: String,
    dateStyle: TextStyle,
    dateTextColor: Color,
    drawerState: DrawerState,
    greetingStyle: TextStyle,
    greetingTextColor: Color,
    @StringRes greetingTextId: Int,
    iconSize: Dp,
    iconTint: Color,
    initScreen: MenuItemVO,
    screens: List<MenuItemVO>,
    testTag: String = emptyText,
    textColor: Color,
    titleStyle: TextStyle,
    onMenuItemClicked: (MenuItemVO) -> Unit,
) {

    var currentScreen by remember { mutableStateOf(value = initScreen) }
    val coroutineScope = rememberCoroutineScope()

    ModalDrawerSheet(modifier = modifier) {
        Surface(color = MaterialTheme.colorScheme.background) {
            Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.Start) {
                Text(
                    modifier = Modifier.padding(end = 16.dp, start = 16.dp, top = 16.dp),
                    color = greetingTextColor,
                    style = greetingStyle,
                    text = stringResource(id = greetingTextId)
                )
                Text(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    color = dateTextColor,
                    style = dateStyle,
                    text = date
                )
                Adapter(
                    modifier = Modifier.padding(top = 16.dp),
                    contentPadding = 16.dp,
                    isScrollable = false,
                    itemOrientation = ItemOrientation.Vertical,
                    item = { _, item ->
                        MenuDrawerItem(
                            modifier = modifierScreen,
                            iconId = item.iconId,
                            iconSize = iconSize,
                            iconTint = iconTint,
                            testTag = testTag,
                            textColor = textColor,
                            title = stringResource(id = item.titleId),
                            titleStyle = titleStyle,
                            onItemClicked = {
                                if (currentScreen != item || currentScreen == initScreen) {
                                    currentScreen = item
                                    onMenuItemClicked(currentScreen)
                                }
                                coroutineScope.launch {
                                    drawerState.close()
                                }
                            }
                        )
                    },
                    items = screens
                )
            }
        }
    }

}