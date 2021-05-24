package com.foundation.app.arc.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**

 *-
 *-
 *create by zhusw on 5/17/21 11:36
 */
abstract class BaseViewModel<T> : ViewModel() {
    protected val _errorLiveData = MutableLiveData<T>()
    val errorLiveData: LiveData<T> = _errorLiveData

}