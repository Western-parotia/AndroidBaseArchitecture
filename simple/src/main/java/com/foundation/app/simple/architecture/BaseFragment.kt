package com.foundation.app.simple.architecture

import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.foundation.app.arc.fragment.BaseViewBindingFragmentInJava

/**
 *create by zhusw on 5/18/21 18:38
 */
open class BaseFragment<VB : ViewBinding> : BaseViewBindingFragmentInJava<VB>() {
    override fun initViewModel() {

    }

    override fun init(savedInstanceState: Bundle?) {

    }

    override fun bindData() {

    }
}