package com.foundation.app.simple.ui

import android.os.Bundle
import com.foundation.app.simple.architecture.BaseActivity
import com.foundation.app.simple.databinding.ActMultiFragmentVisibleTestBinding
import com.foundation.app.simple.ui.fragment.visible.FragmentManagerVisibleTestFragment

/**
 * 测试 单个 fragment 可见性回调
 * create by zhusw on 5/19/21 13:24
 */
class MultiFragmentVisibleTestActivity : BaseActivity() {
    private val vb by lazyVB<ActMultiFragmentVisibleTestBinding>()
    override fun getContentVB() = vb

    override fun init(savedInstanceState: Bundle?) {
        switchFragment(
            FragmentManagerVisibleTestFragment().apply {
                arguments = Bundle().apply {
                    putInt("deep", 0)
                    putString("deepDesc", "")
                    putString("fragType", "主页")
                }
            },
            vb.flFrag.id
        )
    }

    override fun bindData() {

    }

}

