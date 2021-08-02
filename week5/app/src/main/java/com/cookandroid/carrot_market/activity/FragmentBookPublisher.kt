package com.cookandroid.carrot_market.activity

import android.content.Context
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
import com.cookandroid.carrot_market.databinding.FragmentBookPublisherBinding
import com.cookandroid.carrot_market.info.AladinBookInfo
import com.cookandroid.carrot_market.info.BookItem
import com.cookandroid.carrot_market.utils.ALADIN_API
import com.cookandroid.carrot_market.utils.ProgressDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentBookPublisher : Fragment() {
    private lateinit var binding: FragmentBookPublisherBinding
    val aladinBookInterface =
        RetrofitClient.aladinBookClient.create(AladinBookInterface::class.java)
    private var aladinBookList = mutableListOf<BookItem>()
    private lateinit var keyword: String
    private lateinit var productAdapter: ProductAdapter
    private var insertcheck: Int = 0
    private var PAGE = 0
    private lateinit var customProgressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var text = activity?.findViewById<EditText>(R.id.activity_search_edt_search)
        customProgressDialog = ProgressDialog(context)
        customProgressDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        PAGE = 0
        if (aladinBookList.size != null)
            aladinBookList.clear()

        if (!text?.text.toString().equals("")) {
            keyword = text?.text.toString()
            insertcheck = 1
        } else {
            insertcheck = 0
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBookPublisherBinding.inflate(layoutInflater)

        var handler = Handler(Looper.getMainLooper()) //api를 호출할 때 ui를 변경할 수 있도록 핸들러를 선언

        if (insertcheck == 1) {
            binding.fragmentBookPublisherTx.visibility = View.INVISIBLE
            Thread {
                if (PAGE < 10) {
                    PAGE++
                }
                handler.post {
                    getBookData(
                        ALADIN_API.CLIENT_KEY,
                        keyword,
                        "Publisher",
                        ALADIN_API.BESTSELLER_MAX_RESULTS,
                        PAGE.toString(),
                        "Book",
                        "js",
                        ALADIN_API.VERSION
                    )
                }
            }.start()
        } else {
            binding.fragmentBookPublisherTx.visibility = View.VISIBLE
        }


        //스크롤이 더 이상 아래로 내렬갈 곳이 없을 때 동작하는 리스너
        binding.fragmentBookPublisherRcView.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val lastVisibleItemPosition =
                    (recyclerView.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition()
                val itemTotalCount = recyclerView.adapter!!.itemCount - 1


                if (!binding.fragmentBookPublisherRcView.canScrollVertically(1) && lastVisibleItemPosition == itemTotalCount) { // 아래로 스크롤이 더 이상 되지 않을 때 -1은 위로 안될때
                    if (PAGE < 10) PAGE++

                    productAdapter.deleteLoading()

                    var addList = mutableListOf<BookItem>()

                    var handler = android.os.Handler()
                    handler.postDelayed({
                        aladinBookInterface.getBookSearch(
                            ALADIN_API.CLIENT_KEY,
                            keyword,
                            "Publisher",
                            ALADIN_API.BESTSELLER_MAX_RESULTS,
                            PAGE.toString(),
                            "Book",
                            "js",
                            ALADIN_API.VERSION
                        ).enqueue(
                            object : Callback<AladinBookInfo> {
                                override fun onResponse(
                                    call: Call<AladinBookInfo>,
                                    response: Response<AladinBookInfo>
                                ) {
                                    if (response.isSuccessful) {
                                        val result = response.body() as AladinBookInfo
                                        for (i in result.bookItem) {
                                            addList.add(i)
                                        }
                                        addList.add(
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
                                        productAdapter.setList(addList)
                                        productAdapter.notifyItemRangeInserted(
                                            PAGE * 20, 20
                                        )
                                    } else {
                                        Log.d(
                                            "MainActivity",
                                            "getBookBestseller - onResponse : Error code ${response.errorBody()}"
                                        )
                                    }
                                }

                                override fun onFailure(call: Call<AladinBookInfo>, t: Throwable) {
                                    Log.d("MainActivity", t.message ?: "통신오류")
                                }
                            })
                    }, 1000)
                }
            }
        })

        return binding.root
    }

    //알라딘에서 책 정보 API받아오는 함수
    private fun getBookData(
        ttbkey: String,
        querry: String,
        queryType: String,
        maxResult: String,
        start: String,
        searchTarget: String,
        output: String,
        version: String
    ) {
        customProgressDialog.show()
        aladinBookInterface.getBookSearch(
            ttbkey,
            querry,
            queryType,
            maxResult,
            start,
            searchTarget,
            output,
            version
        ).enqueue(
            object : Callback<AladinBookInfo> {
                override fun onResponse(
                    call: Call<AladinBookInfo>,
                    response: Response<AladinBookInfo>
                ) {
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
                            binding.fragmentBookPublisherRcView.layoutManager = LinearLayoutManager(
                                context,
                                LinearLayoutManager.VERTICAL, false
                            ) //리사이클러뷰에 레이아웃매니저를 부착
                            productAdapter = ProductAdapter(aladinBookList)
                            binding.fragmentBookPublisherRcView.adapter =
                                productAdapter //리사이클러뷰에 어댑터를 부착
                            customProgressDialog.dismiss()
                        }else{
                            binding.fragmentBookPublisherTx.text="검색 결과가 없습니다."
                            customProgressDialog.dismiss()
                            binding.fragmentBookPublisherTx.visibility=View.VISIBLE
                        }
                    } else {
                        Log.d(
                            "MainActivity",
                            "getBookBestseller - onResponse : Error code ${response.errorBody()}"
                        )
                    }
                }

                override fun onFailure(call: Call<AladinBookInfo>, t: Throwable) {
                    Log.d("MainActivity", t.message ?: "통신오류")
                }
            })
    }
}