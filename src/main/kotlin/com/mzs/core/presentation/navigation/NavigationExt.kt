package com.mzs.core.presentation.navigation

import android.net.Uri
import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.reflect.KType
import kotlin.reflect.typeOf

inline fun <reified S : Any> NavGraphBuilder.screenNavigation(crossinline screen: @Composable () -> Unit) {
    composable<S> { screen() }
}

inline fun <reified S : Any, reified T : Any> NavGraphBuilder.screenNavigationWithParameters(
    crossinline screen: @Composable (S) -> Unit,
) {
    composable<S>(createTypeMap<T>()) { backStackEntry ->
        screen(backStackEntry.toRoute<S>())
    }
}

interface ParameterScreen<T : Any> {
    val data: T
}

inline fun <reified T : Any> createTypeMap(): Map<KType, NavType<T>> {
    return mapOf(typeOf<T>() to serializableType<T>())
}

inline fun <reified T : Any> serializableType(isNullableAllowed: Boolean = false) =
    object : NavType<T>(isNullableAllowed = isNullableAllowed) {
        override fun get(bundle: Bundle, key: String) =
            bundle.getString(key)?.let<String, T>(Json::decodeFromString)

        override fun parseValue(value: String): T = Json.decodeFromString(Uri.decode(value))

        override fun serializeAsValue(value: T): String = Uri.encode(Json.encodeToString(value))

        override fun put(bundle: Bundle, key: String, value: T) {
            bundle.putString(key, Json.encodeToString(value))
        }
    }

inline fun <reified T : Any, reified S : ParameterScreen<T>> from(savedStateHandle: SavedStateHandle): S {
    val typeMap = createTypeMap<T>()
    return savedStateHandle.toRoute<S>(typeMap)
}
