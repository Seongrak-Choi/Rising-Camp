package com.cookandroid.carrot_market

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.cookandroid.carrot_market.databinding.FragmentProductListviewBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.w3c.dom.Text

class FragmentProductListView : Fragment(){

    val productList = ArrayList<ProductInfo>(5)
    lateinit var binding : FragmentProductListviewBinding
    lateinit var adapter : ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productList.add(ProductInfo(R.drawable.item1,"레노버 게이밍노트북","양촌읍 \u00b7 4분 전","650,000원",0,0))
        productList.add(ProductInfo(R.drawable.item2,"신일 열풍기","대화동 \u00b7 6분 전","5,000원",0,0))
        productList.add(ProductInfo(R.drawable.item3,"에어팟 2세대 미개봉 구합니다","김포시 김포본동 \u00b7 6분 전","85,000원",0,0))
        productList.add(ProductInfo(R.drawable.item4,"NT551XDA-K04/C \n삼성노트북팝니다","탄현동 \u00b7 6분 전","920,000원",1,0))
        productList.add(ProductInfo(R.drawable.item5,"SK인터넷 + TV 가입 행사!","대화동 \u00b7 지역광고 \u00b7 지역광고","",5,2))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding= FragmentProductListviewBinding.inflate(layoutInflater)
        binding.mainFloatingBtn.setColorFilter(ContextCompat.getColor(requireContext(),R.color.white)) //floatingButton의 아이콘 색상을 변경

        return binding.root
    }

    override fun onStart(){ //해당 프래그먼트가 화면에 나올때 마다 리스트를 새로고침하기 위해 onStart()에서 리스트뷰 부착
        super.onStart()
        activity?.runOnUiThread { //onStart()에서는 UI변경이 불가능하기 때문에 runOnUiThread를 사용해 UI를 변경해 준다.
            var productListShuffle = ArrayList<ProductInfo>()
            productListShuffle.addAll(productList.shuffled())
            adapter= ProductAdapter(requireContext(), productListShuffle)
            binding.fragmentListview.adapter=adapter
        }
    }
}