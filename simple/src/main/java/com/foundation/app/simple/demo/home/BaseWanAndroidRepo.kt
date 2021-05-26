package com.foundation.app.simple.demo.home

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

    /**
     * 业务层处理
     */
    protected suspend fun <T> take(block: suspend () -> Response<BaseApiResponse<T>>): T {
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