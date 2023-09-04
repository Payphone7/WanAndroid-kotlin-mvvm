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
import com.wanandroid.xp_navigation.bean.NavigationBean

class NaviRVAdapter(private val listener: NaviItemClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var diff: AsyncListDiffer<NavigationBean>

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
        for (i in data.articles.indices) {
            val layout = LayoutInflater.from(holder.itemView.context)
                .inflate(R.layout.item_flex, null, false)
            val tvContent = layout.findViewById<TextView>(R.id.tvContent)
            tvContent.text = data.articles[i].title
            tvContent.setOnClickListener {
                val event = NaviItemEvent(position,i)
                listener.onClick(event)
            }
            holder.flex.addView(layout)
        }
    }

    override fun getItemCount(): Int {
        return diff.currentList.size
    }

    fun setData(list: List<NavigationBean>?) {
        diff.submitList(if (list != null) ArrayList(list) else null)
    }

    class MyViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        var name: TextView = item.findViewById(R.id.tvTitle)
        var flex: FlexboxLayout = item.findViewById(R.id.flexBoxLayout)
    }

    class MyCallback : DiffUtil.ItemCallback<NavigationBean>() {
        override fun areItemsTheSame(
            oldItem: NavigationBean,
            newItem: NavigationBean
        ): Boolean {
            return oldItem.cid == newItem.cid
        }

        override fun areContentsTheSame(
            oldItem: NavigationBean,
            newItem: NavigationBean
        ): Boolean {
            return oldItem.name == newItem.name
        }
    }

    private var lastClickTime: Long = 0

}