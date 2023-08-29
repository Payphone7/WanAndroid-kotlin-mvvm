package com.wanandroid.xp_commom.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager
import java.util.jar.Attributes

/**
 * create time : 2023/8/29 16:29
 * create by : xupengpeng
 */
class NoScrollViewPager(context: Context,attr: AttributeSet) : ViewPager(context,attr) {
    var scrollable = false

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return if (scrollable){
            super.onInterceptTouchEvent(ev)
        }else{
            false
        }
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return if (scrollable){
            super.onTouchEvent(ev)
        }else{
            true
        }
    }

    override fun isInEditMode(): Boolean {
        return true
    }

    fun setScrollAble(scroll: Boolean){
        scrollable = scroll
    }
}