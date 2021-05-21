package com.foundation.app.simple.ui

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import com.foundation.app.arc.utils.ext.observerStickyLess
import com.foundation.app.simple.architecture.BaseActivity
import com.foundation.app.simple.databinding.ActStickyBinding
import com.foundation.app.simple.vm.AppVM
import com.foundation.app.simple.vm.ReflectionTest

/**
 * 粘性测试
 * create by zhusw on 5/21/21 10:53
 */
class StickyLiveDataActivity : BaseActivity() {
    val vm by lazyAppVM<AppVM>()
    val binding by initVB<ActStickyBinding>()

    override fun getContentVB(): ViewBinding = binding

    private var stickyCount = 0
    private var stickyLessCount = 0
    override fun init(savedInstanceState: Bundle?) {
        binding.btnSticky.setOnClickListener {
            vm.data.value = stickyCount + 1
        }

        binding.btnStickyLess.setOnClickListener {
            vm.data.value = stickyLessCount + 1
            bindData()
            ReflectionTest.test()
        }
    }

    val obs = object : Observer<Int> {
        override fun onChanged(t: Int) {
            stickyLessCount = t
            binding.tv2.text = "$stickyLessCount"
        }
    }

    override fun bindData() {
        vm.data.observe(this) {
            stickyCount = it
            binding.tv.text = "$stickyCount"
        }
        vm.data.observerStickyLess(this, obs)
    }

}

