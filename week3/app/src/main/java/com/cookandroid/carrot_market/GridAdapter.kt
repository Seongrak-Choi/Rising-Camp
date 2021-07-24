package com.cookandroid.carrot_market

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cookandroid.carrot_market.databinding.ItemMyNearGridBinding
import android.view.LayoutInflater


class GridAdapter(private var gridList: ArrayList<GridInfo>) :
    RecyclerView.Adapter<GridAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridAdapter.ViewHolder {
        var binding : ItemMyNearGridBinding
        binding = ItemMyNearGridBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GridAdapter.ViewHolder, position: Int) {
        holder.bind(gridList[position])
    }

    override fun getItemCount(): Int = gridList.size

    inner class ViewHolder(val binding: ItemMyNearGridBinding):
        RecyclerView.ViewHolder(binding.root){
            fun bind(data : GridInfo){
                binding.itemMyNearGridImg.setImageResource(data.getImgSrc())
                binding.itemMyNearGridTx.text=(data.getName())
            }

    }

}