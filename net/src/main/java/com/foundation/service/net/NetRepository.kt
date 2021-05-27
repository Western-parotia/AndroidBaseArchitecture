package com.foundation.service.net

import android.os.Looper
import com.foundation.service.net.utils.networkIsAvailable
import com.foundation.service.net.utils.transformHttpException
import kotlinx.coroutines.*
import retrofit2.Response

/**
 * 网络加载库，基于协程
 * 必须提供一个协程域对象，如果在viewModel中使用，传入
 * 提供网络加载状态监听 与异常过滤
 * create by zhusw on 5/25/21 15:19
 */
open class NetRepository(val coroutineScope: CoroutineScope) {

    /**
     * block 作为匿名协程拓展，具备包含子协程的能力。
     * 但应该完全避免其中包含独立协程（任何情况下，都不应该使用独立协程嵌套，
     * 完全没必要反而会丧失对"子"协程的控制）
     * [NetStateListener] 作为状态监听器，通常你应该自己实现一个子类，
     * 统一处理状态并贴合业务逻辑
     */
    fun launch(block: suspend CoroutineScope.() -> Unit, state: NetStateListener? = null) {
        val exHandler = CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
            val transformThrowable = transformHttpException(throwable)
            state?.onFailure(transformThrowable)
        }
        coroutineScope.launch(exHandler) {
            if (Looper.myLooper() != Looper.getMainLooper()) {
                throw IllegalAccessException("coroutineScope $coroutineScope 不可以使用后台线程调度器作为根协程的调度器")
            }
            if (!networkIsAvailable(NetManager.app)) {
                throw NetException(NetLinkErrorType.CODE_NETWORK_OFF).apply {
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
            else -> throw NetException(NetLinkErrorType.CODE_RESPONSE_ERROR, res)
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