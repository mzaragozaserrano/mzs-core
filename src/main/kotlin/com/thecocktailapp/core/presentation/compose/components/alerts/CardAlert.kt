package com.thecocktailapp.core.presentation.compose.components.alerts

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thecocktailapp.core.R
import com.thecocktailapp.core.presentation.compose.components.buttons.PushedButton
import com.thecocktailapp.core.presentation.compose.components.texts.LargeBoldText
import com.thecocktailapp.core.presentation.compose.components.texts.NormalMediumText

@Composable
fun CardAlert(
    alertBackgroundColor: Color,
    buttonBackgroundColor: Color,
    buttonTextColor: Color,
    @StringRes buttonTextId: Int,
    messageTextColor: Color,
    @StringRes messageTextId: Int,
    titleTextColor: Color,
    @StringRes titleTextId: Int,
    onButtonClicked: () -> Unit,
) {

    var showDialog by remember { mutableStateOf(value = true) }

    if (showDialog) {
        AlertDialog(
            confirmButton = {
                PushedButton(
                    modifier = Modifier.fillMaxWidth(),
                    buttonBackgroundColor = buttonBackgroundColor,
                    textColor = buttonTextColor,
                    textId = buttonTextId,
                    textPaddingVertical = 12.dp
                ) {
                    showDialog = false
                    onButtonClicked()
                }
            },
            containerColor = alertBackgroundColor,
            text = {
                NormalMediumText(
                    modifier = Modifier.fillMaxWidth(),
                    color = messageTextColor,
                    maxLines = 5,
                    text = stringResource(id = messageTextId),
                )
            },
            title = {
                LargeBoldText(
                    modifier = Modifier.fillMaxWidth(),
                    color = titleTextColor,
                    text = stringResource(id = titleTextId).uppercase()
                )
            },
            onDismissRequest = { }
        )
    }

}

@Preview
@Composable
private fun CardAlertPrev() {
    CardAlert(
        alertBackgroundColor = MaterialTheme.colorScheme.background,
        buttonBackgroundColor = MaterialTheme.colorScheme.errorContainer,
        buttonTextColor = MaterialTheme.colorScheme.background,
        buttonTextId = R.string.core_button_text_message,
        messageTextColor = MaterialTheme.colorScheme.onBackground,
        messageTextId = R.string.core_message_alert_error,
        titleTextColor = MaterialTheme.colorScheme.errorContainer,
        titleTextId = R.string.core_title_alert_error
    ) {
        //Here will go the action when clicking on the button
    }
}