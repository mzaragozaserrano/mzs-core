package presentation.base

import android.content.Context
import android.util.AttributeSet
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.AbstractComposeView

abstract class BaseComposeView<T : Any> @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0,
) : AbstractComposeView(context, attrs, defStyleAttr) {

    protected lateinit var item: T

    abstract var content: @Composable () -> Unit

    fun initComponent(item: T) {
        this.item = item
    }

    @Composable
    override fun Content() {
        if (this::item.isInitialized) {
            content()
        }
    }
    
}