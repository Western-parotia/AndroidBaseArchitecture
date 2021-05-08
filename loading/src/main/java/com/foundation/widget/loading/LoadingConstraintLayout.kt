package com.foundation.widget.loading

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout

/**
 *@Desc:
 *- 主动或自动装载一个loading view 在最上层
 *- 如果在xml 主动加入了loading view，将不会再创建新的目标
 *create by zhusw on 5/7/21 15:08
 */
class LoadingConstraintLayout : ConstraintLayout {
    private var _loadingView: PageLoadingView? = null
    val loadingView get() = _loadingView!!

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet)

    override fun onFinishInflate() {
        super.onFinishInflate()
        //检查xml中的loading view
        for (i in 0..childCount) {
            val view = getChildAt(i)
            if (view is PageLoadingView) {
                _loadingView = view
                return
            }
        }
        if (null == _loadingView) {
            //将loadingView 加入布局顶部
            val clp = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
            _loadingView = PageLoadingView(context)
            _loadingView!!.let {
                it.visibility = View.GONE
                it.elevation = 2F.dp
                addView(it, clp)
            }
        }
    }

}
