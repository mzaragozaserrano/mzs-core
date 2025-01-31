package com.mzs.core.presentation.utils.extensions

import java.util.Locale

fun String.capitalize(locale: Locale = Locale.getDefault()): String =
    this.replaceFirstChar {
        if (it.isLowerCase())
            it.titlecase(locale)
        else
            it.toString()
    }