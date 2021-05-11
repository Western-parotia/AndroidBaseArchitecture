package com.foundation.app.simple

import android.content.Context
import android.graphics.drawable.Animatable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import com.foundation.widget.loading.PageLoadingAdapter
import com.foundation.widget.simple.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.contentLoading.setLoadingAdapter(MyContentLoadingAdapter(this))
        binding.contentLoading.failViewEventListener = { _: View, type: Int, extra: Any? ->
            Toast.makeText(
                this,
                "contentLoading fail clikc type:$type extra:$extra",
                Toast.LENGTH_LONG
            ).show()
            binding.normalLoading.showLoading(false)
            binding.contentLoading.showLoading(true)
        }

        binding.btnStart.setOnClickListener {
            binding.normalLoading.showLoading(false)
            binding.contentLoading.showLoading(true)
        }
        binding.btnStop.setOnClickListener {
            binding.normalLoading.stop()
            binding.contentLoading.stop()
        }
        binding.btnShowFail.setOnClickListener {
            binding.normalLoading.showLoadingFail()
            binding.contentLoading.showLoadingFail(false, 0, "额外参数")
        }

        binding.normalLoading.failViewEventListener = { _: View, _: Int, _: Any? ->
            Toast.makeText(this, "lv fail click", Toast.LENGTH_LONG).show()
        }
        binding.btnShow.setOnClickListener {
            binding.normalLoading.checkLoadingState()
            binding.contentLoading.checkLoadingState()
        }
    }

}

/**
 * 自定义loading逻辑
 */
class MyContentLoadingAdapter(private val context: Context) : PageLoadingAdapter {

    /**
     * 设置骨架图
     */
    override fun getBottomPlateView(): View = AppCompatImageView(context).apply {
        layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        background = ContextCompat.getDrawable(context, R.drawable.img_skeleton_screen)
    }

    /**
     * 自定义加载动画的View
     */
    private val loadingView = AppCompatImageView(context).apply {
        background = ContextCompat.getDrawable(context, R.drawable.dw_loading)
    }

    override fun getLoadingView(): View? = loadingView

    /**
     * 展示动画
     */
    override fun onShowLoading(loadingView: View) {
        if (loadingView.background is Animatable) {
            val anim = loadingView.background as Animatable
            anim.start()
        }
    }

    /**
     * 设置失败展示
     */
    override fun getLoadingFailView(): View? = LayoutInflater
        .from(context)
        .inflate(R.layout.fail, null).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
            )
        }

    /**
     * 设置失败响应事件
     */
    override fun onShowFail(
        failView: View,
        type: Int,
        extra: Any?,
        failViewEvent: (view: View, type: Int, extra: Any?) -> Unit
    ) {
        failView.findViewById<View>(R.id.btn).setOnClickListener {
            failViewEvent.invoke(failView, type, extra)
        }
    }

    /**
     * 停止动画
     */
    override fun onStop(loadingView: View?, failView: View?) {
        if (loadingView?.background is Animatable) {
            val anim = loadingView.background as Animatable
            anim.stop()
        }
    }

}

private fun String.log(secTag: String) {
    println("MainActivity $secTag $this")
}