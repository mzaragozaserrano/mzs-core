package presentation.components.utils

import androidx.annotation.RawRes
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thecocktailapp.core.R
import presentation.components.images.LottieImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LottieProgressDialog(modifier: Modifier = Modifier, @RawRes animation: Int) {

    AlertDialog(
        modifier = modifier,
        onDismissRequest = {}
    ) {
        LottieImage(modifier = Modifier.fillMaxSize(), animation = animation)
    }

}

@Preview
@Composable
private fun LottieProgressDialogPrev() {

    LottieProgressDialog(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 60.dp)
            .aspectRatio(ratio = 1f),
        animation = R.raw.core_image_loading
    )

}