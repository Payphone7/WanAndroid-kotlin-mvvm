package com.xp.wanandroid

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.dycw.base.fragment.adapter.FragmentLazyPagerAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.wanandroid.xp_commom.base.BaseActivity
import com.wanandroid.xp_home.fragment.HomeFragment
import com.xp.wanandroid.databinding.ActivityMainBinding


class MainActivity : BaseActivity<ActivityMainBinding>() {

    private var fragmentList: MutableList<Fragment> = mutableListOf(
        HomeFragment(),
        HomeFragment(),
        HomeFragment(),
        HomeFragment()
    )

    private val pagerAdapter : FragmentLazyPagerAdapter by lazy {
        FragmentLazyPagerAdapter(supportFragmentManager)
    }




    override fun init() {
        pagerAdapter.apply {
            setList(fragmentList)
        }
        mBinding.viewPager.apply {
            offscreenPageLimit = 1
            adapter = pagerAdapter
        }
        mBinding.tvName.text="首页"

    }

    override fun observer() {
        mBinding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home ->{
                    mBinding.tvName.text="首页"
                    mBinding.viewPager.currentItem = 0
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.project ->{
                    mBinding.tvName.text="项目"
                    mBinding.viewPager.currentItem = 1
                    return@setOnNavigationItemSelectedListener true

                }
                R.id.navigation ->{
                    mBinding.tvName.text="导航"
                    mBinding.viewPager.currentItem = 2
                    return@setOnNavigationItemSelectedListener true

                }
                R.id.mine ->{
                    mBinding.tvName.text="我的"
                    mBinding.viewPager.currentItem = 3
                    return@setOnNavigationItemSelectedListener true

                }
                else ->{
                    mBinding.viewPager.currentItem = 0
                    return@setOnNavigationItemSelectedListener true

                }
            }
            false
        }

    }

    override fun getLayoutId() = R.layout.activity_main

}
