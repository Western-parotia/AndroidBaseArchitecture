package com.foundation.app.arc.activity

import android.os.Bundle
import androidx.annotation.MainThread
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.viewbinding.ViewBinding
import com.foundation.app.arc.fragment.BaseVMFragment
import com.foundation.app.arc.utils.ext.AFViewModelLazy
import com.foundation.app.arc.utils.ext.ActivityViewBindingDelegate
import com.foundation.app.arc.utils.ext.lazyAtomic
import com.foundation.widget.binding.ViewBindingHelper

/**
 * ViewModel 创建与使用规范
 * ViewBinding 初始化与简化
 * create by zhusw on 5/18/21 15:05
 */
abstract class BaseVMVBActivity : BaseParamsActivity() {

    val activityVMProvider by lazyAtomic {
        ViewModelProvider(this)
    }
    val applicationVMProvider by lazyAtomic {
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
                throw IllegalStateException(
                    "application:$app 没实现" +
                            " ViewModelStoreOwner:调用处为 Activity$this "
                )
            }
        }
    }

    protected fun <VM : ViewModel> getActivityVM(clz: Class<VM>): VM {
        return activityVMProvider.get(clz)
    }

    protected fun <VM : ViewModel> getGlobalVM(clz: Class<VM>): VM {
        return applicationVMProvider.get(clz)
    }

    /**
    protected val testStr = ""
    protected inline fun testI() {
    val t = {
    testStr // protected val testStr = ""
    }
    t.invoke()
    }
    以上代码，在跨module中的子类中访问，将会报错
    如果inline 函数 内部使用了 lambda 或者匿名内部类,并在其中访问了外部类非public的成员
    就会导致无法访问的异常（仅在小米k30 os 10 上不复现）
     */
    @MainThread
    inline fun <reified VM : ViewModel> lazyActivityVM(): Lazy<VM> {
        return AFViewModelLazy(VM::class) {
            activityVMProvider
        }
    }

    @MainThread
    inline fun <reified VM : ViewModel> lazyGlobalVM(): Lazy<VM> {
        return AFViewModelLazy(VM::class) {
            applicationVMProvider
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val support = supportRebuildData()//1
        val state = if (support) savedInstanceState else null
        beforeSuperOnCreate(state)//2
        super.onCreate(state)//3
        afterSuperOnCreate(state)//4
        getContentVB()?.let { setContentView(it.root) }//5
        initViewModel()//6
        init(state)//7
        bindData()//8
    }

    /**
     * 是否支持activity被杀死后重建（是否使用 savedInstanceState中相关数据，
     * 系统默认在其中保存了Fragment的状态，重建会导致fragment异常展示）
     *
     * 注意：此处仅限于app杀死重建，对于fragment的内部的重建参考：[BaseVMFragment.onCreate]
     *
     * @return 默认不支持。如果返回true，则必须测试杀死后重建的流程
     */
    protected open fun supportRebuildData() = false

    /**
     * super.onCreate 之前回调
     * 支持一些特殊的窗口配置需要在onCreate之前设置
     */
    protected abstract fun beforeSuperOnCreate(savedInstanceState: Bundle?)

    /**
     * super.onCreate 之后回调
     */
    abstract fun afterSuperOnCreate(savedInstanceState: Bundle?)

    /**
     * 将ViewBinding.root 设置为根布局
     */
    @Deprecated(message = "不再需要实现", replaceWith = ReplaceWith("lazyAndSetRoot"))
    open fun getContentVB(): ViewBinding? = null

    /**
     * 主要是支持在java中使用，在kotlin中可用[lazyActivityVM]
     */
    protected abstract fun initViewModel()

    /**
     * 建议：
     * 1.view初始化,比如是否开启下拉刷新
     * 2.调用数据加载
     */
    protected abstract fun init(savedInstanceState: Bundle?)

    /**
     * 建议：
     * 订阅viewModel的数据并进行绑定
     */
    protected abstract fun bindData()

    @Deprecated(message = "不再需要实现", replaceWith = ReplaceWith("lazyAndSetRoot"))
    protected inline fun <reified VB : ViewBinding> lazyVB() = lazyAtomic {
        ViewBindingHelper.getViewBindingInstanceByClass(
            VB::class.java,
            layoutInflater, null
        )
    }

    protected inline fun <reified VB : ViewBinding> lazyAndSetRoot() =
        ActivityViewBindingDelegate(this, VB::class.java)
}