package com.foundation.app.simple.ui

import android.os.Bundle
import com.foundation.app.arc.utils.ext.observerStickyLess
import com.foundation.app.simple.architecture.BaseActivity
import com.foundation.app.simple.databinding.ActStickyBinding
import com.foundation.app.simple.vm.AppVM

/**
 * 粘性测试
 * create by zhusw on 5/21/21 10:53
 */
class StickyLiveDataActivity : BaseActivity() {
    val vm by lazyGlobalVM<AppVM>()
    val binding by lazyAndSetRoot<ActStickyBinding>()
    private var stickyCount = 0
    override fun init(savedInstanceState: Bundle?) {
        binding.btnSticky.setOnClickListener {
            vm.data.value = stickyCount + 1
        }

        binding.btnStickyLess.setOnClickListener {
            bindData()//重新订阅，测试是否被重复订阅
        }

    }

    override fun bindData() {
        vm.data.observe(this) {
            stickyCount = it
            binding.tv.text = "$stickyCount"
        }
        vm.data.observerStickyLess(this) {
            binding.tv2.text = "$it"
        }
    }

}

