package com.foundation.app.simple.demo.home

import androidx.lifecycle.MutableLiveData
import com.foundation.app.simple.demo.entity.BaseApiResponse
import com.foundation.app.simple.demo.home.data.BannerEntity
import com.foundation.app.simple.demo.net.BaseApiException
import com.foundation.app.simple.demo.net.RetrofitManager
import com.foundation.app.simple.demo.net.WanAndroidResException
import com.foundation.app.simple.demo.net.api.WanAndroidService
import com.foundation.app.simple.log
import kotlinx.coroutines.*
import retrofit2.Response

/**
 * create by zhusw on 5/24/21 09:58
 */
class HomeRepo(
    val uiCoroutineScope: CoroutineScope,
    val errorLiveData: MutableLiveData<WanAndroidResException>
) {

    val api = RetrofitManager.getApiService(WanAndroidService::class.java)

    fun getBanner(receiver: MutableLiveData<List<BannerEntity>>) {
        wiseLaunch {
            val data: List<BannerEntity> = filerBus {
                api.getBanner()
            }
            //继续
            receiver.value = data
        }

    }

    fun wiseLaunch(block: suspend () -> Unit): Job {
        return uiCoroutineScope.launch(coroutineExceptionHandler) {
            block.invoke()
        }
    }

    private val coroutineExceptionHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { context, throwable ->
            when (throwable) {
                is WanAndroidResException -> {
                    errorLiveData.postValue(throwable)
                }
                else -> {
                    "HomeRepo eror $throwable".log()
                }
            }
        }

    private suspend fun <T> filerBus(block: suspend () -> Response<BaseApiResponse<T>>): T {
        val baseRes = filterRes(block)
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

    private suspend fun <T> filterRes(block: suspend () -> Response<T>): T {
        val res = withIO(block) //Response<BaseApiResponse<List<BannerEntity>>>
        return when {
            res.isSuccessful -> {
                res.body() ?: throw BaseApiException(res)
            }
            else -> throw BaseApiException(res)
        }
    }

    suspend fun <T> withIO(block: suspend () -> T): T {
        return withContext(Dispatchers.IO) {
            block.invoke()
        }
    }
}