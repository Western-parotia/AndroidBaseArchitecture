package com.foundation.app.af.fragment

import android.os.Bundle
import androidx.annotation.MainThread
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.foundation.app.af.utils.ext.AFViewModelLazy
import com.foundation.app.af.utils.ext.lazyAtomic

/**
 *@Desc:
 *- viewModel 创建管理
 *create by zhusw on 5/18/21 13:36
 */
abstract class BaseVMFragment : BaseParamsFragment() {

    protected val fragmentVMProvider: ViewModelProvider by lazyAtomic {
        ViewModelProvider(this)
    }
    protected val activityVMProvider by lazyAtomic {
        val activity = requireActivity()
        ViewModelProvider(activity)
    }
    protected val applicationVMProvider by lazyAtomic {
        val activity = requireActivity()
        val app = activity.application ?: throw IllegalStateException(
            "$hostActivity 还没有 attached to application." +
                    "Fragment 不可以在 onCreate 之前使用  ViewModel"
        )
        when (app) {
            is ViewModelStoreOwner -> {
                ViewModelProvider(
                    app.viewModelStore,
                    ViewModelProvider.AndroidViewModelFactory.getInstance(app)
                )
            }
            else -> {
                throw IllegalStateException("application:$app 没实现 ViewModelStoreOwner:调用处Fragment为 $this ")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    open fun initViewModel() {}

    fun <VM : ViewModel> getFragmentVM(clz: Class<VM>): VM {
        return fragmentVMProvider.get(clz)
    }

    fun <VM : ViewModel> getActivityVM(clz: Class<VM>): VM {
        return activityVMProvider.get(clz)
    }

    fun <VM : ViewModel> getAppVM(clz: Class<VM>): VM {
        return applicationVMProvider.get(clz)
    }

    @MainThread
    protected inline fun <reified VM : ViewModel> lazyFragmentVM(): Lazy<VM> {
        return AFViewModelLazy(VM::class) { fragmentVMProvider }
    }

    @MainThread
    protected inline fun <reified VM : ViewModel> lazyActivityVM(): Lazy<VM> {
        return AFViewModelLazy(VM::class) { activityVMProvider }
    }

    @MainThread
    protected inline fun <reified VM : ViewModel> lazyAppVM(): Lazy<VM> {
        return AFViewModelLazy(VM::class) { applicationVMProvider }
    }

}