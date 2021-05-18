package com.foundation.app.simple.param;


import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;

import com.foundation.app.af.fragment.BaseViewBindingFragment;
import com.foundation.app.af.utils.param.BundleParams;
import com.foundation.app.simple.AndroidVM;
import com.foundation.app.simple.databinding.FragUserInfoBinding;

/**
 * @Desc: -
 * -
 * create by zhusw on 5/18/21 09:40
 */
public class UserInfoFragment extends BaseViewBindingFragment<FragUserInfoBinding> {
    AndroidVM vm;

    @BundleParams()
    private int userId = 1;

    @BundleParams()
    private String userName = "none";

    @BundleParams("address")
    private UserAddress userAddress;

    @BundleParams("desc")
    private UserDesc userDesc;

    @Override
    public void initViewModel() {
        super.initViewModel();
        vm = getAppVM(AndroidVM.class);

    }

    @Override
    public void onViewCreated(@NonNull View view, @androidx.annotation.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        jViewBinding.auiTvUserId.setText(String.valueOf(userId));
        jViewBinding.auiTvUserName.setText(userName);
        jViewBinding.auiTvAddress.setText(userAddress.toString());
        jViewBinding.auiTvDesc.setText(userDesc.toString());
        vm.getImgLiveData().observe(getViewLifecycleOwner(), s -> {
            jViewBinding.auiTvVm.setText(s);
        });
    }
}
