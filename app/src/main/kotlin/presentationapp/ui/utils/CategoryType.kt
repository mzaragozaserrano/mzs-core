package presentationapp.ui.utils

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mzaragozaserrano.app.R
import com.mzaragozaserrano.presentation.compose.components.buttons.PushedButton
import com.mzaragozaserrano.presentation.compose.components.labels.WavyLabel
import com.mzaragozaserrano.presentation.compose.components.texts.ExtraLargeMediumText
import com.mzaragozaserrano.presentation.compose.components.texts.LargeBoldText
import com.mzaragozaserrano.presentation.compose.components.texts.NormalText
import com.mzaragozaserrano.presentation.compose.components.texts.SmallText
import com.mzaragozaserrano.presentation.compose.components.utils.Line
import presentationapp.ui.vo.ComponentVO

sealed class CategoryType(@StringRes val textId: Int, @DrawableRes val imageId: Int) {
    object Buttons : CategoryType(
        textId = R.string.category_buttons,
        imageId = R.drawable.ic_category_buttons
    )

    object Labels : CategoryType(
        textId = R.string.category_labels,
        imageId = R.drawable.ic_category_labels
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
        is CategoryType.Labels -> R.string.category_labels
        is CategoryType.Texts -> R.string.category_texts
        is CategoryType.Utils -> R.string.category_utils
    }
}

fun Int.getSerializableCategory(): CategoryType {
    return when (this) {
        R.string.category_buttons -> CategoryType.Buttons
        R.string.category_labels -> CategoryType.Labels
        R.string.category_texts -> CategoryType.Texts
        R.string.category_utils -> CategoryType.Utils
        else -> throw IllegalArgumentException("Invalid data string: $this")
    }
}

fun createCategoryList(): List<CategoryType> = listOf(
    CategoryType.Buttons,
    CategoryType.Labels,
    CategoryType.Texts,
    CategoryType.Utils
)

fun CategoryType.createComponentList(): List<ComponentVO> = when (this) {
    is CategoryType.Buttons -> {
        listOf(
            ComponentVO(
                nameId = R.string.pushed_button,
                listItems = listOf {
                    PushedButton(
                        buttonBackgroundColor = MaterialTheme.colorScheme.primary,
                        textColor = MaterialTheme.colorScheme.onPrimary,
                        textId = R.string.button_text
                    ) {}
                }
            )
        )
    }

    is CategoryType.Labels -> {
        listOf(
            ComponentVO(
                nameId = R.string.wavy_label,
                listItems = listOf {
                    WavyLabel(
                        modifier = Modifier.fillMaxWidth(),
                        buttonBackgroundColor = MaterialTheme.colorScheme.background,
                        iconBackgroundColor = MaterialTheme.colorScheme.primary,
                        iconId = R.drawable.ic_category_buttons,
                        iconTint = MaterialTheme.colorScheme.onPrimary,
                        textColor = MaterialTheme.colorScheme.onBackground,
                        textId = R.string.label_text
                    )
                }
            )
        )
    }

    is CategoryType.Texts -> {
        listOf(
            ComponentVO(
                nameId = R.string.extra_large_medium_text,
                listItems = listOf { ExtraLargeMediumText(text = stringResource(id = com.mzaragozaserrano.presentation.R.string.core_hello_world)) }),
            ComponentVO(
                nameId = R.string.large_bold_text,
                listItems = listOf { LargeBoldText(text = stringResource(id = com.mzaragozaserrano.presentation.R.string.core_hello_world)) }),
            ComponentVO(
                nameId = R.string.normal_text,
                listItems = listOf { NormalText(text = stringResource(id = com.mzaragozaserrano.presentation.R.string.core_hello_world)) }),
            ComponentVO(
                nameId = R.string.small_text,
                listItems = listOf { SmallText(text = stringResource(id = com.mzaragozaserrano.presentation.R.string.core_hello_world)) })
        )
    }

    is CategoryType.Utils -> {
        listOf(
            ComponentVO(
                nameId = R.string.line,
                listItems = listOf {
                    Line(
                        modifier = Modifier.padding(horizontal = 12.dp),
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        height = 1.dp
                    )
                }
            )
        )
    }
}