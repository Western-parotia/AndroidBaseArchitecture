package com.foundation.app.simple

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.foundation.app.af.BuildConfig
import com.foundation.app.af.extensions.fastBind
import com.foundation.app.af.fragment.ViewBindingLifecycleListener
import com.foundation.app.af.fragment.fastBind

/**
 *@Desc:
 *-
 *-
 *create by zhusw on 5/11/21 14:42
 */

class VBActivity : AppCompatActivity() {
    val actVbBinding by fastBind<ActVbBindingCopy>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.test1)
        supportFragmentManager.beginTransaction()
            .replace(R.id.specialFrameLayout, MyFragment(), "MyFragment")
            .commitNowAllowingStateLoss()
    }
}

class SpecialFrameLayout(context: Context, attributeSet: AttributeSet) :
    FrameLayout(context, attributeSet)

class MyFragment : Fragment(R.layout.act_vb), ViewBindingLifecycleListener {
    val actVbBinding by fastBind<ActVbBindingCopy>()

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
        actVbBinding.btn
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        "5 onActivityCreated Fragment state ${viewLifecycleOwner.lifecycle.currentState}".log()//state INITIALIZED
        actVbBinding.btn
    }

    override fun onStart() {
        super.onStart()
        "6 onStart Fragment state ${viewLifecycleOwner.lifecycle.currentState}".log() //state CREATED
        actVbBinding.btn
    }

    override fun onResume() {
        super.onResume()
        "7 onResume Fragment state ${viewLifecycleOwner.lifecycle.currentState}".log()//state STARTED
        actVbBinding.btn.setOnClickListener {
            Toast.makeText(activity, "dddd", Toast.LENGTH_LONG).show()
        }
    }

    override fun onPause() {
        super.onPause()
        "onPause Fragment state ${viewLifecycleOwner.lifecycle.currentState}".log() //state STARTED
        actVbBinding.btn
    }

    override fun onStop() {
        super.onStop()
        "onStop Fragment state ${viewLifecycleOwner.lifecycle.currentState}".log() //state CREATED
        actVbBinding.btn
    }

    override fun onViewBindingDestroy() {
        super.onViewBindingDestroy()
        actVbBinding.btn
        "onViewBindingDestroy Fragment state ${viewLifecycleOwner.lifecycle.currentState}".log()
    }

    //viewBinding 已销毁
    override fun onDestroyView() {
        super.onDestroyView()
        actVbBinding.btn
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
