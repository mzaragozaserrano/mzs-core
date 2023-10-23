package presentationapp.ui.utils.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import presentationapp.ui.screens.CategoryComponentScreen
import presentationapp.ui.screens.CategoryListScreen
import presentationapp.ui.utils.serializableCategory

@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Feature.Categories.route
    ) {
        presentationApp(modifier = modifier, navController = navController)
    }
}

private fun NavGraphBuilder.presentationApp(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    navigation(
        route = Feature.Categories.route,
        startDestination = NavCommand.CategoryList.route
    ) {
        composable(navItem = NavCommand.CategoryList) {
            CategoryListScreen(modifier = modifier) { category ->
                navController.navigate(route = NavCommand.CategoryComponent.createRoute(categoryName = category.serializableCategory()))
            }
        }
        composable(navItem = NavCommand.CategoryComponent) {
            CategoryComponentScreen(modifier = modifier)
        }
    }
}

private fun NavGraphBuilder.composable(
    navItem: NavCommand,
    content: @Composable (NavBackStackEntry) -> Unit,
) {
    composable(
        route = navItem.route,
        arguments = navItem.args
    ) {
        content(it)
    }
}