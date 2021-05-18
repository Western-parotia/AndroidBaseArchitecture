package com.foundation.app.simple.param

import android.os.Bundle
import com.foundation.app.af.activity.BaseParamsActivity
import com.foundation.app.af.utils.ext.autoBind
import com.foundation.app.af.utils.param.BundleParams
import com.foundation.app.simple.R
import com.foundation.app.simple.databinding.ActUserInfoBinding


/**
 *@Desc:
 *-
 *-
 *create by zhusw on 5/17/21 17:11
 */
class UserInfoActivity : BaseParamsActivity() {
    val vbBinding by autoBind<ActUserInfoBinding>()

    @BundleParams
    private val userId: Int = 0

    @BundleParams
    private val userName: String = "none"

    @BundleParams("address")
    private val userAddress: UserAddress = UserAddress()

    @BundleParams("desc")
    private val userDesc: UserDesc = UserDesc()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vbBinding.auiTvUserId.text = userId.toString()
        vbBinding.auiTvUserName.text = userName

        vbBinding.auiTvAddress.text = userAddress.toString()
        vbBinding.auiTvDesc.text = userDesc.toString()

        vbBinding.auiBtn.setOnClickListener {
            val frag = UserInfoFragment()
            val bundle = Producer.create()
            frag.arguments = bundle
            supportFragmentManager.beginTransaction()
                .replace(R.id.aui_fl, frag, "UserInfoFragment")
                .commitNowAllowingStateLoss()
        }
    }

}