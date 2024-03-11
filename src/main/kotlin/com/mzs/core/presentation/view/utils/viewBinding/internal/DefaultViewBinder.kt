package com.mzs.core.presentation.view.utils.viewBinding.internal

import android.view.View
import androidx.annotation.RestrictTo
import androidx.viewbinding.ViewBinding
import com.mzs.core.presentation.view.utils.viewBinding.ViewBinder

@RestrictTo(RestrictTo.Scope.LIBRARY)
@PublishedApi
internal class DefaultViewBinder<T : ViewBinding>(
    private val viewBindingClass: Class<T>,
) : ViewBinder<T> {

    private val bindViewMethod by lazy(LazyThreadSafetyMode.NONE) {
        viewBindingClass.getMethod("bind", View::class.java)
    }

    @Suppress("UNCHECKED_CAST")
    override fun bind(view: View): T {
        return bindViewMethod(null, view) as T
    }
}