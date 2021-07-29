package com.cookandroid.carrot_market.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cookandroid.carrot_market.databinding.ItemLoadingBinding
import com.cookandroid.carrot_market.databinding.ItemProductListViewBinding
import com.cookandroid.carrot_market.info.BookItem
import java.text.NumberFormat
import java.util.*

class ProductAdapter(private val ProductList:MutableList<BookItem>) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemProductListViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

//    override fun getItemViewType(position: Int): Int {
//        return when (ProductList[position].)
//    }
    override fun getItemCount(): Int = ProductList.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(ProductList[position])
    }

    inner class ViewHolder(val binding: ItemProductListViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(data: BookItem){
                Glide.with(binding.root).load(data.cover).into(binding.itemImgProduct)
                binding.itemTxProductAddress.text="저자: ${data.author}"
                binding.itemTxProductPublisher.text="출판사: ${data.publisher}"
                binding.itemTxProductName.text=data.title
                var price = NumberFormat.getCurrencyInstance(Locale.KOREA).format(data.priceStandard.toInt())
                binding.itemTxProductPrice.text= price

                binding.root.setOnClickListener {
                    val myIntent =
                        Intent(Intent.ACTION_VIEW, Uri.parse(data.link))
                        binding.root.context.startActivity(myIntent)
                }
            }
    }

    inner class LoadingViewHolder(private val binding:ItemLoadingBinding) : RecyclerView.ViewHolder(binding.root){

    }

}

