package com.foundation.app.simple.ui

import android.graphics.Color
import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.foundation.app.simple.R
import com.foundation.app.simple.architecture.BaseActivity
import com.foundation.app.simple.databinding.ActSingleFragmentVisibleTestBinding
import com.foundation.app.simple.ui.fragment.VisibleOneFragment

/**

*-
 *- 测试 单个 fragment 可见性回调
 *create by zhusw on 5/19/21 13:24
 */
class SingleFragmentVisibleTestActivity : BaseActivity() {
    private val viewBinding by initVB<ActSingleFragmentVisibleTestBinding>()
    override fun getContentVB(): ViewBinding = viewBinding

    override fun init(savedInstanceState: Bundle?) {
        switchFragment(
            VisibleOneFragment.newInstance(Color.YELLOW, "单个fragment"), R.id.afv_fl
        )
    }

    override fun bindData() {

    }
}

