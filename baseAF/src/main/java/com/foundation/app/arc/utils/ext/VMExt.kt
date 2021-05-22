package com.foundation.app.arc.utils.ext

import androidx.annotation.MainThread
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlin.reflect.KClass

/**
 *@Desc:
 *-
 *-ViewModel 相关 拓展
 *create by zhusw on 5/17/21 16:02
 */

@MainThread
class AFViewModelLazy<VM : ViewModel>(
    private val viewModelClass: KClass<VM>,
    private val viewModelProvider: () -> ViewModelProvider
) : Lazy<VM> {
    private var cached: VM? = null
    override val value: VM
        get() {
            val viewModel = cached
            return viewModel ?: viewModelProvider.invoke().get(viewModelClass.java).also {
                cached = it
            }
        }

    override fun isInitialized(): Boolean = cached != null
}
