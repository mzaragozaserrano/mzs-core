package com.mzaragozaserrano.presentation.compose.components.images

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.mzaragozaserrano.presentation.R

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
            LottieImage(animation = R.raw.core_image_loading)
        },
        model = ImageRequest.Builder(context = LocalContext.current)
            .data(url)
            .crossfade(enable = true)
            .transformations(cornerRadius?.let { RoundedCornersTransformation(radius = LocalDensity.current.density * cornerRadius.value) }
                ?: RoundedCornersTransformation(radius = 0f))
            .build()
    )

}