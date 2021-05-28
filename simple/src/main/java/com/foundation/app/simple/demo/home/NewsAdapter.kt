package com.foundation.app.simple.demo.home

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.foundation.app.simple.R
import com.foundation.app.simple.databinding.ItemNewsBinding
import com.foundation.app.simple.demo.home.data.NewsFeedInfo

/**
 * create by zhusw on 5/27/21 15:34
 */
class NewsAdapter : BaseQuickAdapter<NewsFeedInfo, BaseViewHolder>(R.layout.item_news) {
    override fun convert(holder: BaseViewHolder, item: NewsFeedInfo) {
        val binding = ItemNewsBinding.bind(holder.itemView)
        binding.tvAuthor.text = if (item.author.isNotEmpty()) item.author else item.shareUser
        binding.tvTitle.text = item.title
    }
}