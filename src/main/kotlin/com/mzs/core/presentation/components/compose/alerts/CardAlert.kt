package com.mzs.core.presentation.components.compose.alerts

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.mzs.core.presentation.components.compose.buttons.PushedButton

@Composable
fun CardAlert(
    modifier: Modifier = Modifier,
    alertBackgroundColor: Color,
    buttonBackgroundColor: Color,
    buttonTextColor: Color,
    buttonText: String,
    messageStyle: TextStyle,
    messageTextColor: Color,
    messageText: String,
    titleStyle: TextStyle,
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
                    textStyle = MaterialTheme.typography.titleSmall
                ) {
                    showDialog = false
                    onButtonClicked()
                }
            },
            containerColor = alertBackgroundColor,
            text = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    color = messageTextColor,
                    maxLines = 5,
                    style = messageStyle,
                    text = messageText
                )
            },
            title = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    color = titleTextColor,
                    style = titleStyle,
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
        messageStyle = MaterialTheme.typography.bodyMedium,
        messageTextColor = MaterialTheme.colorScheme.onBackground,
        messageText = "This is an alert card",
        titleStyle = MaterialTheme.typography.titleMedium,
        titleTextColor = MaterialTheme.colorScheme.errorContainer,
        titleText = "Warning!"
    ) {
        //Here will go the action when clicking on the card
    }
}