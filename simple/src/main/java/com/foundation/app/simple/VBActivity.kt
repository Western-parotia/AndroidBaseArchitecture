package com.foundation.app.simple

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.foundation.app.af.BuildConfig
import com.foundation.app.af.activity.BaseViewBindingFragment
import com.foundation.app.simple.databinding.ActVbBinding

/**
 *@Desc:
 *-
 *-
 *create by zhusw on 5/11/21 14:42
 */

class VBActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

}

class MyFragment : BaseViewBindingFragment<ActVbBinding>() {
    override fun onResume() {
        super.onResume()
    }
}


private const val TAG = "baseAF"
fun String.log(secTag: String = "") {
    if (BuildConfig.DEBUG) {
        println("$TAG $secTag $this")
    }
}
