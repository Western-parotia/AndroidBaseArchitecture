package com.foundation.app.simple.ui

import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.foundation.app.arc.utils.param.BundleParams
import com.foundation.app.simple.R
import com.foundation.app.simple.architecture.BaseActivity
import com.foundation.app.simple.databinding.ActUserInfoBinding
import com.foundation.app.simple.ui.data.BundleProducer


/**
 *@Desc:
 *-
 *-
 *create by zhusw on 5/17/21 17:11
 * scheme://xxxd/?k-v
 *
 */
class UserInfoActivity : BaseActivity() {

    @BundleParams("userId")
    private val userId: Int = 0

    @BundleParams("userId")
    private val userName: String = "none"

    private val vbBinding by initVB<ActUserInfoBinding>()

    override fun getContentVB(): ViewBinding = vbBinding

    override fun init(savedInstanceState: Bundle?) {

        vbBinding.auiBtn.setOnClickListener {
            val frag = UserInfoFragment()
            val bundle = BundleProducer.create()
            frag.arguments = bundle
            supportFragmentManager.beginTransaction()
                .replace(R.id.aui_fl, frag, "UserInfoFragment")
                .commitNowAllowingStateLoss()
        }
    }

    override fun bindData() {
        vbBinding.auiTvUserId.text = userId.toString()
        vbBinding.auiTvUserName.text = userName
    }


}