package presentationapp.ui.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mzaragozaserrano.app.R
import com.mzaragozaserrano.presentation.compose.components.texts.ExtraLargeMediumText
import presentationapp.ui.utils.CategoryType
import presentationapp.ui.utils.navigation.NavCommand
import presentationapp.ui.utils.navigation.Navigation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route
    var isFirstTime by remember { mutableStateOf(true) }
    var titleId by remember { mutableIntStateOf(R.string.app_name) }
    var categoryType by remember { mutableStateOf<CategoryType?>(null) }

    LaunchedEffect(currentRoute) {
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
                    AnimatedContent(targetState = titleId, transitionSpec = {
                        when {
                            isFirstTime -> {
                                (slideInVertically(
                                    initialOffsetY = { it },
                                    animationSpec = tween(1250)
                                ).togetherWith(
                                    slideOutVertically(
                                        targetOffsetY = { -it },
                                        animationSpec = tween(1250)
                                    )
                                ))
                            }

                            titleId != R.string.title_categories -> {
                                (slideInHorizontally(
                                    initialOffsetX = { it },
                                    animationSpec = tween(600)
                                ).togetherWith(
                                    slideOutHorizontally(
                                        targetOffsetX = { -it },
                                        animationSpec = tween(600)
                                    )
                                ))
                            }

                            else -> {
                                (slideInHorizontally(
                                    initialOffsetX = { -it },
                                    animationSpec = tween(600)
                                ).togetherWith(
                                    slideOutHorizontally(
                                        targetOffsetX = { it },
                                        animationSpec = tween(600)
                                    )
                                ))
                            }
                        }
                    }, label = "") {
                        ExtraLargeMediumText(
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(id = it).uppercase()
                        )
                    }
                }
            )
        }
    ) { padding ->
        Navigation(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
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