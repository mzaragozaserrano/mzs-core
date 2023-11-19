package com.mzaragozaserrano.presentation.compose.components.alerts

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mzaragozaserrano.presentation.R
import com.mzaragozaserrano.presentation.compose.components.buttons.PushedButton
import com.mzaragozaserrano.presentation.compose.components.texts.LargeBoldText
import com.mzaragozaserrano.presentation.compose.components.texts.NormalMediumText

@Composable
fun ErrorAlert(
    alertBackgroundColor: Color,
    buttonBackgroundColor: Color,
    buttonTextColor: Color,
    @StringRes buttonTextId: Int,
    messageTextColor: Color,
    @StringRes messageTextId: Int,
    titleTextColor: Color,
    @StringRes titleTextId: Int,
    onRetryButtonClicked: () -> Unit,
) {

    AlertDialog(
        confirmButton = {
            PushedButton(
                buttonBackgroundColor = buttonBackgroundColor,
                textColor = buttonTextColor,
                textId = buttonTextId
            ) {
                onRetryButtonClicked()
            }
        },
        containerColor = alertBackgroundColor,
        text = {
            NormalMediumText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                color = messageTextColor,
                maxLines = 5,
                text = stringResource(id = messageTextId),
            )
        },
        title = {
            LargeBoldText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                color = titleTextColor,
                text = stringResource(id = titleTextId).uppercase()
            )
        },
        onDismissRequest = { /*TODO*/ }
    )

}

@Preview
@Composable
private fun ErrorAlertPrev() {
    ErrorAlert(
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