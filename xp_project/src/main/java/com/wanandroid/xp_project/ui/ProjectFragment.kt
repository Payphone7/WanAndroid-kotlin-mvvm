package com.wanandroid.xp_project.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dycw.xp_project.bean.ProjectType
import com.google.android.material.tabs.TabLayoutMediator
import com.wanandroid.xp_commom.base.BaseFragment
import com.wanandroid.xp_commom.network.BaseStateObserver
import com.wanandroid.xp_commom.utils.ToastUtil
import com.wanandroid.xp_project.databinding.FragmentProjectBinding
import com.wanandroid.xp_project.R
import com.wanandroid.xp_project.adapter.ProjectVpAdapter
import com.wanandroid.xp_project.viewmodel.ProjectViewModel

/**
 * create time : 2023/9/1 10:44
 * create by : xupengpeng
 */
class ProjectFragment : BaseFragment<FragmentProjectBinding>() {

    private val projectViewModel: ProjectViewModel by viewModels()
    private var titleList: MutableList<String> = arrayListOf()
    private var fragmentList: MutableList<Fragment> = arrayListOf()

    override fun observer() {
        projectViewModel.projectTypeLiveData.observe(this, object : BaseStateObserver<List<ProjectType>>() {
            override fun getRespDataSuccess(it: List<ProjectType>) {
                initTab(it)
            }
        })
    }

    private fun initTab(list: List<ProjectType>) {
        for (i in list.indices) {
            titleList.add((i + 1).toString() + "." + list[i].name)
            fragmentList.add(ProjectChildFragment.newInstance(list[i].id, i))
        }
        mBinding.viewPager.offscreenPageLimit = 5
        mBinding.viewPager.adapter = ProjectVpAdapter(this, fragmentList)
        TabLayoutMediator(mBinding.tabLayout, mBinding.viewPager) { tab, position ->
            tab.text = titleList[position]
        }.attach()
    }

    override fun init() {
        projectViewModel.getProjectType()

    }

    override fun getLayoutId() = R.layout.fragment_project



}