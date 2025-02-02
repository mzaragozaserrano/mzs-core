package com.mzs.core.presentation.utils.extensions

import android.app.Activity
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import java.io.Serializable

fun <T : Serializable> Activity.getSerializableExtra(key: String, clazz: Class<T>): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        intent.getSerializableExtra(key, clazz)
    } else {
        @Suppress("DEPRECATION", "UNCHECKED_CAST")
        intent.getSerializableExtra(key) as? T
    }
}

fun <T : Serializable> Fragment.getSerializableArgument(key: String, clazz: Class<T>): T? {
    return arguments?.let {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            it.getSerializable(key, clazz)
        } else {
            @Suppress("DEPRECATION", "UNCHECKED_CAST")
            it.getSerializable(key) as? T
        }
    }
}

fun <T : Serializable> Bundle.getSerializableBundle(key: String, clazz: Class<T>): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getSerializable(key, clazz)
    } else {
        @Suppress("DEPRECATION", "UNCHECKED_CAST")
        getSerializable(key) as? T
    }
}