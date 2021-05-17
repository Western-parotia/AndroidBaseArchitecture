package com.foundation.app.af.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/**
 *@Desc:
 *-
 *-
 *create by zhusw on 5/17/21 11:36
 */
class BaseViewModel : ViewModel() {

    fun foo() {

        viewModelScope.launch {

        }
    }
}