package com.foundation.app.simple.demo.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.foundation.app.arc.vm.BaseViewModel
import com.foundation.app.simple.demo.home.data.BannerEntity
import com.foundation.app.simple.demo.net.RetrofitManager
import com.foundation.app.simple.demo.net.api.WanAndroidService
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 *
 */
class HomeVM : BaseViewModel() {

    val bannerData = MutableLiveData<List<BannerEntity>>()

    fun loadBanner() {

        viewModelScope.launch(ex) {
            val data = withContext(Dispatchers.Default) {
                RetrofitManager.getApiService(WanAndroidService::class.java)
                    .getBanner()
                    .formatData()
            }
            bannerData.value = data
        }
    }

    private val ex = CoroutineExceptionHandler { coroutineContext, throwable ->
        errorMsg.value = "请求发生错误:$throwable"
    }
}