package com.mzaragozaserrano.presentation.view.components.buttons

import android.content.Context
import android.util.AttributeSet
import androidx.compose.runtime.Composable
import com.mzaragozaserrano.presentation.compose.components.buttons.MinimalButton
import com.mzaragozaserrano.presentation.view.base.BaseComposeView
import com.mzaragozaserrano.presentation.view.vo.MinimalButtonVO

class MinimalButtonView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : BaseComposeView<MinimalButtonVO>(
    context = context,
    attrs = attrs,
    defStyleAttr = defStyleAttr
) {

    private var onButtonClicked: () -> Unit = {}

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

    fun setOnButtonClicked(onButtonClicked: () -> Unit) {
        this.onButtonClicked = onButtonClicked
    }
    
}