package com.foundation.app.simple.ui

import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.foundation.app.arc.utils.param.BundleParams
import com.foundation.app.arc.utils.param.BundleParamsUseSerializable
import com.foundation.app.simple.R
import com.foundation.app.simple.architecture.BaseActivity
import com.foundation.app.simple.databinding.ActUserInfoBinding
import com.foundation.app.simple.ui.data.BundleProducer


/**

 *-
 *-
 *create by zhusw on 5/17/21 17:11
 * scheme://xxxd/?k-v
 *
 */
class UserInfoActivity : BaseActivity() {

    @BundleParams("userId")
    private val userId: Int = 0

    @BundleParams("userName")
    private val userName: String = "none"

    @BundleParamsUseSerializable
    @BundleParams("clsTest")
    private val cls: Class<*> = Any::class.java

    private val vbBinding by lazyVB<ActUserInfoBinding>()

    override fun getContentVB(): ViewBinding = vbBinding

    override fun init(savedInstanceState: Bundle?) {

        vbBinding.auiBtn.setOnClickListener {
            val frag = UserInfoFragment()
            val bundle = BundleProducer.create()
            frag.arguments = bundle
            switchFragment(frag, R.id.aui_fl, "UserInfoFragment")
        }
    }

    override fun bindData() {

        vbBinding.auiTvUserId.text = "用户ID: $userId"
        vbBinding.auiTvUserName.text = "用户名称: $userName"
    }

}
