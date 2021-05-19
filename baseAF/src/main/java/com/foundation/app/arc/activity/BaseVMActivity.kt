package com.foundation.app.arc.activity

import android.os.Bundle
import androidx.annotation.MainThread
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.foundation.app.arc.utils.ext.AFViewModelLazy
import com.foundation.app.arc.utils.ext.lazyAtomic

/**
 *@Desc:
 *- ViewModel 创建与使用规范
 *create by zhusw on 5/18/21 15:05
 */
abstract class BaseVMActivity : BaseParamsActivity() {
    protected val activityVMProvider by lazyAtomic {
        ViewModelProvider(this)
    }
    protected val applicationVMProvider by lazyAtomic {
        val app = application ?: throw IllegalStateException(
            "$this is not attach application." +
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

    protected fun <VM : ViewModel> getActivityVM(clz: Class<VM>): VM {
        return activityVMProvider.get(clz)
    }

    protected fun <VM : ViewModel> getAppVM(clz: Class<VM>): VM {
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

    override fun onCreate(savedInstanceState: Bundle?) {
        beforeOnCreate(savedInstanceState)//1
        super.onCreate(savedInstanceState)
        initViewModel()//2
        init(savedInstanceState)//3
        observeData()//4
    }

    /**
     * 支持一些特殊的窗口配置需要在onCreate之前设置
     */
    protected abstract fun beforeOnCreate(savedInstanceState: Bundle?)

    /**
     * 主要是支持在java中使用，在kotlin中可用[lazyActivityVM]
     */
    protected abstract fun initViewModel()

    /**
     * 建议：
     * 1.view初始化,比如是否开启下拉刷新
     * 2.初始数据加载执
     */
    protected abstract fun init(savedInstanceState: Bundle?)

    /**
     * 建议：
     * 订阅viewModel的数据并进行绑定
     */
    protected abstract fun observeData()


}