package com.foundation.service.net

import kotlinx.coroutines.*
import retrofit2.Response

/**
 * create by zhusw on 5/25/21 15:19
 */
open class NetRepository(private val uiCoroutineScope: CoroutineScope) {


    fun launch(block: suspend () -> Unit): Job {
        return uiCoroutineScope.launch { block() }
    }

    protected suspend fun <T> takeResponse(block: suspend () -> Response<T>): T {
        val res = withIO(block) //Response<BaseApiResponse<List<BannerEntity>>>
        return when {
            res.isSuccessful -> {
                res.body() ?: throw NetException(res)
            }
            else -> throw NetException(res)
        }
    }

    protected suspend fun <T> withIO(block: suspend () -> T): T {
        return withContext(Dispatchers.IO) {
            block.invoke()
        }
    }

}