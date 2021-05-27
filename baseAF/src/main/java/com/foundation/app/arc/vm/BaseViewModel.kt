package com.foundation.app.arc.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.foundation.app.arc.utils.LoadingEvent

/**
 *-
 *-
 *create by zhusw on 5/17/21 11:36
 */
abstract class BaseViewModel : ViewModel() {
    protected val _loadEventLiveData = MutableLiveData<LoadingEvent>()
    val loadEventLiveData: LiveData<LoadingEvent> = _loadEventLiveData
}