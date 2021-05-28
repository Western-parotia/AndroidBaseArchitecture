package com.foundation.app.arc.fragment

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.annotation.MainThread
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.foundation.app.arc.utils.ext.AFViewModelLazy
import com.foundation.app.arc.utils.ext.lazyAtomic

/**
 * ViewModel 创建与使用规范
 *create by zhusw on 5/18/21 13:36
 */
abstract class BaseVMFragment : BaseParamsFragment() {

    val fragmentVMProvider: ViewModelProvider by lazyAtomic {
        ViewModelProvider(this)
    }
    val activityVMProvider by lazyAtomic {
        val activity = requireActivity()
        ViewModelProvider(activity)
    }
    val applicationVMProvider by lazyAtomic {
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

    protected fun <VM : ViewModel> getFragmentVM(clz: Class<VM>): VM {
        return fragmentVMProvider.get(clz)
    }

    protected fun <VM : ViewModel> getActivityVM(clz: Class<VM>): VM {
        return activityVMProvider.get(clz)
    }

    protected fun <VM : ViewModel> getGlobalVM(clz: Class<VM>): VM {
        return applicationVMProvider.get(clz)
    }

    @MainThread
    inline fun <reified VM : ViewModel> lazyFragmentVM(): Lazy<VM> {
        return AFViewModelLazy(VM::class) { fragmentVMProvider }
    }

    @MainThread
    inline fun <reified VM : ViewModel> lazyActivityVM(): Lazy<VM> {
        return AFViewModelLazy(VM::class) { activityVMProvider }
    }

    @MainThread
    inline fun <reified VM : ViewModel> lazyGlobalVM(): Lazy<VM> {
        return AFViewModelLazy(VM::class) { applicationVMProvider }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()//1
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(savedInstanceState)//2
        bindData()//3
    }

    /**
     * 主要是支持在java中使用，在kotlin中可用[lazyFragmentVM]
     */
    protected abstract fun initViewModel()

    /**
     * 建议：
     * 1.view初始化,比如是否开启下拉刷新
     * 2.初始数据加载执
     */
    abstract fun init(savedInstanceState: Bundle?)

    /**
     * 建议：
     * 订阅viewModel的数据并进行绑定
     */
    protected abstract fun bindData()

}