package com.wanandroid.xp_navigation.ui

import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.wanandroid.xp_commom.base.BaseFragment
import com.wanandroid.xp_navigation.adapter.NaviVPAdapter
import com.wanandroid.xp_navigation.R
import com.wanandroid.xp_navigation.databinding.FragmentNaviBinding

/**
 * create time : 2023/9/1 15:20
 * create by : xupengpeng
 */
class NaviFragment : BaseFragment<FragmentNaviBinding>() {

    private lateinit var titleList: MutableList<String>
    private lateinit var fragmentList: MutableList<Fragment>
    
    override fun observer() {

    }

    override fun init() {
        titleList = arrayListOf("导航","体系")
        fragmentList = arrayListOf(NavigationFragment(),SystemFragment())

        mBinding.viewPager.adapter = NaviVPAdapter(this, fragmentList)
        TabLayoutMediator(mBinding.tabLayout, mBinding.viewPager) { tab, position ->
            tab.text = titleList[position]
        }.attach()
    }

    override fun getLayoutId() = R.layout.fragment_navi
}