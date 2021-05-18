package com.foundation.app.af.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.viewbinding.ViewBinding
import com.foundation.app.af.utils.ext.FragmentViewBindingDelegate
import com.foundation.app.af.utils.ext.ViewBindingLifecycleListener

/**
 *@Desc:
 *- 为了约束仅在子类中可用自动绑定对[autoBind] [bind]都进行了内部约束
 *- layoutId 作为减少[onCreateView]模版代码的替代品
 *- create by zhusw on 5/18/21 10:46
 */
abstract class BaseViewBinding2Fragment(@LayoutRes private val layoutId: Int) :
    BaseVMFragment(), ViewBindingLifecycleListener {

    /**
     * 懒加载赋值
     * viewBinding 销毁前调用 [onViewBindingDestroy]
     */
    protected inline fun <reified VB : ViewBinding> autoBind() = FragmentViewBindingDelegate<VB> {
        requireView().bind()
    }

    protected inline fun <reified VB : ViewBinding> View.bind(): VB =
        VB::class.java.getMethod("bind", View::class.java)
            .invoke(null, this) as VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        if (layoutId != 0) {
            inflater.inflate(layoutId, container, false)
        } else {
            null
        }

}
