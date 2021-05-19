package com.foundation.app.simple.vm
import androidx.lifecycle.MutableLiveData
import com.foundation.app.arc.vm.BaseViewModel

/**
 *@Desc:
 *-
 *-
 *create by zhusw on 5/18/21 11:02
 */
class AndroidVM : BaseViewModel() {
    val imgLiveData: MutableLiveData<String> = MutableLiveData()

    fun setUrl(str: String) {
        imgLiveData.value = str
    }
}