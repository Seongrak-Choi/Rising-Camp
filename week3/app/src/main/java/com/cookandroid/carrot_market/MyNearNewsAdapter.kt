package com.cookandroid.carrot_market

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cookandroid.carrot_market.databinding.ItemMyNearNewsBinding
import com.cookandroid.carrot_market.databinding.ItemProductListViewBinding


class MyNearNewsAdapter(private var newsList: ArrayList<MyNearNewsInfo>) :
    RecyclerView.Adapter<MyNearNewsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyNearNewsAdapter.ViewHolder {
        val binding =
            ItemMyNearNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyNearNewsAdapter.ViewHolder, position: Int) {
        holder.bind(newsList[position])
    }

    override fun getItemCount(): Int = newsList.size

    inner class ViewHolder(val binding : ItemMyNearNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: MyNearNewsInfo) {
            binding.fragmentMyNearImg.setImageResource(data.getImgSrc())
            binding.fragmentMyNearNewsTx11.text = data.getTitle()
            binding.fragmentMyNearNewsTx12.text = data.getContents()
            binding.fragmentMyNearNewsProfileImg.setImageResource(data.getProfileImg())
            binding.itemMyNearNewsTxTown.text = data.getTown()
            binding.fragmentMyNearNewsProfileName.text = data.getProfileName()
        }
    }

}