package com.foundation.app.simple.param;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.foundation.app.af.fragment.BaseViewBindingFragment;
import com.foundation.app.af.utils.param.BundleParams;
import com.foundation.app.simple.databinding.FragUserInfoBinding;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @Desc: -
 * -
 * create by zhusw on 5/18/21 09:40
 */
public class UserInfoFragment extends BaseViewBindingFragment {

    FragUserInfoBinding binding;

    @BundleParams()
    private int userId = 1;

    @BundleParams()
    private String userName = "none";

    @BundleParams("address")
    private UserAddress userAddress;

    @BundleParams("desc")
    private UserDesc userDesc;


    @Nullable
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragUserInfoBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @androidx.annotation.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.auiTvUserId.setText(String.valueOf(userId));
        binding.auiTvUserName.setText(userName);
        binding.auiTvAddress.setText(userAddress.toString());
        binding.auiTvDesc.setText(userDesc.toString());
    }
}
