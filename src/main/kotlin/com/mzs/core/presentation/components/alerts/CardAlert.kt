package com.mzs.core.presentation.components.alerts

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mzs.core.presentation.components.buttons.PushedButton
import com.mzs.core.presentation.compose.components.texts.NormalMediumText
import com.thecocktailapp.core.presentation.compose.components.texts.LargeBoldText

@Composable
fun CardAlert(
    modifier: Modifier = Modifier,
    alertBackgroundColor: Color,
    buttonBackgroundColor: Color,
    buttonTextColor: Color,
    buttonText: String,
    messageTextColor: Color,
    messageText: String,
    titleText: String,
    titleTextColor: Color,
    onButtonClicked: () -> Unit,
) {

    var showDialog by remember { mutableStateOf(value = true) }

    if (showDialog) {
        AlertDialog(
            modifier = modifier,
            confirmButton = {
                PushedButton(
                    modifier = Modifier.fillMaxWidth(),
                    buttonBackgroundColor = buttonBackgroundColor,
                    textColor = buttonTextColor,
                    text = buttonText,
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
                    text = messageText,
                )
            },
            title = {
                LargeBoldText(
                    modifier = Modifier.fillMaxWidth(),
                    color = titleTextColor,
                    text = titleText.uppercase()
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
        buttonText = "Accept",
        messageTextColor = MaterialTheme.colorScheme.onBackground,
        messageText = "This is an alert card",
        titleTextColor = MaterialTheme.colorScheme.errorContainer,
        titleText = "Warning!"
    ) {
        //Here will go the action when clicking on the button
    }
}