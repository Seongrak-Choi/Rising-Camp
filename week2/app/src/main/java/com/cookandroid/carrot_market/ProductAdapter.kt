package com.cookandroid.carrot_market

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import org.w3c.dom.Text

class ProductAdapter(val context: Context, val ProductList: ArrayList<ProductInfo>) : BaseAdapter(){
    override fun getCount(): Int {
        return ProductList.size
    }

    override fun getItem(position: Int): Any {
        return ProductList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_product_list_view,null)
        val imgSrc = view.findViewById<ImageView>(R.id.item_img_product)
        var name = view.findViewById<TextView>(R.id.item_tx_product_name)
        var address = view.findViewById<TextView>(R.id.item_tx_product_address)
        var price = view.findViewById<TextView>(R.id.item_tx_product_price)
        var imgChat = view.findViewById<ImageView>(R.id.item_img_product_chat)
        var imgLike = view.findViewById<ImageView>(R.id.item_img_product_like)
        var chatCount = view.findViewById<TextView>(R.id.item_tx_product_chatCount)
        var likeCount = view.findViewById<TextView>(R.id.item_tx_product_likeCount)

        imgSrc.setImageResource(ProductList.get(position).getImgSrc())
        name.setText(ProductList.get(position).getName())
        address.setText(ProductList.get(position).getAddress())
        price.setText(ProductList.get(position).getPrice())

        if(ProductList.get(position).getChatCount()>0){
            imgChat.visibility=view.visibility
            chatCount.visibility=view.visibility
            chatCount.setText(ProductList.get(position).getChatCount().toString())
        }

        if(ProductList.get(position).getLikeCount()>0){
            imgLike.visibility=view.visibility
            likeCount.visibility=view.visibility
            likeCount.setText(ProductList.get(position).getLikeCount().toString())
        }

        return view
    }

}