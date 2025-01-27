package com.mzs.core.presentation.components.compose.alerts

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.mzs.core.presentation.components.compose.buttons.PushedButton

@Composable
fun CardAlert(
    modifier: Modifier = Modifier,
    alertBackgroundColor: Color,
    buttonBackgroundColor: Color,
    buttonText: String,
    buttonTextColor: Color,
    durationMillisBlockingButton: Int? = null,
    messageStyle: TextStyle,
    messageText: String,
    messageTextColor: Color,
    titleStyle: TextStyle,
    titleText: String,
    titleTextColor: Color,
    onButtonClicked: () -> Unit,
    onDismissRequest: (() -> Unit)? = null
) {
    AlertDialog(
        modifier = modifier,
        confirmButton = {
            PushedButton(
                modifier = Modifier.fillMaxWidth(),
                buttonBackgroundColor = buttonBackgroundColor,
                durationMillisBlockingButton = durationMillisBlockingButton,
                text = buttonText,
                textColor = buttonTextColor,
                textStyle = MaterialTheme.typography.titleSmall,
                onButtonClicked = onButtonClicked
            )
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
        onDismissRequest = {
            onDismissRequest?.invoke()
        }
    )
}

@PreviewLightDark
@Composable
private fun CardAlertPrev() {
    CardAlert(
        alertBackgroundColor = MaterialTheme.colorScheme.background,
        buttonBackgroundColor = MaterialTheme.colorScheme.errorContainer,
        buttonText = "Accept",
        buttonTextColor = MaterialTheme.colorScheme.background,
        durationMillisBlockingButton = 3000,
        messageStyle = MaterialTheme.typography.bodyMedium,
        messageText = "This is an alert card",
        messageTextColor = MaterialTheme.colorScheme.onBackground,
        titleStyle = MaterialTheme.typography.titleMedium,
        titleText = "Warning!",
        titleTextColor = MaterialTheme.colorScheme.errorContainer,
        onButtonClicked = { /*Here will go the action when clicking on the card*/ }
    )
}