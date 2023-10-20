package presentationapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mzaragozaserrano.compose.composables.buttons.WavyButton
import com.mzaragozaserrano.compose.composables.cards.RoundedCard
import com.mzaragozaserrano.presentationapp.R
import presentationapp.ui.vo.ComponentVO

@Composable
fun Component(modifier: Modifier = Modifier, component: ComponentVO) {
    RoundedCard(modifier = modifier, backgroundColor = MaterialTheme.colorScheme.primaryContainer) {
        Column(
            modifier = Modifier.padding(all = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontSize = 24.sp,
                text = stringResource(id = component.nameId).uppercase()
            )
            component.item()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ComponentPrev() {
    Component(
        modifier = Modifier.padding(all = 16.dp),
        component = ComponentVO(
            nameId = R.string.wavy_button,
            item = {
                WavyButton(
                    iconId = R.drawable.ic_category_buttons,
                    textId = R.string.button_text
                ) {}
            }
        )
    )
}