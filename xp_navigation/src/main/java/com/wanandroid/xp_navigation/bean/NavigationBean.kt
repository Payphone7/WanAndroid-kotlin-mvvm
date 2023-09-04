package com.wanandroid.xp_navigation.bean

/**
 * Created by stew on 8/8/22.
 * mail: stewforani@gmail.com
 */
data class NavigationBean(
    val cid: Int,
    val name: String,
    val articles: List<NavigationChildBean>
) {
    var select: Boolean = false
    data class NavigationChildBean(
        val id: Int,
        val link: String,
        val title: String,
    )
}
