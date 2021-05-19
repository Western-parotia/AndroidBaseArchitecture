package com.foundation.app.simple.architecture

import android.os.Bundle
import com.foundation.app.arc.activity.BaseVMVBActivity

/**
 *@Desc:
 *-
 *-
 *create by zhusw on 5/18/21 18:38
 */
abstract class BaseActivity : BaseVMVBActivity() {
    override fun beforeSuperOnCreate(savedInstanceState: Bundle?) {

    }

    override fun afterSuperOnCreate(savedInstanceState: Bundle?) {

    }

    override fun initViewModel() {

    }
}