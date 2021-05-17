package com.foundation.app.simple

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.foundation.app.af.BuildConfig
import com.foundation.app.af.utils.ext.ViewBindingLifecycleListener
import com.foundation.app.af.utils.ext.autoBind
import com.foundation.app.simple.databinding.ActVbBinding
import com.foundation.app.simple.param.UserAddress
import com.foundation.app.simple.param.UserDesc
import com.foundation.app.simple.param.UserInfoActivity

/**
 *@Desc:
 *-
 *-
 *create by zhusw on 5/11/21 14:42
 */

class VBActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction()
            .replace(android.R.id.content, MyFragment(), "MyFragment")
            .commitNowAllowingStateLoss()
    }
}

/**
 * 限制子类必须传布局id
 */
open class MyBaseFragment(@LayoutRes id: Int) : Fragment(id) {

}

class MyFragment : MyBaseFragment(R.layout.act_vb), ViewBindingLifecycleListener {
    val actVbBinding by autoBind<ActVbBinding>()
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
        actVbBinding.btn.setOnClickListener {
            val intent = Intent(activity, UserInfoActivity::class.java)
            intent.putExtra("userId", 2)
            intent.putExtra("userName", "DogGi")
            val address = UserAddress()
            val desc = UserDesc()
            intent.putExtra("address", address)
            intent.putExtra("desc", desc)
            startActivity(intent)
        }
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


private const val TAG = "baseAF"
fun String.log(secTag: String = "") {
    if (BuildConfig.DEBUG) {
        println("$TAG $secTag $this")
    }
}
