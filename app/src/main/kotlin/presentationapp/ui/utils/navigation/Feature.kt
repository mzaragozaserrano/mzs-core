package presentationapp.ui.utils.navigation

sealed class Feature(val route: String) {
    object Categories: Feature("categories")
}