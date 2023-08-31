package com.wanandroid.xp_commom.widget

import android.content.Context
import android.util.AttributeSet
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener
import com.scwang.smart.refresh.layout.listener.OnRefreshListener
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener

/**
 * create time : 2023/8/29 14:49
 * create by : xupengpeng
 */
class MySmartRefreshLayout(context: Context,attr:AttributeSet) : SmartRefreshLayout(context,attr) {
    init {
        setRefreshHeader(ClassicsHeader(context))
        setRefreshFooter(ClassicsFooter(context))
    }

    override fun setStateRefreshing(notify: Boolean) {
        super.setStateRefreshing(notify)
        finishRefreshOrLoadMore(1500)
    }

    override fun setStateDirectLoading(triggerLoadMoreEvent: Boolean) {
        super.setStateDirectLoading(triggerLoadMoreEvent)
        finishRefreshOrLoadMore(1500)
    }

    override fun moveSpinnerInfinitely(spinner: Float) {
        super.moveSpinnerInfinitely(spinner)
        finishRefreshOrLoadMore(1500)
    }



    fun finishRefreshOrLoadMore(delay:Int){
        finishRefresh(delay)
        finishLoadMore(delay)
    }
}