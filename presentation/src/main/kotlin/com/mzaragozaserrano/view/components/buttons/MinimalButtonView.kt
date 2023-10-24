package com.mzaragozaserrano.view.components.buttons

import android.content.Context
import android.util.AttributeSet
import androidx.compose.runtime.Composable
import com.mzaragozaserrano.compose.components.buttons.MinimalButton
import com.mzaragozaserrano.view.utils.BaseComposeView
import com.mzaragozaserrano.view.vo.MinimalButtonVO

class MinimalButtonView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : BaseComposeView<MinimalButtonVO>(
    context = context,
    attrs = attrs,
    defStyleAttr = defStyleAttr
) {
    override var content: @Composable () -> Unit = {
        with(item) {
            MinimalButton(
                iconId = iconId,
                iconTint = iconTint,
                textColor = textColor,
                textId = textId
            ) {
                onButtonClicked()
            }
        }
    }
}