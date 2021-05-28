package com.foundation.app.simple.demo.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.foundation.app.simple.demo.base.BaseWanAndroidVM
import com.foundation.app.simple.demo.home.data.BannerEntity
import com.foundation.app.simple.demo.home.data.NewsFeedInfo
import com.foundation.app.simple.demo.net.WanAndroidNetStateHandler
import kotlinx.coroutines.delay

/**
 *
 */
class HomeVM : BaseWanAndroidVM() {

    private val homeRepo by lazy {
        HomeRepo()
    }

    /**
     * 核心架构 思想：保证单一可信源
     * view层只能订阅状态，不可修改状态
     */
    private val _bannerData = MutableLiveData<List<BannerEntity>>()
    val bannerData: LiveData<List<BannerEntity>> = _bannerData


    private val _newsLiveData = MutableLiveData<List<NewsFeedInfo>>()
    val newsLiveData: LiveData<List<NewsFeedInfo>> = _newsLiveData

    fun loadBanner() {
        netLaunch({
            delay(3000)
            val data = withBusiness {
                homeRepo.homeApi.getBanner()
            }
            _bannerData.value = data
        }, WanAndroidNetStateHandler(true, _loadEventLiveData), "加载 banner")
    }

    private var pageCount = -1

    private val _cleanAdapterLiveData = MutableLiveData<Unit>()
    val cleanAdapterLiveData: LiveData<Unit> = _cleanAdapterLiveData

    fun loadNews(refresh: Boolean = true) {
        if (refresh) {
            _cleanAdapterLiveData.value = Unit
            pageCount = 0
        } else {
            pageCount++
        }
        netLaunch(
            {
                val data = withBusiness {
                    homeRepo.homeApi.getNews(pageCount)
                }
                _newsLiveData.value = data.datas
            }, WanAndroidNetStateHandler(stateLiveData = _loadEventLiveData),
            "加载 列表"
        )
    }
}