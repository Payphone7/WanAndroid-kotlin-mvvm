package com.wanandroid.xp_commom.widget

import android.content.Context
import android.util.AttributeSet
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener
import com.scwang.smart.refresh.layout.listener.OnRefreshListener

/**
 * create time : 2023/8/29 14:49
 * create by : xupengpeng
 */
class MySmartRefreshLayout(context: Context,attr:AttributeSet) : SmartRefreshLayout(context,attr) {
    init {
        setRefreshHeader(ClassicsHeader(context))
    }


    override fun setOnRefreshListener(listener: OnRefreshListener?): RefreshLayout {
        finishRefresh(3000)
        finishLoadMore(3000)
        return super.setOnRefreshListener(listener)
    }

    override fun setOnLoadMoreListener(listener: OnLoadMoreListener?): RefreshLayout {
        finishRefresh(3000)
        finishLoadMore(3000)
        return super.setOnLoadMoreListener(listener)
    }
}