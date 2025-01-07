package com.mzs.core.presentation.components.compose.utils

import androidx.compose.runtime.Composable
import java.io.Serializable

@Composable
fun <T> SkeletonableItem(
    skeletonable: Skeletonable<T>,
    contentSkeleton: @Composable () -> Unit,
    content: @Composable (T) -> Unit,
) {
    if (skeletonable.data != null) {
        content(skeletonable.data)
    } else {
        contentSkeleton()
    }
}

data class Skeletonable<T>(val data: T? = null) : Serializable

fun <T> Skeletonable<T>.hasData() = this.data != null

fun <T> T.toSkeletonable(): Skeletonable<T> = Skeletonable(this)