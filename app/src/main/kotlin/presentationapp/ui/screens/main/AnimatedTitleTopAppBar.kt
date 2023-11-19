package presentationapp.ui.screens.main

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.mzaragozaserrano.app.R
import com.mzaragozaserrano.presentation.compose.components.texts.ExtraLargeMediumText

@Composable
fun AnimatedTitleTopAppBar(isFirstTime: Boolean, titleId: Int) {
    AnimatedContent(targetState = titleId, transitionSpec = {
        when {
            isFirstTime -> {
                slideInVertically(
                    initialOffsetY = { it },
                    animationSpec = tween(1250)
                ).togetherWith(
                    slideOutVertically(
                        targetOffsetY = { -it },
                        animationSpec = tween(1250)
                    )
                )
            }

            titleId != R.string.title_categories -> {
                slideInHorizontally(
                    initialOffsetX = { it },
                    animationSpec = tween(600)
                ).togetherWith(
                    slideOutHorizontally(
                        targetOffsetX = { -it },
                        animationSpec = tween(600)
                    )
                )
            }

            else -> {
                slideInHorizontally(
                    initialOffsetX = { -it },
                    animationSpec = tween(600)
                ).togetherWith(
                    slideOutHorizontally(
                        targetOffsetX = { it },
                        animationSpec = tween(600)
                    )
                )
            }
        }
    }, label = "") {
        ExtraLargeMediumText(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = it).uppercase()
        )
    }
}