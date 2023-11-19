package presentationapp.ui.utils.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import presentationapp.ui.screens.category.CategoryComponentScreen
import presentationapp.ui.screens.category.CategoryListScreen
import presentationapp.ui.utils.CategoryType
import presentationapp.ui.utils.serializableCategory

@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    onCategoryClicked: (CategoryType) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Feature.Categories.route
    ) {
        presentationApp(modifier = modifier, navController = navController, onCategoryClicked = onCategoryClicked)
    }
}

private fun NavGraphBuilder.presentationApp(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    onCategoryClicked: (CategoryType) -> Unit,
) {
    navigation(
        route = Feature.Categories.route,
        startDestination = NavCommand.CategoryList.route
    ) {
        composable(navItem = NavCommand.CategoryList) {
            CategoryListScreen(modifier = modifier) { category ->
                onCategoryClicked(category)
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