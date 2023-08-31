package com.wanandroid.xp_home.fragment

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.launcher.ARouter
import com.dycw.xp_home.bean.ArticleBean
import com.dycw.xp_home.bean.BannerBean
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import com.wanandroid.xp_commom.base.BaseFragment
import com.wanandroid.xp_commom.network.BaseStateObserver
import com.wanandroid.xp_commom.utils.Constants
import com.wanandroid.xp_commom.utils.ToastUtil
import com.wanandroid.xp_home.R
import com.wanandroid.xp_home.adapter.HomeAdapter
import com.wanandroid.xp_home.adapter.HomeBannerAdapter
import com.wanandroid.xp_home.adapter.HomeItemClickListener
import com.wanandroid.xp_home.databinding.FragmentHomeBinding
import com.wanandroid.xp_home.viewmodel.HomeViewModel
import com.youth.banner.indicator.CircleIndicator

/**
 * create time : 2023/8/29 16:49
 * create by : xupengpeng
 */
open class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val mViewModel: HomeViewModel by viewModels()
    private lateinit var mAdapter: HomeAdapter
    private lateinit var mLayoutManager: LinearLayoutManager
    private lateinit var mBannerAdapter: HomeBannerAdapter
    var list: MutableList<ArticleBean.ArticleDetail> = arrayListOf()
    var collectPosition: Int = 0

    private var currentPage = 0


    override fun observer() {

        mBinding.refreshLayout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onRefresh(refreshLayout: RefreshLayout) {
                currentPage = 0
                mViewModel.getArticle(currentPage)
            }

            override fun onLoadMore(refreshLayout: RefreshLayout) {
                mViewModel.getArticle(currentPage + 1)
            }
        })

        mViewModel.bannerLiveData.observe(this, object : BaseStateObserver<List<BannerBean>>() {
            override fun getRespDataSuccess(it: List<BannerBean>) {
                super.getRespDataSuccess(it)
                mBannerAdapter.updateData(it)

            }
        })


        mViewModel.collectLiveData.observe(this,object : BaseStateObserver<String>(){
            override fun getRespDataSuccess(it: String) {
                super.getRespDataSuccess(it)
                if (list[collectPosition].collect) {
                    ToastUtil.instance.showMsg("取消收藏！")
                    list[collectPosition].collect = false
                } else {
                    ToastUtil.instance.showMsg("收藏成功！")
                    list[collectPosition].collect = true
                }
                mAdapter.notifyItemChanged(collectPosition)
            }
        })


        mViewModel.articleLiveData.observe(this,object : BaseStateObserver<ArticleBean>(){
            override fun getRespDataSuccess(it: ArticleBean) {
                super.getRespDataSuccess(it)
                it.apply {
                    currentPage = curPage - 1

                    //下拉刷新
                    if (currentPage == 0) {
                        list.clear()
                    }

                    //最后一页
                    mBinding.refreshLayout.setNoMoreData(over)

                    //list添加数据
                    list.addAll(it.datas)

                    //处理数据
                    if (currentPage == 0) {
                        mAdapter.setData(null)
                        mAdapter.setData(list)
                        mBinding.recyclerView.scrollToPosition(0)
                    } else {
                        mAdapter.setData(list)
                    }
                }
            }
        })

    }

    override fun init() {
        mAdapter = HomeAdapter(object : HomeItemClickListener {
            override fun onItemClick(position: Int) {
                val data = list[position]
                ARouter.getInstance()
                    .build(Constants.PATH_WEB)
                    .withString(Constants.WEB_LINK, data.link)
                    .withString(Constants.WEB_TITLE, data.title)
                    .navigation()
            }

            override fun onCollectClick(position: Int) {
                collectPosition = position
                if (list[collectPosition].collect) {
                    mViewModel.unCollect(list[collectPosition].id)
                } else {
                    mViewModel.collect(list[collectPosition].id)
                }
            }

        })

        mLayoutManager = LinearLayoutManager(mContext).apply {
            orientation = LinearLayoutManager.VERTICAL
        }
        mBinding.recyclerView.apply {
            layoutManager = mLayoutManager
            adapter = mAdapter
        }

        mBannerAdapter = HomeBannerAdapter(arrayListOf());
        mBinding.banner.isAutoLoop(true)
            .isAutoLoop(true)
            .setIndicator(CircleIndicator(mContext))
            .setAdapter(mBannerAdapter)


        getHomeData()
    }

    private fun getHomeData() {
        mViewModel.getBanner()
        mViewModel.getArticle(0)
    }

    override fun getLayoutId() = R.layout.fragment_home
}