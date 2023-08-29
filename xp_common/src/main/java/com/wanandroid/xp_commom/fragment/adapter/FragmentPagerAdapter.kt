package com.dycw.base.fragment.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter

/**
 * description :
 * created time: 2023/8/22 15:33
 * created by: 许朋朋
 */
class FragmentLazyPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(
    fragmentManager,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
){
    private lateinit var mList: List<Fragment>
    private lateinit var mTitles: Array<String>


    open fun setList(list: List<Fragment>) {
        mList = list
    }

    open fun setTitles(titles: Array<String>) {
        mTitles = titles
    }

//    override fun getItem(position: Int): Fragment? {
//        return if (position < 0 || position >= count) {
//            null
//        } else mList!![position]
//    }

    override fun getItem(position: Int): Fragment {
        return mList[position]
    }

    override fun getCount(): Int {
        return mList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return if (position < 0 || position >= mTitles!!.size) {
            super.getPageTitle(position)
        } else mTitles[position]
    }
}


abstract class FragmentLazyStateAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(
    fragmentManager,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
)
