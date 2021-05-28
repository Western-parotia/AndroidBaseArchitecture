package com.foundation.service.net

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foundation.service.net.utils.log
import com.foundation.service.net.utils.networkIsAvailable
import com.foundation.service.net.utils.transformHttpException
import kotlinx.coroutines.*
import retrofit2.Response

/**
 * 网络加载状态监听
 * 异常捕获与分类
 * create by zhusw on 5/25/21 15:19
 */
open class NetViewModel : ViewModel() {
    private val TAG = "NetViewModel"
    protected val _loadEventLiveData = MutableLiveData<NetLoadingEvent>()
    val loadEventLiveData: LiveData<NetLoadingEvent> = _loadEventLiveData

    /**
     * block 作为匿名协程拓展，具备包含子协程的能力
     * 但应该完全避免其中包含独立协程（任何情况下，都不应该使用独立协程嵌套，
     * 完全没必要反而会丧失对"子"协程的控制）
     * [NetStateListener] 作为状态监听器，通常你应该自己实现一个子类，
     * 统一处理状态满足匹配UI展示需要
     *
     * 异常捕获：不管是 withContext(IO) 还是 async(IO) 中发生的异常，最终都会
     * 在根协程的线程环境获取到异常信息。赞！
     *
     */
    fun netLaunch(
        block: suspend CoroutineScope.() -> Unit,
        state: NetStateListener? = null,
        tag: String = ""
    ) {
        val exHandler = CoroutineExceptionHandler { ctx, throwable ->
            throwable.printStackTrace()
            val name: String? = ctx[CoroutineName.Key]?.name
            "$throwable ,ctxName:$name ,thread:${Thread.currentThread().name}".log(TAG)
            val transformThrowable = transformHttpException(throwable)
            state?.onFailure(transformThrowable)
        }
        val ctx = if (tag.isNotEmpty()) {
            exHandler + CoroutineName(tag)
        } else {
            exHandler
        }
        viewModelScope.launch(ctx) {
            if (!networkIsAvailable(NetManager.app)) {
                throw NetException.createNetWorkType("网络链接不可用")
            }
            state?.onStart()
            block.invoke(this)
            state?.onSuccess()
        }
    }


    protected suspend fun <T> withResponse(block: suspend () -> Response<T>): T? {
        val res = withIO(block) //Response<BaseApiResponse<List<YourData>>>
        return when {
            res.isSuccessful -> {
                res.body()
            }
            else -> throw NetException.createResponseType(res)
        }
    }

    //需要测试异常线程
    protected suspend fun <T> withIO(block: suspend () -> T): T {
        return withContext(Dispatchers.IO) {//异常信息 将在根协程的线程环境捕获
            block.invoke()
        }
    }

    protected suspend fun <T> withUI(block: suspend () -> T): T {
        return withContext(Dispatchers.Main) {
            block.invoke()
        }
    }
}