package com.thecocktailapp.core.presentation.view.utils.viewBinding

import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.activity.ComponentActivity
import androidx.annotation.IdRes
import androidx.annotation.MainThread
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import com.thecocktailapp.core.presentation.view.utils.viewBinding.internal.DefaultViewBinder
import com.thecocktailapp.core.presentation.view.utils.viewBinding.internal.checkIsMainThread
import com.thecocktailapp.core.presentation.view.utils.viewBinding.internal.requireViewByIdCompat
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

@PublishedApi
internal class ActivityViewBindingProperty<T : ViewBinding>(
    @IdRes private val rootViewId: Int,
    private val viewBinder: ViewBinder<T>,
) : ReadOnlyProperty<ComponentActivity, T> {

    internal var viewBinding: T? = null
    private val lifecycleObserver = BindingLifecycleObserver()

    @MainThread
    override fun getValue(thisRef: ComponentActivity, property: KProperty<*>): T {
        checkIsMainThread()
        viewBinding?.let { return it }

        val view: View = thisRef.requireViewByIdCompat(rootViewId)
        thisRef.lifecycle.addObserver(lifecycleObserver)
        return viewBinder.bind(view).also { viewBinding = it }
    }

    private inner class BindingLifecycleObserver : DefaultLifecycleObserver {

        private val mainHandler = Handler(Looper.getMainLooper())

        @MainThread
        override fun onDestroy(owner: LifecycleOwner) {
            owner.lifecycle.removeObserver(this)
            mainHandler.post {
                viewBinding = null
            }
        }
    }
}

@Suppress("unused")
inline fun <reified T : ViewBinding> ComponentActivity.viewBinding(
    @IdRes rootViewId: Int,
): ReadOnlyProperty<ComponentActivity, T> {
    return ActivityViewBindingProperty(rootViewId, DefaultViewBinder(T::class.java))
}

@Suppress("unused")
inline fun <T : ViewBinding> ComponentActivity.viewBinding(
    @IdRes rootViewId: Int,
    crossinline bindView: (View) -> T,
): ReadOnlyProperty<ComponentActivity, T> {
    return ActivityViewBindingProperty(rootViewId, viewBinder(bindView))
}
