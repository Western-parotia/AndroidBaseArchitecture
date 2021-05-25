package com.foundation.app.simple.demo.home

import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.foundation.app.simple.architecture.BaseActivity
import com.foundation.app.simple.databinding.ActHomeWanandroidBinding

/**
 * create by zhusw on 5/20/21 11:33
 */

class HomeActivity : BaseActivity() {
//    java.lang.IllegalAccessError: Method com.xx is inaccessible to class com.xx declaration of com.xx appears in base.apk!classes2.dex

    private val homeVM by lazyActivityVM<HomeVM>()

    private val viewBinding by initVB<ActHomeWanandroidBinding>()

    override fun getContentVB(): ViewBinding = viewBinding
    override fun init(savedInstanceState: Bundle?) {
        viewBinding.btnStart.setOnClickListener {
            homeVM.loadBanner()
        }
    }

    override fun bindData() {
        homeVM.loadState.observe(this) {
            when (it) {
                LoadingState.LOADING_START -> {
                    viewBinding.contentLoading.asLoading().showLoading()
                }
                LoadingState.LOADING_STOP -> {
                    viewBinding.contentLoading.asLoading().stop()
                }
                LoadingState.LOADING_ERROR -> {

                }
            }
        }
        homeVM.bannerData.observe(this) {
            Glide.with(this).load(it[1].imagePath)
                .into(viewBinding.ivBanner)
            viewBinding.tvBannerTitle.text = it[1].title
        }
        homeVM.errorLiveData.observe(this) {
            viewBinding.tvErrorMsg.text = it.msg
        }
    }

}