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
import androidx.compose.ui.unit.dp
import com.mzs.core.presentation.components.compose.utils.Adapter
import com.mzs.core.presentation.utils.generic.ItemOrientation
import kotlinx.coroutines.launch

@Composable
fun MenuDrawerContent(
    modifier: Modifier = Modifier,
    modifierScreen: Modifier = Modifier,
    date: String,
    dateTextColor: Color,
    drawerState: DrawerState,
    greetingTextColor: Color,
    @StringRes greetingTextId: Int,
    iconTint: Color,
    initScreen: Pair<Int, Int>,
    screens: List<Pair<Int, Int>>,
    testTag: String = "",
    textColor: Color,
    onClick: (Pair<Int, Int>) -> Unit,
) {

    var currentScreen by remember { mutableStateOf(initScreen) }
    val coroutineScope = rememberCoroutineScope()

    ModalDrawerSheet(modifier = modifier) {
        Surface(color = MaterialTheme.colorScheme.background) {
            Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.Start) {
                Text(
                    modifier = Modifier.padding(end = 16.dp, start = 16.dp, top = 16.dp),
                    color = greetingTextColor,
                    text = stringResource(id = greetingTextId)
                )
                Text(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    color = dateTextColor,
                    text = date
                )
                Adapter(
                    modifier = Modifier.padding(top = 16.dp),
                    contentPadding = 16.dp,
                    isScrollable = false,
                    itemOrientation = ItemOrientation.Vertical,
                    list = screens
                ) { _, item ->
                    MenuDrawerItem(
                        modifier = modifierScreen,
                        iconId = item.first,
                        iconTint = iconTint,
                        testTag = testTag,
                        textColor = textColor,
                        titleId = item.second
                    ) {
                        if (currentScreen != item || currentScreen == initScreen) {
                            currentScreen = item
                            onClick(currentScreen)
                        }
                        coroutineScope.launch {
                            drawerState.close()
                        }
                    }
                }
            }
        }
    }

}