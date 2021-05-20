package com.foundation.app.arc.vm
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 *@Desc:
 *-
 *-
 *create by zhusw on 5/17/21 11:36
 */
abstract class BaseViewModel : ViewModel() {
    val errorMsg = MutableLiveData<String>()
}