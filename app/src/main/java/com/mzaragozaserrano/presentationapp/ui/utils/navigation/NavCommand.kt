package com.mzaragozaserrano.presentationapp.ui.utils.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class NavCommand(
    internal open val feature: Feature,
    internal val subRoute: String = "home",
    private val navArgs: List<NavArg> = emptyList(),
) {
    object CategoryList : NavCommand(feature = Feature.Categories)
    object CategoryComponent : NavCommand(
        feature = Feature.Categories,
        subRoute = "id",
        navArgs = listOf(NavArg.Category)
    ) {
        fun createRoute(categoryName: Int) = "${feature.route}/$subRoute/$categoryName"
    }

    val route = run {
        val argValues = navArgs.map { "{${it.key}}" }
        listOf(feature.route)
            .plus(subRoute)
            .plus(argValues)
            .joinToString("/")
    }

    val args = navArgs.map {
        navArgument(it.key) { type = it.navType }
    }
}

sealed class NavArg(val key: String, val navType: NavType<*>) {
    object Category : NavArg("categoryName", NavType.IntType)

}