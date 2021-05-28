package com.foundation.app.arc.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.foundation.app.arc.utils.ViewBindingHelper
import com.foundation.app.arc.utils.ext.ViewBindingLifecycleListener

/**
 * 完成viewBinding的初始化，设置根布局
 *create by zhusw on 4/22/21 11:28
 */
abstract class BaseViewBindingFragment<B : ViewBinding> : BaseFragmentManagerFragment(),
    ViewBindingLifecycleListener {

    private var binding: B? = null

    protected val viewBinding: B get() = binding!!

    @JvmField
    protected var jViewBinding: B? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ViewBindingHelper.getViewBindingInstance(this, inflater, container, false)
        jViewBinding = binding
        val vbCheck = requireNotNull(binding, {
            "BaseViewBindingFragment ViewBinding has not init  or init failure,please check it"
        })
        return vbCheck.root
    }

    override fun onDestroyView() {
        onViewBindingDestroy()
        binding = null
        super.onDestroyView()
    }

}