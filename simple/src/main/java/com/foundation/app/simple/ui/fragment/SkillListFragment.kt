package com.foundation.app.simple.ui.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.foundation.app.simple.R
import com.foundation.app.simple.architecture.BaseFragment2
import com.foundation.app.simple.databinding.ActVbBinding
import com.foundation.app.simple.demo.home.HomeActivity
import com.foundation.app.simple.jump
import com.foundation.app.simple.log
import com.foundation.app.simple.ui.MultiFragmentVisibleTestActivity
import com.foundation.app.simple.ui.SingleFragmentVisibleTestActivity
import com.foundation.app.simple.ui.StickyLiveDataActivity
import com.foundation.app.simple.ui.UserInfoActivity

/**
 *@Desc:
 *-
 *-
 *create by zhusw on 5/19/21 13:32
 */
class SkillListFragment : BaseFragment2(R.layout.act_vb) {

    val actVbBinding by initVB<ActVbBinding>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        "1 onAttach".log()
//        "onAttach  onCreateView 前 不能访问 ${viewLifecycleOwner.lifecycle.currentState}".log()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        "2 onCreate ".log()
//        "onCreate onCreateView 前 不能访问 ${viewLifecycleOwner.lifecycle.currentState}".log()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        "4 onViewCreated Fragment state ${viewLifecycleOwner.lifecycle.currentState}".log()//state INITIALIZED
    }

    override fun init(savedInstanceState: Bundle?) {
        "init savedInstanceState".log()
        actVbBinding.btn.setOnClickListener {
            val intent = Intent(activity, UserInfoActivity::class.java)
            intent.putExtra("userId", 2)
            intent.putExtra("userName", "DogGi")
            //不支持传递实体
//            val address = UserAddress("beijing", 99)
//            val desc = UserDesc("friendly", 170)
//            intent.putExtra("address", address)
//            intent.putExtra("desc", desc)
            startActivity(intent)
        }
        actVbBinding.btnSingleVisible.setOnClickListener {
            jump(SingleFragmentVisibleTestActivity::class.java)
        }
        actVbBinding.btnViewPageVisible.setOnClickListener {
            jump(MultiFragmentVisibleTestActivity::class.java)
        }
        actVbBinding.btnVm.setOnClickListener {
            jump(HomeActivity::class.java)
        }
        actVbBinding.btnLd.setOnClickListener {
            jump(StickyLiveDataActivity::class.java)
        }

    }

    override fun bindData() {
        "init observeData".log()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        "5 onActivityCreated Fragment state ${viewLifecycleOwner.lifecycle.currentState}".log()//state INITIALIZED

    }

    override fun onStart() {
        super.onStart()
        "6 onStart Fragment state ${viewLifecycleOwner.lifecycle.currentState}".log() //state CREATED

    }

    override fun onResume() {
        super.onResume()
        "7 onResume Fragment state ${viewLifecycleOwner.lifecycle.currentState}".log()//state STARTED
    }

    override fun onPause() {
        super.onPause()
        "onPause Fragment state ${viewLifecycleOwner.lifecycle.currentState}".log() //state STARTED

    }

    override fun onStop() {
        super.onStop()
        "onStop Fragment state ${viewLifecycleOwner.lifecycle.currentState}".log() //state CREATED
    }

    override fun onViewBindingDestroy() {
        super.onViewBindingDestroy()
        "onViewBindingDestroy Fragment state ${viewLifecycleOwner.lifecycle.currentState}".log()
    }

    //viewBinding 已销毁
    override fun onDestroyView() {
        super.onDestroyView()
        "onDestroyView Fragment state ${viewLifecycleOwner.lifecycle.currentState}".log()
//        "onDestroyView actVbBinding = ${actVbBinding.btn.text}".log("")

    }

    //view已销毁
    override fun onDestroy() { // Can't access the Fragment View's LifecycleOwner when getView() is null i.e., before onCreateView() or after onDestroyView()
        super.onDestroy()
        "onDestroy Fragment".log()
//        "onDestroy Fragment state ${viewLifecycleOwner.lifecycle.currentState}".log()
    }

    override fun onDetach() {// Can't access the Fragment View's LifecycleOwner when getView() is null i.e., before onCreateView() or after onDestroyView()
        super.onDetach()
        "onDetach Fragment ".log()
//        "onDetach Fragment state ${viewLifecycleOwner.lifecycle.currentState}".log()
    }


}