package com.foundation.app.simple.demo.home

import androidx.lifecycle.MutableLiveData
import com.foundation.app.simple.demo.entity.BaseApiResponse
import com.foundation.app.simple.demo.home.data.BannerEntity
import com.foundation.app.simple.demo.net.WanAndroidResException
import com.foundation.app.simple.demo.net.api.WanAndroidService
import com.foundation.app.simple.log
import com.foundation.service.net.NetException
import com.foundation.service.net.NetManager
import com.foundation.service.net.NetStateListener
import com.foundation.service.net.getApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import retrofit2.Response

/**
 * create by zhusw on 5/24/21 09:58
 */
class HomeRepo(
    uiCoroutineScope: CoroutineScope,
    val errorLiveData: MutableLiveData<WanAndroidResException>,
    val loadingLiveData: MutableLiveData<LoadingState>
) : BaseRepo<WanAndroidResException>(uiCoroutineScope) {

    val api = NetManager.getApiService<WanAndroidService>()

    fun getBanner(receiver: MutableLiveData<List<BannerEntity>>) {
        launch({
            val data: List<BannerEntity> = take {
                api.getBanner()
            }
            delay(3000)//看loading 效果
            receiver.value = data
        }, object : NetStateListener {
            override fun onStart() {
                loadingLiveData.value = LoadingState.LOADING_START
            }

            override fun onSuccess() {
                loadingLiveData.value = LoadingState.LOADING_STOP
            }

            override fun onFailure(e: Throwable) {
                loadingLiveData.value = LoadingState.LOADING_ERROR
                handlerNetException(e, errorLiveData)
            }
        })
    }


    override fun handlerNetException(
        e: Throwable,
        errorLiveData: MutableLiveData<WanAndroidResException>?
    ) {
        when (e) {
            is NetException -> {
                "NetException: $e".log("net--")
            }
            is WanAndroidResException -> {
                "WanAndroidResException: $e".log("net--")
            }
            else -> {
                "else: $e".log("net--")
            }
        }

    }

    /**
     * 业务层处理
     */
    private suspend fun <T> take(block: suspend () -> Response<BaseApiResponse<T>>): T {
        val baseRes: BaseApiResponse<T> = takeResponse(block)!!
        //过滤业务状态码
        return when (baseRes.errorCode) {
            0 -> {
                baseRes.data ?: throw WanAndroidResException(baseRes.errorCode, baseRes.errorMsg)
            }
            else -> {
                throw WanAndroidResException(baseRes.errorCode, baseRes.errorMsg)
            }
        }
    }


}