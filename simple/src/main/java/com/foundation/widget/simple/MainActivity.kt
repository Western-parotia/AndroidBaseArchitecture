package com.foundation.widget.simple

import android.content.Context
import android.graphics.drawable.Animatable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import com.foundation.widget.loading.NormalLoadingAdapter
import com.foundation.widget.loading.PageLoadingAdapter
import com.foundation.widget.simple.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.lc.loadingView.loadingAdapter = MyContentLoadingAdapter(this)
        binding.lv2.loadingAdapter = DiyLoading(this)

        binding.btnStart.setOnClickListener {
            binding.lv.showLoading()
            binding.lv2.showLoading()
            binding.lc.loadingView.showLoading()
        }
        binding.btnStop.setOnClickListener {
            binding.lv.stopLoading()
            binding.lv2.stopLoading()
            binding.lc.loadingView.stopLoading()
        }
        binding.btnShowFail.setOnClickListener {
            binding.lv.showLoadingFail(true)
            binding.lv2.showLoadingFail(true)
            binding.lc.loadingView.showLoadingFail(true)
        }
        binding.lc.loadingView.failViewClickListener = {
            Toast.makeText(MainActivity@ this, "lc fail click", Toast.LENGTH_LONG).show()
        }
        binding.lv.failViewClickListener = {
            Toast.makeText(MainActivity@ this, "lv fail click", Toast.LENGTH_LONG).show()
        }
        binding.btnShow.setOnClickListener {
            binding.lv.showLoadingState()
            binding.lc.loadingView.showLoadingState()
        }
    }

}

class MySingleLoadingAdapter(private val context: Context) : NormalLoadingAdapter(context) {
    override fun showBackgroundImg(): Boolean = true
    override fun getBackground(): Drawable? =
        ContextCompat.getDrawable(context, R.drawable.sp_loading_bg1)
}

class MyContentLoadingAdapter(private val context: Context) : NormalLoadingAdapter(context) {
    override fun showBackgroundImg(): Boolean = true
    override fun getBackground(): Drawable? {
        return ContextCompat.getDrawable(context, R.drawable.img_skeleton_screen)

    }
}

class DiyLoading(private val context: Context) : PageLoadingAdapter {
    private val loadingView = AppCompatImageView(context).apply {
        background = ContextCompat.getDrawable(context, R.drawable.dw_loading)
    }

    override fun showBackgroundImg(): Boolean = true

    override fun getBackground(): Drawable? {
        return ContextCompat.getDrawable(context, R.drawable.sp_loading_bg2)
    }

    override fun getLoadingFailView(): View? {
        return null
    }

    override fun getLoadingFailEventView(): View? {
        return null
    }

    override fun getLoadingView(): View = loadingView

    override fun onShowLoading(loadingView: View) {
        if (loadingView.background is Animatable) {
            "onShowLoading Animatable".log("PageLoadingView")
            val anim = loadingView.background as Animatable
            anim.start()
        }
    }

    override fun onStopLoading(loadingView: View) {
        if (loadingView.background is Animatable) {
            val anim = loadingView.background as Animatable
            anim.stop()
        }
    }

}

private fun String.log(secTag: String) {
    println("MainActivity $secTag $this")
}