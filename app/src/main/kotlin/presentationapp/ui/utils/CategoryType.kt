package presentationapp.ui.utils

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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
                item = {
                    WavyButton(
                        modifier = Modifier.padding(all = 8.dp),
                        buttonBackgroundColor = Color(0xFFAEDFF7),
                        iconId = R.drawable.ic_category_buttons,
                        isAnimationEnabled = true,
                        textId = R.string.button_text
                    ) {}
                }
            )
        )
    }

    is CategoryType.Texts -> {
        listOf()
    }

    is CategoryType.Utils -> {
        listOf(ComponentVO(nameId = R.string.line, item = { Line() }))
    }
}