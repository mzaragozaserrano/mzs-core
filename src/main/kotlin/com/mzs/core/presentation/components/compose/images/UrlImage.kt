package com.mzs.core.presentation.components.compose.images

import androidx.annotation.RawRes
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.mzs.core.presentation.utils.extensions.conditional

@Composable
fun UrlImage(
    modifier: Modifier = Modifier,
    @RawRes animationId: Int? = null,
    contentDescription: String? = null,
    contentScale: ContentScale,
    cornerRadius: Dp? = null,
    url: String,
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
    UrlImage(contentScale = ContentScale.Crop, url = "https://picsum.photos/200")
}