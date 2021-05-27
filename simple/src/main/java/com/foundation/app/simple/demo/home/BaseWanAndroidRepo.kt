package com.foundation.app.simple.demo.home

import androidx.lifecycle.MutableLiveData
import com.foundation.app.arc.utils.LoadingEvent
import com.foundation.app.simple.demo.entity.BaseApiResponse
import com.foundation.app.simple.demo.net.WanAndroidResException
import com.foundation.service.net.NetRepository
import kotlinx.coroutines.CoroutineScope
import retrofit2.Response

/**
 * create by zhusw on 5/25/21 17:26
 */
open abstract class BaseWanAndroidRepo(uiCoroutineScope: CoroutineScope) :
    NetRepository(uiCoroutineScope) {
    val loadingEventLiveData = MutableLiveData<LoadingEvent>()

    /**
     * 业务层处理
     */
    suspend fun <F> takeBusiness(block: suspend () -> Response<BaseApiResponse<F>>): F {
        val baseRes: BaseApiResponse<F> = takeResponse(block)!!
        //过滤业务状态码
        return when (baseRes.errorCode) {
            0 -> {
                baseRes.data ?: throw WanAndroidResException(-1, "无数据")
            }
            else -> {
                throw WanAndroidResException(baseRes.errorCode, baseRes.errorMsg)
            }
        }
    }
}