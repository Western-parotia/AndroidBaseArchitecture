package com.foundation.app.arc.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.viewbinding.ViewBinding
import com.foundation.app.arc.utils.ext.FragmentViewBindingDelegate
import com.foundation.app.arc.utils.ext.ViewBindingLifecycleListener

/**
 *@Desc:
 *- 约束在子类中使用[initVB] 时 必须设置 layoutId
 *- create by zhusw on 5/18/21 10:46
 */
abstract class BaseViewBinding2Fragment(@LayoutRes private val layoutId: Int) :
    BaseVMFragment(), ViewBindingLifecycleListener {

    /**
     * 懒加载赋值
     * viewBinding 销毁前调用 [onViewBindingDestroy]
     */
    protected inline fun <reified VB : ViewBinding> initVB() = FragmentViewBindingDelegate {
        VB::class.java.getMethod("bind", View::class.java)
            .invoke(null, requireView()) as VB
    }

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
