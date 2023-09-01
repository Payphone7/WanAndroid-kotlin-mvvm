package com.wanandroid.xp_project.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.launcher.ARouter
import com.dycw.xp_project.bean.Project
import com.wanandroid.xp_commom.base.BaseFragment
import com.wanandroid.xp_commom.network.BaseStateObserver
import com.wanandroid.xp_commom.utils.Constants
import com.wanandroid.xp_commom.utils.ToastUtil
import com.wanandroid.xp_project.R
import com.wanandroid.xp_project.adapter.ProjectItemClickListener
import com.wanandroid.xp_project.adapter.ProjectRvAdapter
import com.wanandroid.xp_project.databinding.FragmentProjectChildBinding
import com.wanandroid.xp_project.viewmodel.ProjectViewModel

/**
 * create time : 2023/9/1 11:02
 * create by : xupengpeng
 */
class ProjectChildFragment : BaseFragment<FragmentProjectChildBinding>(){

    private var currentPage = 1
    private var currentID: Int = 0
    private var currentIndex: Int = 0
    private var collectPosition: Int = -1

    private val mViewModel: ProjectViewModel by viewModels()
    private lateinit var mAdapter: ProjectRvAdapter
    private lateinit var mLayoutManager: LinearLayoutManager
    var list: MutableList<Project.ProjectDetail> = arrayListOf()


    companion object {
        private const val C_ID: String = "cid"
        private const val INDEX: String = "index"
        fun newInstance(cid: Int, index: Int): ProjectChildFragment {
            val f = ProjectChildFragment()
            val bundle = Bundle()
            bundle.putInt(C_ID, cid)
            bundle.putInt(INDEX, index)
            f.arguments = bundle
            return f
        }
    }

    override fun observer() {
        mViewModel.projectListLiveData.observe(this, object : BaseStateObserver<Project>() {

            override fun getRespDataSuccess(it: Project) {
                currentPage = it.curPage
                mBinding.refreshLayout.setNoMoreData(it.over)
                if (currentPage == 1) {
                    list.clear()
                    list.addAll(it.datas)
                    mAdapter.setData(list)
                    mLayoutManager.scrollToPosition(0)
                } else {
                    list.addAll(it.datas)
                    mAdapter.setData(list)
                }
                Log.d(TAG, "observe articleList: " + list.size)
            }
        })

        mViewModel.collectLiveData.observe(this, object : BaseStateObserver<String>() {
            override fun getRespSuccess() {
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
    }

    override fun init() {
        arguments?.apply {
            currentID = getInt(C_ID)
            currentIndex = getInt(INDEX)
        }


        mAdapter = ProjectRvAdapter(object : ProjectItemClickListener{
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
        mViewModel.getProList(currentPage + 1, currentID)
    }

    override fun getLayoutId() = R.layout.fragment_project_child



}