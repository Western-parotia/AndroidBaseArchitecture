package com.foundation.app.af.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.foundation.app.af.utils.ViewBindingHelper
import com.foundation.app.af.utils.ext.ViewBindingLifecycleListener

/**
 *@Desc:
 *-
 *-完成viewBinding的初始化，设置根布局
 * 如果继承了BaseViewBindingFragment 却不需要使用ViewBinding则不指定范型
 * 并重写onCreateView 放弃super 调用
 *create by zhusw on 4/22/21 11:28
 */
open class BaseViewBindingFragment<B : ViewBinding> : BaseParamsFragment(),
    ViewBindingLifecycleListener {

    private var binding: B? = null

    val viewBinding: B get() = binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ViewBindingHelper.getViewBindingInstance(this, inflater, container, false)
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