package com.foundation.app.arc.utils.ext

import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
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

//<editor-fold desc="获取application级别的VM">
/**
 * val vm by viewModels<BaseViewModel>()
 * val vm2 by applicationViewModels<BaseViewModel>()
 */
/*
@MainThread
inline fun <reified VM : ViewModel> ComponentActivity.applicationViewModels(): Lazy<VM> {
    val storeProducer: () -> ViewModelStore = {
        val app = application
        when (app) {
            is ViewModelStoreOwner -> {
                app.viewModelStore
            }
            else -> {
                throw IllegalStateException("application:$app 没实现 ViewModelStoreOwner:调用处Activity为 $this ")
            }
        }
    }
    val factoryProducer: () -> ViewModelProvider.Factory = {
        val d = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        Log.e("viewModel", "ComponentActivity:$d")
        d
    }
    return ViewModelLazy(
        VM::class,
        storeProducer,
        factoryProducer
    )

}*/


/**
 * val viewModel1 by viewModels<BaseViewModel>({requireParentFragment()})
 * val viewModel2 by activityViewModels<BaseViewModel>()
 * val viewModel3 by applicationViewModels<BaseViewModel>()
 */
@MainThread
inline fun <reified VM : ViewModel> Fragment.applicationViewModels(): Lazy<VM> {
    val storeProducer: () -> ViewModelStore = {
        val activity = requireActivity()
        val app = activity.application
        when (app) {
            is ViewModelStoreOwner -> {
                app.viewModelStore
            }
            else -> {
                throw IllegalStateException("application:$app 没实现 ViewModelStoreOwner:调用处Fragment为 $this ")
            }
        }
    }
    val factoryProducer: () -> ViewModelProvider.Factory = {
        val activity = requireActivity()
        val app = activity.application
        ViewModelProvider.AndroidViewModelFactory.getInstance(app)
    }
    return ViewModelLazy(
        VM::class,
        storeProducer,
        factoryProducer
    )
}
//</editor-fold>