package com.swjjang7.reportmillie.screen.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.swjjang7.reportmillie.R
import com.swjjang7.reportmillie.databinding.NewsItemDataBinding
import com.swjjang7.reportmillie.repository.remote.entity.Article

class MainAdapter(private val context: Context, private val viewModel: MainViewModel) :
    ListAdapter<Article, MainAdapter.ItemHolder>(MainDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val binding = DataBindingUtil.inflate<NewsItemDataBinding>(
            LayoutInflater.from(context),
            R.layout.news_item_data,
            parent,
            false
        )

        return ItemHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item = getItem(position)
        holder.onBindViewHolder(item)
    }

    inner class ItemHolder(val dataBinding: NewsItemDataBinding) :
        RecyclerView.ViewHolder(dataBinding.root) {

        fun onBindViewHolder(item: Article) {
            dataBinding.also {
                it.data = item
                it.viewModel = viewModel
            }
        }
    }
}

private class MainDiffCallback : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.title == newItem.title && oldItem.url == newItem.url
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }
}