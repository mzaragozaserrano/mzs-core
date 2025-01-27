package com.mzs.core.presentation.components.compose.images

import androidx.annotation.RawRes
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation

@Composable
fun UrlImage(
    modifier: Modifier = Modifier,
    @RawRes animationId: Int? = null,
    contentDescription: String? = null,
    contentScale: ContentScale,
    cornerRadius: Dp? = null,
    onLoading: ((AsyncImagePainter.State.Loading) -> Unit)? = null,
    onSuccess: ((AsyncImagePainter.State.Success) -> Unit)? = null,
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
            .data(data = url)
            .transformations(cornerRadius?.let { RoundedCornersTransformation(radius = LocalDensity.current.density * cornerRadius.value) }
                ?: RoundedCornersTransformation(radius = 0f))
            .memoryCachePolicy(policy = CachePolicy.ENABLED)
            .build(),
        onLoading = onLoading,
        onSuccess = onSuccess
    )
}

@PreviewLightDark
@Composable
private fun UrlImagePrev() {
    UrlImage(contentScale = ContentScale.Crop, url = "https://picsum.photos/200")
}