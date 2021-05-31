package com.foundation.app.simple.demo.home

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.foundation.app.simple.architecture.BaseActivity
import com.foundation.app.simple.databinding.ActHomeWanandroidBinding
import com.foundation.app.simple.demo.home.data.NewsFeedInfo
import com.foundation.app.simple.log
import com.foundation.app.simple.toast
import com.foundation.service.net.NetLoadingEvent
import com.foundation.widget.loading.NormalLoadingAdapter

/**
 * create by zhusw on 5/20/21 11:33
 */
class HomeActivity : BaseActivity() {

    private val viewBinding by lazyVB<ActHomeWanandroidBinding>()

    override fun getContentVB(): ViewBinding = viewBinding

    private lateinit var homeVM: HomeVM

    override fun initViewModel() {
        super.initViewModel()
        homeVM = getActivityVM(HomeVM::class.java)
    }

    private val adapter = NewsAdapter()
    override fun init(savedInstanceState: Bundle?) {
        viewBinding.rlNews.adapter = adapter
        viewBinding.rlNews.layoutManager = LinearLayoutManager(this)
        adapter.setOnItemClickListener { adapter, view, position ->
            val data = adapter.getItem(position) as NewsFeedInfo
            "点击：${data.title}".toast()

        }
        viewBinding.contentLoading.asLoading().setLoadingAdapter(LoadingAdapter(this))

        viewBinding.btnInit.setOnClickListener {
            homeVM.loadBanner()
            homeVM.loadNews(true)
        }
        viewBinding.btnListMore.setOnClickListener {
            homeVM.loadNews(false)
        }
        viewBinding.btnListNew.setOnClickListener {
            homeVM.loadNews(true)
        }
    }

    override fun bindData() {
        homeVM.loadEventLiveData.observe(this) {
            "loadState type :${it.type}".log("net--")
            when (it.type) {
                NetLoadingEvent.TYPE_START -> {
                    viewBinding.contentLoading.asLoading().showLoading()
                }
                NetLoadingEvent.TYPE_STOP -> {
                    viewBinding.contentLoading.asLoading().stop()
                }
                NetLoadingEvent.TYPE_ERROR -> {
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
            adapter.addData(it)
        }

    }


}

private class LoadingAdapter(private val ctx: Context) : NormalLoadingAdapter(ctx) {
    override fun getBottomPlateView(): View? {
        return View(ctx).apply {
            setBackgroundColor(Color.LTGRAY)
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
    }


}

