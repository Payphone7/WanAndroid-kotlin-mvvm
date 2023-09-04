package com.wanandroid.xp_navigation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexboxLayout
import com.wanandroid.xp_navigation.R
import com.wanandroid.xp_navigation.bean.SystemBean
import kotlin.math.PI

class SystemRVAdapter(private val listener: NaviItemClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var diff: AsyncListDiffer<SystemBean>

    init {
        diff = AsyncListDiffer(this, MyCallback())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_sys_rv, parent, false)
        )
    }

    @SuppressLint("MissingInflatedId")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = diff.currentList[position]
        (holder as MyViewHolder).name.text = data.name
        holder.flex.removeAllViews()
        for (i in data.children.indices){
            val  item = data.children[i]
            val layout = LayoutInflater.from(holder.itemView.context).inflate(R.layout.item_flex, null, false)
            val tvContent = layout.findViewById<TextView>(R.id.tvContent)
            tvContent.setOnClickListener {
                val event = NaviItemEvent(position,i)
                listener.onClick(event)
            }
            tvContent.text = item.name
            holder.flex.addView(layout)
        }
    }

    override fun getItemCount(): Int {
        return diff.currentList.size
    }

    fun setData(list: List<SystemBean>) {
        diff.submitList(list)
    }

    class MyViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        var name: TextView = item.findViewById(R.id.tvTitle)
        var flex:FlexboxLayout = item.findViewById(R.id.flexBoxLayout)
    }

    class MyCallback : DiffUtil.ItemCallback<SystemBean>() {
        override fun areItemsTheSame(
            oldItem: SystemBean,
            newItem: SystemBean
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: SystemBean,
            newItem: SystemBean
        ): Boolean {
            return oldItem.name == newItem.name
        }
    }
}