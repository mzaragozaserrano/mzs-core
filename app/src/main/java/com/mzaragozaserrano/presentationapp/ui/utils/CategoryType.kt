package com.mzaragozaserrano.presentationapp.ui.utils

import androidx.annotation.StringRes
import com.mzaragozaserrano.compose.composables.buttons.ButtonImage
import com.mzaragozaserrano.compose.composables.utils.Line
import com.mzaragozaserrano.presentationapp.R
import com.mzaragozaserrano.presentationapp.ui.vo.ComponentVO

sealed class CategoryType(@StringRes val id: Int) {
    object Buttons : CategoryType(R.string.category_buttons)
    object Texts : CategoryType(R.string.category_texts)
    object Utils : CategoryType(R.string.category_utils)
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
        listOf(ComponentVO(nameId = R.string.button_image, item = {
            ButtonImage(
                textId = R.string.button_image_text,
                imageId = R.drawable.ic_launcher_background
            ) {}
        }))
    }

    is CategoryType.Texts -> {
        listOf()
    }

    is CategoryType.Utils -> {
        listOf(ComponentVO(nameId = R.string.line, item = { Line() }))
    }
}