package com.foundation.service.net

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

/**
 * create by zhusw on 5/25/21 15:19
 */


open class NetRepository(val uiCoroutineScope: CoroutineScope) {

    fun netLaunch(block: suspend () -> Unit, state: NetStateListener? = null) {
        state?.onStart()
        uiCoroutineScope.launch {
            runCatching {
                block.invoke()
                state?.onSuccess()
            }.onFailure {
                if (NetManager.debug) {
                    it.printStackTrace()
                }
                withUI {
                    state?.onFailure(it)
                }
            }
        }
    }

    protected suspend fun <T> takeResponse(block: suspend () -> Response<T>): T? {
        val res = withIO(block) //Response<BaseApiResponse<List<BannerEntity>>>
        return when {
            res.isSuccessful -> {
                res.body()
            }
            else -> throw NetException(res)
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