package com.foundation.app.simple.vm
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 *-模拟粘性事件
 *create by zhusw on 5/18/21 11:02
 */
class AppVM : ViewModel() {
    val data: MutableLiveData<Int> = MutableLiveData()

}