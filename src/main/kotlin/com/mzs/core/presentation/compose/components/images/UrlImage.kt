package com.mzs.core.presentation.compose.components.images

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.mzs.core.presentation.R

@Composable
fun UrlImage(
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    cornerRadius: Dp? = null,
    url: String,
    contentScale: ContentScale = ContentScale.Crop,
) {

    SubcomposeAsyncImage(
        modifier = modifier,
        contentDescription = contentDescription,
        contentScale = contentScale,
        loading = {
            LottieImage(
                modifier = Modifier.padding(all = 120.dp),
                animation = R.raw.core_image_loading
            )
        },
        model = ImageRequest.Builder(context = LocalContext.current)
            .data(url)
            .crossfade(enable = true)
            .transformations(cornerRadius?.let { RoundedCornersTransformation(radius = LocalDensity.current.density * cornerRadius.value) }
                ?: RoundedCornersTransformation(radius = 0f))
            .build()
    )

}

@Preview
@Composable
private fun UrlImagePrev() {

    UrlImage(url = "https://picsum.photos/200")

}