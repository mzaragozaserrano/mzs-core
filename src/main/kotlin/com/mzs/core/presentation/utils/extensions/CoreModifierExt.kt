package com.mzs.core.presentation.utils.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Modifier.conditional(
    condition: Boolean,
    modifierElse: @Composable (Modifier.() -> Modifier)? = null,
    modifier: @Composable Modifier.() -> Modifier,
): Modifier {
    return when {
        condition -> {
            then(modifier(Modifier))
        }

        modifierElse != null -> {
            then(modifierElse(Modifier))
        }

        else -> this
    }
}

@Composable
fun <T> Modifier.conditional(
    condition: T?,
    modifierElse: @Composable (Modifier.() -> Modifier)? = null,
    modifier: @Composable Modifier.(T) -> Modifier,
): Modifier {
    return when {
        condition != null -> {
            then(modifier(Modifier, condition))
        }

        modifierElse != null -> {
            then(modifierElse(Modifier))
        }

        else -> {
            this
        }
    }
}