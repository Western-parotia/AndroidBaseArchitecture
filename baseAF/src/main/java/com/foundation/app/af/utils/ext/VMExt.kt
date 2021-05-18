package com.foundation.app.af.utils.ext

import androidx.activity.ComponentActivity
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

/**
 *@Desc:
 *-
 *-ViewModel 相关 拓展
 *create by zhusw on 5/17/21 16:02
 */

//<editor-fold desc="获取application级别的VM">
/**
 * val vm by viewModels<BaseViewModel>()
 * val vm2 by applicationViewModels<BaseViewModel>()
 */
@MainThread
inline fun <reified VM : ViewModel> ComponentActivity.applicationViewModels(): Lazy<VM> {
    val app = application// val app 因为 此处 是 getApplication()，返回值可变，
    return when (app) {
        is ViewModelStoreOwner -> ViewModelLazy(
            VM::class,
            { app.viewModelStore },
            { defaultViewModelProviderFactory })
        else -> throw IllegalStateException("Activity $this application is not implement ViewModelStoreOwner")
    }
}

/**
 * val viewModel1 by viewModels<BaseViewModel>({requireParentFragment()})
 * val viewModel2 by activityViewModels<BaseViewModel>()
 * val viewModel3 by applicationViewModels<BaseViewModel>()
 */
@MainThread
inline fun <reified VM : ViewModel> Fragment.applicationViewModels(
    noinline factoryProducer: (() -> ViewModelProvider.Factory)? = null
): Lazy<VM> = when (val app = requireActivity().application) {
    is ViewModelStoreOwner -> createViewModelLazy(
        VM::class,
        { app.viewModelStore },
        factoryProducer
    )
    else -> throw IllegalStateException("fragment $this application is not implement ViewModelStoreOwner")
}
//</editor-fold>