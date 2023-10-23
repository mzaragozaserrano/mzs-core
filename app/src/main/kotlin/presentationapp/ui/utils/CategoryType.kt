package presentationapp.ui.utils

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material3.MaterialTheme
import com.mzaragozaserrano.compose.composables.buttons.PushedButton
import com.mzaragozaserrano.compose.composables.buttons.WavyButton
import com.mzaragozaserrano.compose.composables.utils.Line
import com.mzaragozaserrano.presentationapp.R
import presentationapp.ui.vo.ComponentVO

sealed class CategoryType(@StringRes val textId: Int, @DrawableRes val imageId: Int) {
    object Buttons : CategoryType(
        textId = R.string.category_buttons,
        imageId = R.drawable.ic_category_buttons
    )

    object Texts : CategoryType(
        textId = R.string.category_texts,
        imageId = R.drawable.ic_category_texts
    )

    object Utils : CategoryType(
        textId = R.string.category_utils,
        imageId = R.drawable.ic_category_utils
    )
}

fun CategoryType.serializableCategory(): Int {
    return when (this) {
        is CategoryType.Buttons -> R.string.category_buttons
        is CategoryType.Texts -> R.string.category_texts
        is CategoryType.Utils -> R.string.category_utils
    }
}

fun Int.getSerializableCategory(): CategoryType {
    return when (this) {
        R.string.category_buttons -> CategoryType.Buttons
        R.string.category_texts -> CategoryType.Texts
        R.string.category_utils -> CategoryType.Utils
        else -> throw IllegalArgumentException("Invalid data string: $this")
    }
}

fun createCategoryList(): List<CategoryType> = listOf(
    CategoryType.Buttons,
    CategoryType.Texts,
    CategoryType.Utils
)

fun CategoryType.createComponentList(): List<ComponentVO> = when (this) {
    is CategoryType.Buttons -> {
        listOf(
            ComponentVO(
                nameId = R.string.wavy_button,
                listItems = listOf(
                    {
                        WavyButton(
                            buttonBackgroundColor = MaterialTheme.colorScheme.secondaryContainer,
                            iconId = R.drawable.ic_category_buttons,
                            iconTint = MaterialTheme.colorScheme.onSecondaryContainer,
                            isAnimationEnabled = true,
                            textColor = MaterialTheme.colorScheme.onSecondaryContainer,
                            textId = R.string.button_text
                        ) {}
                    },
                    {
                        WavyButton(
                            buttonBackgroundColor = MaterialTheme.colorScheme.secondaryContainer,
                            isAnimationEnabled = true,
                            textColor = MaterialTheme.colorScheme.onSecondaryContainer,
                            textId = R.string.button_text
                        ) {}
                    },
                    {
                        WavyButton(
                            buttonBackgroundColor = MaterialTheme.colorScheme.secondaryContainer,
                            iconId = R.drawable.ic_category_buttons,
                            iconTint = MaterialTheme.colorScheme.onSecondaryContainer,
                            textColor = MaterialTheme.colorScheme.onSecondaryContainer,
                            textId = R.string.button_text
                        ) {}
                    },
                    {
                        WavyButton(
                            buttonBackgroundColor = MaterialTheme.colorScheme.secondaryContainer,
                            textColor = MaterialTheme.colorScheme.onSecondaryContainer,
                            textId = R.string.button_text
                        ) {}
                    }
                )
            ),
            ComponentVO(
                nameId = R.string.pushed_button,
                listItems = listOf(
                    {
                        PushedButton(
                            buttonBackgroundColor = MaterialTheme.colorScheme.secondaryContainer,
                            iconBackgroundColor = MaterialTheme.colorScheme.onSecondaryContainer,
                            iconId = R.drawable.ic_category_buttons,
                            iconTint = MaterialTheme.colorScheme.onSecondaryContainer,
                            isAnimationEnabled = true,
                            textColor = MaterialTheme.colorScheme.onSecondaryContainer,
                            textId = R.string.button_text
                        ) {}
                    },
                    {
                        PushedButton(
                            buttonBackgroundColor = MaterialTheme.colorScheme.secondaryContainer,
                            isAnimationEnabled = true,
                            textColor = MaterialTheme.colorScheme.onSecondaryContainer,
                            textId = R.string.button_text
                        ) {}
                    },
                    {
                        PushedButton(
                            buttonBackgroundColor = MaterialTheme.colorScheme.secondaryContainer,
                            iconBackgroundColor = MaterialTheme.colorScheme.onSecondaryContainer,
                            iconId = R.drawable.ic_category_buttons,
                            iconTint = MaterialTheme.colorScheme.onSecondaryContainer,
                            textColor = MaterialTheme.colorScheme.onSecondaryContainer,
                            textId = R.string.button_text
                        ) {}
                    },
                    {
                        PushedButton(
                            buttonBackgroundColor = MaterialTheme.colorScheme.secondaryContainer,
                            textColor = MaterialTheme.colorScheme.onSecondaryContainer,
                            textId = R.string.button_text
                        ) {}
                    }
                )
            )
        )
    }

    is CategoryType.Texts -> {
        listOf()
    }

    is CategoryType.Utils -> {
        listOf(ComponentVO(nameId = R.string.line, listItems = listOf { Line() }))
    }
}