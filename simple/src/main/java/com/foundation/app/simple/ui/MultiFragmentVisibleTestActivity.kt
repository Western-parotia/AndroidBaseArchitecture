package com.foundation.app.simple.ui

import android.graphics.Color
import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.foundation.app.simple.architecture.BaseActivity
import com.foundation.app.simple.databinding.ActMultiFragmentVisibleTestBinding
import com.foundation.app.simple.jump
import com.foundation.app.simple.ui.adapter.FragmentAdapter
import com.foundation.app.simple.ui.fragment.VisibleMultiFragment
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * 测试 单个 fragment 可见性回调
 * create by zhusw on 5/19/21 13:24
 */
class MultiFragmentVisibleTestActivity : BaseActivity() {
    private val viewBinding by initVB<ActMultiFragmentVisibleTestBinding>()
    override fun getContentVB(): ViewBinding = viewBinding

    override fun init(savedInstanceState: Bundle?) {
        val fragments = listOf(
            VisibleMultiFragment.newInstance(Color.MAGENTA, "F 1"),
            VisibleMultiFragment.newInstance(Color.YELLOW, "F 2"),
            VisibleMultiFragment.newInstance(Color.CYAN, "F 3"),
            VisibleMultiFragment.newInstance(Color.GREEN, "F 4")
        )
        viewBinding.afvVp.adapter = FragmentAdapter(supportFragmentManager, fragments)
        viewBinding.afvVp.offscreenPageLimit = 1// 1 default
        viewBinding.btn.setOnClickListener {
            jump(EmptyActivity::class.java)
            MainScope().launch {
                delay(2000)
                viewBinding.afvVp.currentItem = 2
            }
        }
    }

    override fun bindData() {

    }

}

