package com.foundation.app.simple.demo.home

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.foundation.app.arc.utils.LoadingEvent
import com.foundation.app.simple.architecture.BaseActivity
import com.foundation.app.simple.databinding.ActHomeWanandroidBinding
import com.foundation.app.simple.log
import com.foundation.app.simple.toast

/**
 * create by zhusw on 5/20/21 11:33
 */

class HomeActivity : BaseActivity() {
//    java.lang.IllegalAccessError: Method com.xx is inaccessible to class com.xx declaration of com.xx appears in base.apk!classes2.dex

    private val homeVM by lazyActivityVM<HomeVM>()

    private val viewBinding by initVB<ActHomeWanandroidBinding>()

    override fun getContentVB(): ViewBinding = viewBinding

    private val adapter = NewsAdapter()
    override fun init(savedInstanceState: Bundle?) {
        viewBinding.rlNews.adapter = adapter
        viewBinding.rlNews.layoutManager = LinearLayoutManager(this)
        adapter.setOnItemChildClickListener { adapter, view, position ->

        }

        viewBinding.btnBanner.setOnClickListener {
            homeVM.loadBanner()
        }
        viewBinding.btnList.setOnClickListener {
            homeVM.loadNews(false)
        }

    }

    override fun bindData() {
        homeVM.loadEventLiveData.observe(this) {
            "loadState type :${it.type}".log("net--")
            when (it.type) {
                LoadingEvent.TYPE_START -> {
                    viewBinding.contentLoading.asLoading().showLoading()
                }
                LoadingEvent.TYPE_STOP -> {
                    viewBinding.contentLoading.asLoading().stop()
                }
                LoadingEvent.TYPE_ERROR -> {
                    viewBinding.contentLoading.asLoading().stop()
                    "${it.msg}:${it.code}".toast()
                }
            }
        }
        homeVM.bannerData.observe(this) {
            Glide.with(this).load(it[2].imagePath)
                .into(viewBinding.ivBanner)
            viewBinding.tvBannerTitle.text = it[2].title
        }

        homeVM.cleanAdapterLiveData.observe(this) {
            adapter.setNewInstance(null)
        }
        homeVM.newsLiveData.observe(this) {
            "size ${it.size}".toast()
            adapter.addData(it)
        }

    }

}