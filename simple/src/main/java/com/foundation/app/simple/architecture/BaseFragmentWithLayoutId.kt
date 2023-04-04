package com.foundation.app.simple.architecture

import android.os.Bundle
import androidx.annotation.LayoutRes
import com.foundation.app.arc.fragment.BaseViewBindingFragment

/**
 *create by zhusw on 5/18/21 18:38
 */
open class BaseFragmentWithLayoutId(@LayoutRes id: Int) : BaseViewBindingFragment(id) {
    override fun initViewModel() {

    }

    override fun init(savedInstanceState: Bundle?) {

    }

    override fun bindData() {
    }
}