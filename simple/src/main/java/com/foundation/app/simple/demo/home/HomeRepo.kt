package com.foundation.app.simple.demo.home

import androidx.lifecycle.MutableLiveData
import com.foundation.app.simple.demo.home.data.BannerEntity
import com.foundation.app.simple.demo.net.LoadingEvent
import com.foundation.app.simple.demo.net.WanAndroidNetStateHandler
import com.foundation.app.simple.demo.net.api.WanAndroidService
import com.foundation.service.net.NetManager
import com.foundation.service.net.getApiService
import kotlinx.coroutines.CoroutineScope

/**
 * create by zhusw on 5/24/21 09:58
 */
class HomeRepo(
    uiCoroutineScope: CoroutineScope,
    private val loadingStateLiveData: MutableLiveData<LoadingEvent>
) : BaseWanAndroidRepo<LoadingEvent>(uiCoroutineScope) {

    val api = NetManager.getApiService<WanAndroidService>()

    fun getBanner(receiver: MutableLiveData<List<BannerEntity>>) {
        launch({
            receiver.value = take {
                api.getBanner()
            }
            throw IllegalArgumentException("数据处理异常")
        }, WanAndroidNetStateHandler(loadingStateLiveData))
    }


}