package com.mzs.core.presentation.navigation

import android.net.Uri
import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.reflect.KType
import kotlin.reflect.typeOf

inline fun <reified Screen : Any> NavGraphBuilder.screenNavigation(crossinline screen: @Composable (NavBackStackEntry) -> Unit) {
    composable<Screen> { entry -> screen(entry) }
}

inline fun <reified Screen : Any, reified Type : Any> NavGraphBuilder.screenNavigationWithParameters(
    crossinline screen: @Composable (Screen) -> Unit,
) {
    composable<Screen>(createTypeMap<Type>()) { backStackEntry ->
        screen(backStackEntry.toRoute<Screen>())
    }
}

interface ParameterScreen<Type : Any> {
    val data: Type
}

inline fun <reified Type : Any> createTypeMap(): Map<KType, NavType<Type>> {
    return mapOf(typeOf<Type>() to serializableType<Type>())
}

inline fun <reified Type : Any> serializableType(isNullableAllowed: Boolean = false) =
    object : NavType<Type>(isNullableAllowed = isNullableAllowed) {
        override fun get(bundle: Bundle, key: String) =
            bundle.getString(key)?.let<String, Type>(Json::decodeFromString)

        override fun parseValue(value: String): Type = Json.decodeFromString(Uri.decode(value))

        override fun serializeAsValue(value: Type): String = Uri.encode(Json.encodeToString(value))

        override fun put(bundle: Bundle, key: String, value: Type) {
            bundle.putString(key, Json.encodeToString(value))
        }
    }

inline fun <reified Type : Any, reified Screen : ParameterScreen<Type>> from(savedStateHandle: SavedStateHandle): Screen {
    val typeMap = createTypeMap<Type>()
    return savedStateHandle.toRoute<Screen>(typeMap)
}
