package presentationapp.ui.screens.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mzaragozaserrano.app.R
import presentationapp.ui.utils.CategoryType
import presentationapp.ui.utils.navigation.NavCommand
import presentationapp.ui.utils.navigation.Navigation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {

    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()

    var categoryType by remember { mutableStateOf<CategoryType?>(value = null) }
    val currentRoute = currentBackStackEntry?.destination?.route
    var isFirstTime by remember { mutableStateOf(value = true) }
    var titleId by remember { mutableIntStateOf(value = R.string.app_name) }

    LaunchedEffect(key1 = currentRoute) {
        when {
            titleId == R.string.app_name -> {
                categoryType = null
                titleId = R.string.title_categories
            }

            currentRoute?.contains(NavCommand.CategoryList.route) == true -> {
                categoryType = null
                titleId = R.string.title_categories
            }

            categoryType != null -> {
                titleId = when (requireNotNull(categoryType)) {
                    is CategoryType.Buttons -> {
                        R.string.title_buttons
                    }

                    is CategoryType.Labels -> {
                        R.string.category_labels
                    }

                    is CategoryType.Texts -> {
                        R.string.title_texts
                    }

                    is CategoryType.Utils -> {
                        R.string.title_utils
                    }

                }
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
                title = {
                    AnimatedTitleTopAppBar(isFirstTime = isFirstTime, titleId = titleId)
                }
            )
        }
    ) { padding ->
        Navigation(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = padding),
            navController = navController
        ) { category ->
            categoryType = category
            isFirstTime = false
        }
    }

}

@Preview
@Composable
private fun PresentationAppPrev() {

    MainScreen()

}