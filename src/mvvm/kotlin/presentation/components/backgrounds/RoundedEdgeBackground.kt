package presentation.components.backgrounds

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.thecocktailapp.core.R
import presentation.components.texts.NormalText

@Composable
fun RoundedEdgeBackground(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    borderColor: Color,
    cornerRadius: Dp,
    strokeBorder: Dp,
    content: @Composable () -> Unit,
) {

    Card(
        shape = RoundedCornerShape(size = cornerRadius),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        border = BorderStroke(color = borderColor, width = strokeBorder),
        modifier = modifier
    ) {
        content()
    }

}

@Preview
@Composable
private fun RoundedEdgeBackgroundPrev() {
    RoundedEdgeBackground(
        modifier = Modifier.padding(all = 16.dp),
        backgroundColor = Color.White,
        borderColor = Color.Black,
        cornerRadius = 12.dp,
        strokeBorder = 1.dp
    ) {
        NormalText(
            modifier = Modifier.padding(all = 16.dp),
            color = Color.Black,
            text = stringResource(id = R.string.core_hello_world)
        )
    }
}