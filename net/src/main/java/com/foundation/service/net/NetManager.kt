package com.foundation.service.net

import me.jessyan.retrofiturlmanager.RetrofitUrlManager
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.atomic.AtomicBoolean

/**
 * 网络管理器：init 之后 可获取 apiService,并对其进行缓存优化内存
 * 支持全局动态修改域名
 * @see [more_Introduction]("http://xx.com")
 * create by zhusw on 5/25/21 10:52
 */
object NetManager : IRetrofitUrlSkill {

    private val skill: IRetrofitUrlSkill by lazy {
        UrkSkill()
    }

    private lateinit var retrofit: Retrofit
    private val initState = AtomicBoolean(false)

    private val cacheMap by lazy {
        mutableMapOf<Class<*>, Any>()
    }

    /**
     * 不保证多线程安全，但是会以最早传入的对象为准
     * 参考 lazy(Atomic 模式)
     */
    fun init(retrofit: Retrofit) {
        if (!initState.get()) {
            initState.set(true)
            this.retrofit = retrofit
        }
    }

    fun <T : Any> getApiService(clz: Class<T>): T {
        return loadService(clz)
    }

    /**
     * 对运行时创建的service对象缓存
     */
    @Suppress("UNCHECKED_CAST")
    private fun <T : Any> loadService(clz: Class<T>): T {
        var service: Any? = cacheMap[clz]
        if (service == null) {
            synchronized(NetManager::class) {
                service = cacheMap[clz]
                if (service == null) {
                    service = retrofit.create(clz)
                    cacheMap[clz] = service as T
                }
            }
        }
        return service as T
    }


    override fun putDomain(domainKey: String, domainUrl: String) {
        skill.putDomain(domainKey, domainUrl)
    }

    override fun setGlobalDomain(domain: String) {
        skill.setGlobalDomain(domain)
    }

}

inline fun <reified T : Any> NetManager.getApiService(): T {
    return NetManager.getApiService(T::class.java)
}

fun OkHttpClient.Builder.addUrlSkill(): OkHttpClient.Builder {
    return RetrofitUrlManager.getInstance().with(this)
}

internal class UrkSkill : IRetrofitUrlSkill {
    override fun putDomain(domainKey: String, domainUrl: String) {
        RetrofitUrlManager.getInstance().putDomain(domainKey, domainUrl)
    }

    override fun setGlobalDomain(domain: String) {
        RetrofitUrlManager.getInstance().setGlobalDomain(domain)
    }


}