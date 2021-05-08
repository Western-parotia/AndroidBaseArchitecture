package com.foundation.widget.loading

import android.graphics.drawable.Drawable
import android.view.View

/**
 *@Desc:
 *-
 *-loading 适配器
 *create by zhusw on 5/7/21 09:37
 */
interface PageLoadingAdapter {
    fun showBackgroundImg(): Boolean
    fun getBackground(): Drawable?
    fun getLoadingView(): View?
    fun getLoadingFailView(): View?

    /**
     * 响应失败状态下的view点击
     */
    fun getLoadingFailEventView(): View?
    fun onShowLoading(loadingView: View)
    fun onStopLoading(loadingView: View)

}