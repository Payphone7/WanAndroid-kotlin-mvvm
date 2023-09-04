package com.wanandroid.xp_navigation.ui

import android.annotation.SuppressLint
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.launcher.ARouter
import com.wanandroid.xp_commom.base.BaseFragment
import com.wanandroid.xp_commom.network.BaseStateObserver
import com.wanandroid.xp_commom.utils.Constants
import com.wanandroid.xp_navigation.R
import com.wanandroid.xp_navigation.adapter.NaviFirstAdapter
import com.wanandroid.xp_navigation.adapter.NaviFirstItemClickListener
import com.wanandroid.xp_navigation.adapter.NaviItemClickListener
import com.wanandroid.xp_navigation.adapter.NaviItemEvent
import com.wanandroid.xp_navigation.adapter.NaviRVAdapter
import com.wanandroid.xp_navigation.bean.NavigationBean
import com.wanandroid.xp_navigation.databinding.FragmentSystemBinding
import com.wanandroid.xp_navigation.viewmodel.NaviViewModel

/**
 * create time : 2023/9/1 15:32
 * create by : xupengpeng
 */
class NavigationFragment : BaseFragment<FragmentSystemBinding>() {

    private val mViewModel by viewModels<NaviViewModel> ()
    private lateinit var mAdapter : NaviRVAdapter
    private var mSysList :List<NavigationBean> = arrayListOf()

    private lateinit var mFirstAdapter: NaviFirstAdapter

    override fun observer() {
        mViewModel.navigationLiveData.observe(this, object : BaseStateObserver<List<NavigationBean>>() {
            override fun getRespDataSuccess(it: List<NavigationBean>) {
                mSysList = it
                mAdapter.setData(mSysList)
                mFirstAdapter.setData(mSysList)
                setSelect(0)
            }
        })
    }

    override fun init() {
        val manager = LinearLayoutManager(mContext)
        mAdapter = NaviRVAdapter (object : NaviItemClickListener{
            override fun onClick(e: NaviItemEvent) {
                val data = mSysList[e.position1].articles[e.position2]
                ARouter.getInstance()
                    .build(Constants.PATH_WEB)
                    .withString(Constants.WEB_LINK, data.link)
                    .withString(Constants.WEB_TITLE, data.title)
                    .navigation()
            }
        })
        mBinding.recyclerView2.apply {
            layoutManager = manager
            adapter = mAdapter
        }

        val firstLayoutManager = LinearLayoutManager(mContext)
        mFirstAdapter = NaviFirstAdapter(object : NaviFirstItemClickListener{
            override fun onFirstItemClick(position: Int) {
                setSelect(position)
                manager.scrollToPositionWithOffset(position,0)
            }
        })
        mBinding.recyclerView1.apply {
            layoutManager = firstLayoutManager
            adapter = mFirstAdapter
        }



        mViewModel.getNavigation()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setSelect(position: Int){
        for (i in mSysList.indices){
            mSysList[i].select = position == i
        }
        mFirstAdapter.notifyDataSetChanged()
    }

    override fun getLayoutId() = R.layout.fragment_system
}