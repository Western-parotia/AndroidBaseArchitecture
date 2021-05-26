package com.foundation.service.net

import com.foundation.service.net.utils.NetStateType
import com.foundation.service.net.utils.networkIsAvailable
import com.foundation.service.net.utils.transformHttpException
import kotlinx.coroutines.*
import retrofit2.Response

/**
 * create by zhusw on 5/25/21 15:19
 */
open class NetRepository(val uiCoroutineScope: CoroutineScope) {

    fun launch(block: suspend CoroutineScope.() -> Unit, state: NetStateListener? = null) {
        val exHandler = CoroutineExceptionHandler { _, throwable ->
            val transformThrowable = transformHttpException(throwable)
            state?.onFailure(transformThrowable)
        }
        uiCoroutineScope.launch(exHandler) {
            if (!networkIsAvailable(NetManager.app)) {
                throw NetException(NetStateType.CODE_NETWORK_OFF).apply {
                    netCode = NetStateType.CODE_NETWORK_OFF.value
                    netMsg = "network is unavailable"
                }
            }
            state?.onStart()
            block.invoke(this)
            state?.onSuccess()
        }
    }


    protected suspend fun <T> takeResponse(block: suspend () -> Response<T>): T? {
        val res = withIO(block) //Response<BaseApiResponse<List<YourData>>>
        return when {
            res.isSuccessful -> {
                res.body()
            }
            else -> throw NetException(NetStateType.CODE_RESPONSE_ERROR, res)
        }
    }

    suspend fun <T> withIO(block: suspend () -> T): T {
        return withContext(Dispatchers.IO) {
            block.invoke()
        }
    }

    suspend fun <T> withUI(block: suspend () -> T): T {
        return withContext(Dispatchers.Main) {
            block.invoke()
        }
    }
}