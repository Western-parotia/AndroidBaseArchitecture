package com.foundation.app.simple.demo.net

/**
 * create by zhusw on 5/20/21 14:30
 */
class BaseApiException(val errorMessage: String, val errorCode: Int) : Throwable() {
}