package com.cookandroid.carrot_market


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.cookandroid.carrot_market.databinding.ItemTownListViewBinding
import java.util.*
import kotlin.collections.ArrayList

class TownAdapter(val context: Context, var TownList: ArrayList<AddressInfo>) : BaseAdapter() {
    lateinit var sp: SharedPreferences
    lateinit var editor : SharedPreferences.Editor

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
        var view: View
        val holder: ViewHolder

        sp = context.getSharedPreferences("user_data", 0)
        editor = sp.edit()

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_town_list_view, null)
            holder = ViewHolder()
            holder.address = view.findViewById(R.id.Item_TownListView_txt)
            holder.bookmark = view.findViewById(R.id.item_TownListView_toggle)
            view.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
            view = convertView
        }

        holder.bookmark?.setOnClickListener {//토글버튼을 누르면 발생할 때 동작 정의
            if (TownList[position].getBookmark() == "t") { //누르기 전의 토글 버튼이 true일 경우 동작
                TownList[position].setBookmarkFalse()
                sort()
            } else { //누르기 전의 토글 버튼이 false일 경우 동작
                TownList[position].setBookmarkTrue()
                sort()
                println(TownList)
            }
        }

        holder.address?.text = TownList[position].getAddress()
        holder.bookmark?.isChecked = TownList[position].getBookmark() == "t"

        view.setOnClickListener {
            var town = choiceTown(TownList[position].getAddress())
            editor.putString("town", town)
            editor.commit()
            var intent = Intent(context, VertificationActivity::class.java)
            context.startActivity(intent)
        }
        return view
    }

    private class ViewHolder {
        var address: TextView? = null
        var bookmark: ToggleButton? = null
    }

    private fun choiceTown(address: String): String {  //배열의 주소 중 마지막 동,읍,면을 따로 구하는 메소드
        var addressArray = address.split(" ")
        return addressArray.get(addressArray.size - 1).toString()
    }

    fun sort() {
        var list2 = ArrayList<AddressInfo>()
        list2.addAll(TownList)
        TownList.clear()
        for (i in list2) {
            if (i.getBookmark() == "t"){
                TownList.add(i)
            }
        }
        for (j in list2) {
            if (j.getBookmark() == "f"){
                TownList.add(j)
            }
        }
        notifyDataSetChanged()
    }

    fun registerActivityState() = object : OnActivityStateChagned{
        override fun onPaused() {
            for(i in TownList.indices){
                var key = "index$i"
                var data = TownList[i].getAddress()+","+TownList[i].getBookmark()
                editor.putString(key,data)
                editor.commit()
            }
        }
    }

    interface OnActivityStateChagned{
        fun onPaused()
    }
}

