package com.foundation.widget.loading

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.SparseArray
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation
import androidx.core.util.forEach

/**
 *@Desc:
 *-
 *-
 *create by zhusw on 5/7/21 14:57
 */
private const val ANIM_DURATION_LONG = 800L

open class NormalLoadingAdapter(private val context: Context) : PageLoadingAdapter {
    private val animCache: SparseArray<ObjectAnimator> = SparseArray()
    override fun showBackgroundImg(): Boolean = false

    override fun getBackground(): Drawable? = null

    override fun getLoadingView(): View? = null

    override fun getLoadingFailView(): View? = null

    override fun getLoadingFailEventView(): View? = null

    /**
     * 属性动画 默认是弱引用的，不必考虑释放问题
     */
    override fun onShowLoading(loadingView: View) {
        //今内部借用stop 处理，每次loading 都作为新都开始
        onStopLoading(loadingView)
        ObjectAnimator.ofFloat(loadingView, "rotationX", 0F, 200F).apply {
            repeatCount = Animation.INFINITE
            duration = ANIM_DURATION_LONG
            repeatMode = ValueAnimator.RESTART
            interpolator = AccelerateDecelerateInterpolator()
            start()
            animCache.put(animCache.size(), this)
        }
        ObjectAnimator.ofFloat(loadingView, "alpha", 0.2F, 0.8F).apply {
            repeatCount = Animation.INFINITE
            repeatMode = ValueAnimator.REVERSE
            duration = ANIM_DURATION_LONG
            start()
            animCache.put(animCache.size(), this)
        }
    }

    override fun onStopLoading(loadingView: View) {
        loadingView.animation?.cancel()
        animCache.forEach { _, value ->
            value.cancel()
        }
        animCache.clear()
    }
}