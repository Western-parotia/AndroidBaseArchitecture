package com.foundation.app.simple.demo.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.foundation.app.arc.vm.BaseViewModel
import com.foundation.app.simple.demo.home.data.BannerEntity
import com.foundation.app.simple.demo.net.LoadingEvent

/**
 *
 */
class HomeVM : BaseViewModel() {
    protected val _loadState = MutableLiveData<LoadingEvent>()
    val loadState: LiveData<LoadingEvent> = _loadState
    private val homeRepo by lazy {
        HomeRepo(viewModelScope, _loadState)
    }

    /**
     * 核心架构 思想：保证单一可信源
     * view层只能订阅状态，不可修改状态
     */
    private val _bannerData = MutableLiveData<List<BannerEntity>>()
    val bannerData: LiveData<List<BannerEntity>> = _bannerData


    fun loadBanner() {
        homeRepo.getBanner(_bannerData)
    }


}