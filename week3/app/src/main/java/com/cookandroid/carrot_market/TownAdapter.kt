package com.cookandroid.carrot_market


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class TownAdapter(val context: Context, val TownList: Array<String>) : BaseAdapter(){
    override fun getCount(): Int {
        return TownList.size
    }

    override fun getItem(position: Int): Any {
        return TownList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_town_list_view,null)
        val town = view.findViewById<TextView>(R.id.Item_TownListView_txt)
        val townList = TownList[position]

        town.setText(townList)

        return view
    }

}

