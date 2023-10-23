package presentationapp.ui.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mzaragozaserrano.compose.composables.backgrounds.RoundedBackground
import com.mzaragozaserrano.compose.composables.images.ResourceImage
import com.mzaragozaserrano.compose.composables.texts.SmallText

@Composable
fun Category(
    modifier: Modifier = Modifier,
    @DrawableRes imageId: Int,
    @StringRes textId: Int,
) {
    RoundedBackground(
        modifier = modifier,
        backgroundColor = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically)
        ) {
            ResourceImage(
                iconId = imageId,
                iconTint = MaterialTheme.colorScheme.onBackground,
                size = 36.dp
            )
            SmallText(color = MaterialTheme.colorScheme.onBackground, textId = textId)
        }
    }
}