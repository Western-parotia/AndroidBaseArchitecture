package com.foundation.app.simple.demo.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.foundation.app.simple.demo.net.WanAndroidResException
import com.foundation.service.net.NetLoadingEvent
import com.foundation.service.net.NetViewModel
import retrofit2.Response

/**
 * create by zhusw on 5/27/21 17:06
 */
open class BaseWanAndroidVM : NetViewModel() {

    protected val _loadEventLiveData: MutableLiveData<NetLoadingEvent> = MutableLiveData()
    val loadEventLiveData: LiveData<NetLoadingEvent> = _loadEventLiveData

    /**
     * 业务层处理
     */
    protected suspend fun <F> withBusiness(block: suspend () -> Response<BaseApiResponse<F>>): F {
        val baseRes: BaseApiResponse<F> = withResponse(block)!!
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