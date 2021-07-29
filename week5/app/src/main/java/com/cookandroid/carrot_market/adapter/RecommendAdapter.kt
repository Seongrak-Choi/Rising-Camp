package com.cookandroid.carrot_market.adapter

import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cookandroid.carrot_market.info.RecommendInfo
import com.cookandroid.carrot_market.databinding.ItemMyNearRecommendStoreBinding

class RecommendAdapter(private var recommendList : ArrayList<RecommendInfo>) :
 RecyclerView.Adapter<RecommendAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding : ItemMyNearRecommendStoreBinding =
            ItemMyNearRecommendStoreBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(recommendList[position])
    }

    override fun getItemCount(): Int = recommendList.size

    inner class ViewHolder(val binding : ItemMyNearRecommendStoreBinding):
    RecyclerView.ViewHolder(binding.root){
        fun bind(data : RecommendInfo){
            binding.itemMyNearRecommendImg.setImageResource(data.getImgSrc())
            binding.itemMyNearRecommendTxTitle.text=data.getTitle()
            binding.itemMyNearRecommendTxSubtitle.text=data.getSubTitle()
            binding.itemMyNearRecommendTxPostscript.text = "후기 ${data.getPostscriptCount()}"
            binding.itemMyNearRecommendTxPatron.setText(Html.fromHtml("   &middot 단골 ${data.getPatron()}"))
            binding.itemMyNearRecommendTxContents.text=Html.fromHtml("<b>${data.getNickName()}님</b> ${data.getContents()}")
        }
    }
}