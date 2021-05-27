package com.foundation.app.simple.demo.home

import com.foundation.app.simple.demo.net.api.WanAndroidService
import com.foundation.service.net.NetManager
import com.foundation.service.net.getApiService

/**
 * create by zhusw on 5/24/21 09:58
 */
class HomeRepo {
    val homeApi = NetManager.getApiService<WanAndroidService>()
}