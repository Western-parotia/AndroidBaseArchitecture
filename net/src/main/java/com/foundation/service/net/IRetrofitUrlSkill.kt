package com.foundation.service.net

/**
 * create by zhusw on 5/25/21 10:52
 */
interface IRetrofitUrlSkill {
    fun putDomain(domainKey: String, domainUrl: String)
    fun setGlobalDomain(domain: String)
}