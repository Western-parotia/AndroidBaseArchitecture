package com.foundation.app.simple.param

import android.os.Bundle
import com.foundation.app.af.activity.BaseVMActivity
import com.foundation.app.af.utils.ext.autoBind
import com.foundation.app.af.utils.param.BundleParams
import com.foundation.app.simple.AndroidVM
import com.foundation.app.simple.R
import com.foundation.app.simple.databinding.ActUserInfoBinding


/**
 *@Desc:
 *-
 *-
 *create by zhusw on 5/17/21 17:11
 * scheme://xxxd/?k-v
 *
 */
class UserInfoActivity : BaseVMActivity() {

    @BundleParams
    private val userId: Int = 0

    @BundleParams
    private val userName: String = "none"

    @BundleParams("address")
    private val userAddress: UserAddress = UserAddress()

    @BundleParams("desc")
    private val userDesc: UserDesc = UserDesc()

    private val vbBinding by autoBind<ActUserInfoBinding>()
    private val vm by lazyAppVM<AndroidVM>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.imgLiveData.observe(this, {
            vbBinding.auiVm.text = it
        })
        vbBinding.auiVm.setOnClickListener {
            vm.imgLiveData.value = "activity update img${System.currentTimeMillis()}"
        }

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