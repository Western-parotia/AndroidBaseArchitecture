package com.foundation.app.simple.ui

import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.foundation.app.simple.architecture.BaseActivity
import com.foundation.app.simple.databinding.ActStickyBinding
import com.foundation.app.simple.vm.AppVM

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
            vm.stickyData.value = stickyCount + 1
        }

        binding.btnStickyLess.setOnClickListener {
            vm.stickyLessData.value = stickyLessCount + 1
        }

    }

    override fun bindData() {
        vm.stickyData.observe(this) {
            stickyCount = it
            binding.tv.text = "$stickyCount"
        }
        vm.stickyLessData.observe(this) {
            stickyLessCount = it
            binding.tv2.text = "$stickyLessCount"
        }
    }
}