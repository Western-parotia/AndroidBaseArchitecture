package com.foundation.app.simple.demo.home

import androidx.lifecycle.MutableLiveData
import com.foundation.app.arc.utils.LoadingEvent
import com.foundation.app.simple.demo.home.data.BannerEntity
import com.foundation.app.simple.demo.net.WanAndroidNetStateHandler
import com.foundation.app.simple.demo.net.api.WanAndroidService
import com.foundation.service.net.NetManager
import com.foundation.service.net.getApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay

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
                delay(3000)//模拟耗时
                api.getBanner()
            }
//            throw IllegalAccessException("客户端错误")
        }, WanAndroidNetStateHandler(loadingStateLiveData))
    }


}