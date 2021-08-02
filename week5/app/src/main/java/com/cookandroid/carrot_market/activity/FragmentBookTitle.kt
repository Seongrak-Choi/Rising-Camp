package com.cookandroid.carrot_market.activity

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cookandroid.carrot_market.R
import com.cookandroid.carrot_market.`interface`.AladinBookInterface
import com.cookandroid.carrot_market.`object`.RetrofitClient
import com.cookandroid.carrot_market.adapter.ProductAdapter
import com.cookandroid.carrot_market.databinding.FragmentBookTitleBinding
import com.cookandroid.carrot_market.info.AladinBookInfo
import com.cookandroid.carrot_market.info.BookItem
import com.cookandroid.carrot_market.utils.ALADIN_API
import com.cookandroid.carrot_market.utils.ProgressDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentBookTitle : Fragment() {
    private lateinit var binding : FragmentBookTitleBinding
    private lateinit var keyword : String
    val aladinBookInterface = RetrofitClient.aladinBookClient.create(AladinBookInterface::class.java)
    private var aladinBookList = mutableListOf<BookItem>()
    private lateinit var productAdapter: ProductAdapter
    private var insertcheck : Int =0
    private var PAGE_TITLE = 0
    private lateinit var customProgressDialog : ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //로딩화면을 구현하기 위해 객체 생성
        customProgressDialog = ProgressDialog(context)
        customProgressDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        var text = activity?.findViewById<EditText>(R.id.activity_search_edt_search)

        //책 정보를 담는 배열을 초기화 시켜줌으로써 프래그먼트가 이동했다가 왔을 때 오류를 제거
        if(aladinBookList.size!=null)
            aladinBookList.clear()

        //검색 페이지를 계속 0으로 초기화 해줌
        PAGE_TITLE = 0

        if(!text?.text.toString().equals("")){
            keyword=text?.text.toString()
            insertcheck=1
        }else{
            insertcheck=0
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentBookTitleBinding.inflate(layoutInflater)
        var handler = Handler(Looper.getMainLooper()) //api를 호출할 때 ui를 변경할 수 있도록 핸들러를 선언

        if(insertcheck==1){
            binding.fragmentBookTitleTx.visibility=View.INVISIBLE
            Thread {
                if(PAGE_TITLE <10){
                    PAGE_TITLE++
                }
                handler.post{
                    getBookData(ALADIN_API.CLIENT_KEY,keyword,"Title",ALADIN_API.BESTSELLER_MAX_RESULTS,
                        PAGE_TITLE.toString(),"Book","js",ALADIN_API.VERSION)
                }
            }.start()
        }else{
            binding.fragmentBookTitleTx.visibility=View.VISIBLE
        }


        //스크롤이 더 이상 아래로 내렬갈 곳이 없을 때 동작하는 리스너
        binding.fragmentBookTitleRcView.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val lastVisibleItemPosition =
                    (recyclerView.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition()
                val itemTotalCount = recyclerView.adapter!!.itemCount-1


                if(!binding.fragmentBookTitleRcView.canScrollVertically(1)&&lastVisibleItemPosition==itemTotalCount){ // 아래로 스크롤이 더 이상 되지 않을 때 -1은 위로 안될때
                    if(PAGE_TITLE <10) PAGE_TITLE++

                    productAdapter.deleteLoading()

                    var addList = mutableListOf<BookItem>()

                    var handler = android.os.Handler()
                    handler.postDelayed({
                        aladinBookInterface.getBookSearch(ALADIN_API.CLIENT_KEY,keyword,"Title",ALADIN_API.BESTSELLER_MAX_RESULTS,
                            PAGE_TITLE.toString(),"Book","js",ALADIN_API.VERSION).enqueue(
                            object : Callback<AladinBookInfo> {
                                override fun onResponse(call: Call<AladinBookInfo>, response: Response<AladinBookInfo>) {
                                    if(response.isSuccessful){
                                        val result = response.body() as AladinBookInfo
                                        for(i in result.bookItem){
                                            addList.add(i)
                                        }
                                        addList.add(BookItem(" "," "," "," "," "," "," "," "," "))
                                        productAdapter.setList(addList)
                                        productAdapter.notifyItemRangeInserted(
                                            PAGE_TITLE*20,20)
                                    }else{
                                        Log.d("MainActivity","getBookBestseller - onResponse : Error code ${response.errorBody()}")
                                    }
                                }
                                override fun onFailure(call: Call<AladinBookInfo>, t: Throwable) {
                                    Log.d("MainActivity",t.message ?: "통신오류")
                                }
                            })
                    },1000)
                }
            }
        })





        return binding.root
    }


    //알라딘에서 책 정보 API받아오는 함수
    private fun getBookData(ttbkey:String,querry:String,queryType:String,maxResult:String,start:String,searchTarget:String,output:String,version:String) {
        customProgressDialog.show()
        aladinBookInterface.getBookSearch(ttbkey, querry, queryType, maxResult, start, searchTarget, output, version).enqueue(
            object : Callback<AladinBookInfo> {
                override fun onResponse(call: Call<AladinBookInfo>, response: Response<AladinBookInfo>) {
                    if (response.isSuccessful) {
                        val result = response.body() as AladinBookInfo
                        if (result.bookItem.size != 0) {
                            for (i in result.bookItem) {
                                aladinBookList.add(i)
                            }
                            aladinBookList.add(
                                BookItem(
                                    " ",
                                    " ",
                                    " ",
                                    " ",
                                    " ",
                                    " ",
                                    " ",
                                    " ",
                                    " "
                                )
                            )
                            binding.fragmentBookTitleRcView.layoutManager = LinearLayoutManager(
                                context,
                                LinearLayoutManager.VERTICAL, false
                            ) //리사이클러뷰에 레이아웃매니저를 부착
                            productAdapter = ProductAdapter(aladinBookList)
                            binding.fragmentBookTitleRcView.adapter =
                                productAdapter //리사이클러뷰에 어댑터를 부착
                            customProgressDialog.dismiss()
                        }else{
                            binding.fragmentBookTitleTx.text="검색 결과가 없습니다."
                            customProgressDialog.dismiss()
                            binding.fragmentBookTitleTx.visibility=View.VISIBLE
                        }
                    } else {
                        Log.d("MainActivity", "getBookBestseller - onResponse : Error code ${response.errorBody()}")
                    }
                }

                override fun onFailure(call: Call<AladinBookInfo>, t: Throwable) {
                    Log.d("MainActivity", t.message ?: "통신오류")
                }
            })
    }



}