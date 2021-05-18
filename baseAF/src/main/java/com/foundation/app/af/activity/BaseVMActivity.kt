package com.foundation.app.af.activity

import androidx.annotation.MainThread
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.foundation.app.af.utils.ext.AFViewModelLazy
import com.foundation.app.af.utils.ext.lazyAtomic

/**
 *@Desc:
 *-
 *-
 *create by zhusw on 5/18/21 15:05
 */
abstract class BaseVMActivity : BaseParamsActivity() {
    protected val activityVMProvider by lazyAtomic {
        ViewModelProvider(this)
    }
    protected val applicationVMProvider by lazyAtomic {
        val app = application ?: throw IllegalStateException(
            "$this has not attached application." +
                    "Activity 不可以在 onCreate 之前使用  ViewModel"
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

    fun <VM : ViewModel> getActivityVM(clz: Class<VM>): VM {
        return activityVMProvider.get(clz)
    }

    fun <VM : ViewModel> getAppVM(clz: Class<VM>): VM {
        return applicationVMProvider.get(clz)
    }

    @MainThread
    protected inline fun <reified VM : ViewModel> lazyActivityVM(): Lazy<VM> {
        return AFViewModelLazy(VM::class) { activityVMProvider }
    }

    @MainThread
    protected inline fun <reified VM : ViewModel> lazyAppVM(): Lazy<VM> {
        return AFViewModelLazy(VM::class) {
            applicationVMProvider
        }
    }
}