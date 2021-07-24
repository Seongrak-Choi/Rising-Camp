package com.cookandroid.carrot_market

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cookandroid.carrot_market.databinding.ItemProductListViewBinding

class ProductAdapter(private val ProductList:ArrayList<ProductInfo>) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapter.ViewHolder {
        val binding =
            ItemProductListViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = ProductList.size
    override fun onBindViewHolder(holder: ProductAdapter.ViewHolder, position: Int) {
        holder.bind(ProductList[position])
    }

    inner class ViewHolder(val binding: ItemProductListViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(data: ProductInfo){
                binding.itemImgProduct.setImageResource(data.getImgSrc())
                binding.itemTxProductPrice.setText(data.getPrice())
                binding.itemTxProductName.setText(data.getName())
                binding.itemTxProductAddress.setText(data.getAddress())

                if(data.getChatCount()>0){
                    binding.itemImgProductChat.visibility=binding.root.visibility
                    binding.itemTxProductChatCount.visibility=binding.root.visibility
                    binding.itemTxProductChatCount.setText(ProductList.get(position).getChatCount().toString())
                }

                if(data.getLikeCount()>0){
                    binding.itemImgProductLike.visibility=binding.root.visibility
                    binding.itemTxProductLikeCount.visibility=binding.root.visibility
                    binding.itemTxProductLikeCount.setText(ProductList.get(position).getLikeCount().toString())
                }

            }
    }

}

