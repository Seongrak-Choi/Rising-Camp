package com.cookandroid.carrot_market

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.cookandroid.carrot_market.databinding.FragmentProductListviewBinding


class FragmentProductListView : Fragment(){

    val productList = ArrayList<ProductInfo>(5)
    lateinit var binding : FragmentProductListviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productList.add(ProductInfo(R.drawable.item1,"레노버 게이밍노트북","양촌읍 \u00b7 4분 전","650,000원",0,0))
        productList.add(ProductInfo(R.drawable.item2,"신일 열풍기","대화동 \u00b7 6분 전","5,000원",0,0))
        productList.add(ProductInfo(R.drawable.item3,"에어팟 2세대 미개봉 구합니다","김포시 김포본동 \u00b7 6분 전","85,000원",0,0))
        productList.add(ProductInfo(R.drawable.item4,"NT551XDA-K04/C \n삼성노트북팝니다","탄현동 \u00b7 6분 전","920,000원",1,0))
        productList.add(ProductInfo(R.drawable.item5,"SK인터넷 + TV 가입 행사!","대화동 \u00b7 지역광고 \u00b7 지역광고","",5,2))
        productList.add(ProductInfo(R.drawable.item6,"미용실 모발가습기/미스트기/\n헤어스티머 판매","야당동 \u00b7 끌올 1시간 전","250,000원",5,0))
        productList.add(ProductInfo(R.drawable.item7,"미용실 열처리기스탠드 판매합니다","야당동 \u00b7 끌올 1시간 전","350,000",6,1))
        productList.add(ProductInfo(R.drawable.item8,"아이폰 SE2 64GB","양촌읍 \u00b7 끌올 1시간 전","280,000원",4,0))
        productList.add(ProductInfo(R.drawable.item9,"올리비아하슬러 세트 셔츠블라우스\n슬리브리스","가좌동 \u00b7 1시간 전","7,000원",0,0))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding= FragmentProductListviewBinding.inflate(layoutInflater)
        binding.mainFloatingBtn.setColorFilter(ContextCompat.getColor(requireContext(),R.color.white)) //floatingButton의 아이콘 색상을 변경

        var sp = activity?.getSharedPreferences("user_data",0)
        var town = sp?.getString("town","")
        binding.mainTxAddress.setText(town)

        binding.fragmentProductListSwiperefreshlayout.setOnRefreshListener {
            var productListShuffle = ArrayList<ProductInfo>() //섞인 ArrayList를 저장할 변수 선언
            productListShuffle.addAll(productList.shuffled()) // 셔플된 리스트를 위에서 만든 변수에 저장함.

            binding.fragmentRecyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false) //리사이클러뷰에 레이아웃매니저를 부착
            binding.fragmentRecyclerView.adapter = ProductAdapter(productListShuffle) //리사이클러뷰에 어댑터를 부착

            binding.fragmentProductListSwiperefreshlayout.isRefreshing=false
        }

        return binding.root
    }

    override fun onStart(){ //해당 프래그먼트가 화면에 나올때 마다 리스트를 새로고침하기 위해 onStart()에서 리스트뷰 부착
        super.onStart()
        activity?.runOnUiThread { //onStart()에서는 UI변경이 불가능하기 때문에 runOnUiThread를 사용해 UI를 변경해 준다.
            var productListShuffle = ArrayList<ProductInfo>() //섞인 ArrayList를 저장할 변수 선언
            productListShuffle.addAll(productList.shuffled()) // 셔플된 리스트를 위에서 만든 변수에 저장함.

            binding.fragmentRecyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false) //리사이클러뷰에 레이아웃매니저를 부착
            binding.fragmentRecyclerView.adapter = ProductAdapter(productListShuffle) //리사이클러뷰에 어댑터를 부착
        }
    }
}