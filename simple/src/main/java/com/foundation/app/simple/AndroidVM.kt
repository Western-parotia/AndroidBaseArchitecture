package com.foundation.app.simple

import androidx.lifecycle.MutableLiveData
import com.foundation.app.af.vm.BaseViewModel

/**
 *@Desc:
 *-
 *-
 *create by zhusw on 5/18/21 11:02
 */
class AndroidVM : BaseViewModel() {
    val imgLiveData: MutableLiveData<String> = MutableLiveData()
}