package com.foundation.app.af.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.foundation.app.af.utils.ViewBindingHelper

/**
 *@Desc:
 *-
 *-这是一个示范类
 *-可以单独实现[ViewBindingProvider]来跳转继承顺序
 *create by zhusw on 5/11/21 17:21
 */
abstract class BaseViewBindingFragment<T : ViewBinding> : Fragment(), ViewBindingProvider<T> {

    private var realBinding: T? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        realBinding = ViewBindingHelper.getViewBindingInstance(this, inflater, container, false)
        val vbCheck = requireNotNull(realBinding, {
            "${this::class.java} ViewBinding init failure,please check it"
        })
        return vbCheck.root
    }

    override val viewBinding: T
        get() = realBinding!!

    override fun onDestroyView() {
        super.onDestroyView()
        realBinding = null
    }
}
