package com.foundation.app.simple.demo.home

import androidx.lifecycle.MutableLiveData
import com.foundation.app.simple.demo.entity.BaseApiResponse
import com.foundation.app.simple.demo.net.WanAndroidResException
import com.foundation.service.net.NetRepository
import kotlinx.coroutines.CoroutineScope
import retrofit2.Response

/**
 * create by zhusw on 5/25/21 17:26
 */
open abstract class BaseWanAndroidRepo<C>(uiCoroutineScope: CoroutineScope) :
    NetRepository(uiCoroutineScope) {
    val loadingState = MutableLiveData<C>()

    /**
     * 业务层处理
     */
    protected suspend fun <F> take(block: suspend () -> Response<BaseApiResponse<F>>): F {
        val baseRes: BaseApiResponse<F> = takeResponse(block)!!
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