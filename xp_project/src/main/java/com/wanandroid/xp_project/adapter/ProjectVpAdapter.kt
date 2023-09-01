package com.wanandroid.xp_project.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ProjectVpAdapter(fragment: Fragment, private val fragmentList: MutableList<Fragment>) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

}
