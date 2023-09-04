package com.wanandroid.xp_navigation.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * create time : 2023/9/1 15:38
 * create by : xupengpeng
 */
class NaviVPAdapter(context: Fragment, private val fragmentList: MutableList<Fragment>) :
    FragmentStateAdapter(context) {

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}