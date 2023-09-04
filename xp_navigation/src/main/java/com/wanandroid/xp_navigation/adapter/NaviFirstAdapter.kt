package com.wanandroid.xp_navigation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wanandroid.xp_navigation.R
import com.wanandroid.xp_navigation.bean.NavigationBean

/**
 * create time : 2023/9/1 17:00
 * create by : xupengpeng
 */
class NaviFirstAdapter(private val listener: NaviFirstItemClickListener) :
    RecyclerView.Adapter<NaviFirstAdapter.ViewHolder>() {

    private var mList: ArrayList<NavigationBean> = ArrayList()


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvContent: TextView = itemView.findViewById(R.id.tvContent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_sys, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = mList.size

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val navigationBean = mList[position]
        holder.tvContent.text = navigationBean.name
        holder.itemView.setOnClickListener {
            listener.onFirstItemClick(position)
        }

        val context = holder.tvContent.context
        holder.tvContent.background = if (navigationBean.select) {
            context.getDrawable(R.drawable.shape_10dp_grey)
        } else {
            context.getDrawable(R.drawable.shape_10dp_white)
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<NavigationBean>) {
        mList.addAll(list)
        notifyDataSetChanged()
    }


}