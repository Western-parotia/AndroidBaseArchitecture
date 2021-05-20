package com.foundation.app.simple.demo

import kotlinx.coroutines.CoroutineScope

/**
 * create by zhusw on 5/20/21 11:48
 */
abstract class BaseRepository(coroutineScope: CoroutineScope) {

    fun doWork(task: suspend () -> Unit) {

    }

    fun foo() {
        doWork { a() }

    }

    suspend fun a() {

    }

}