package com.wanandroid.xp_navigation.bean

/**
 * Created by stew on 8/7/22.
 * mail: stewforani@gmail.com
 */

data class SystemBean(
    val id: Int,
    val name: String,
    val children: List<SystemChildBean>,
) {
    var select: Boolean = false

    data class SystemChildBean(
        val id: Int,
        val name: String,
    )
}
