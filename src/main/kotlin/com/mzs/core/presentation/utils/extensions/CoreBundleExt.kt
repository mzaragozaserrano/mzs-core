package com.mzs.core.presentation.utils.extensions

import android.app.Activity
import android.os.Build
import java.io.Serializable

fun <T : Serializable> Activity.getSerializableExtra(key: String, clazz: Class<T>): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        intent.getSerializableExtra(key, clazz)
    } else {
        @Suppress("DEPRECATION", "UNCHECKED_CAST")
        intent.getSerializableExtra(key) as? T
    }
}