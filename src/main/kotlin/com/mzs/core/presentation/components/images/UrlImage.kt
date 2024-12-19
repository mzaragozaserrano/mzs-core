package com.mzs.core.presentation.components.images

import androidx.annotation.RawRes
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

@Composable
fun UrlImage(
    modifier: Modifier = Modifier,
    @RawRes animationId: Int? = null,
    contentDescription: String? = null,
    cornerRadius: Dp? = null,
    url: String,
    contentScale: ContentScale = ContentScale.Crop,
) {
    SubcomposeAsyncImage(
        modifier = modifier,
        contentDescription = contentDescription,
        contentScale = contentScale,
        loading = if (animationId != null) {
            {
                LottieImage(
                    modifier = Modifier.padding(all = 16.dp),
                    animationId = animationId
                )
            }
        } else {
            null
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