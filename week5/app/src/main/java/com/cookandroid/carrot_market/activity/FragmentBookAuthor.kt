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
import android.view.animation.Animation
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cookandroid.carrot_market.R
import com.cookandroid.carrot_market.`interface`.AladinBookInterface
import com.cookandroid.carrot_market.`object`.RetrofitClient
import com.cookandroid.carrot_market.adapter.ProductAdapter
import com.cookandroid.carrot_market.databinding.FragmentBookAuthorBinding
import com.cookandroid.carrot_market.databinding.FragmentBookTitleBinding
import com.cookandroid.carrot_market.info.AladinBookInfo
import com.cookandroid.carrot_market.info.BookItem
import com.cookandroid.carrot_market.utils.ALADIN_API
import com.cookandroid.carrot_market.utils.ProgressDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentBookAuthor : Fragment() {
    private lateinit var binding : FragmentBookAuthorBinding
    val aladinBookInterface = RetrofitClient.aladinBookClient.create(AladinBookInterface::class.java)
    private var aladinBookList = mutableListOf<BookItem>()
    private lateinit var keyword : String
    private lateinit var productAdapter: ProductAdapter
    private var insertcheck : Int =0
    private var PAGE_AUTHOR = 0
    private lateinit var customProgressDialog : ProgressDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var text = activity?.findViewById<EditText>(R.id.activity_search_edt_search)
        customProgressDialog = ProgressDialog(context)
        customProgressDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        PAGE_AUTHOR = 0
        if(aladinBookList.size!=null)
            aladinBookList.clear()

        if(!text?.text.toString().equals("")){
            keyword=text?.text.toString()
            insertcheck=1
        }else{
            insertcheck=0
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentBookAuthorBinding.inflate(layoutInflater)

        var handler = Handler(Looper.getMainLooper()) //api??? ????????? ??? ui??? ????????? ??? ????????? ???????????? ??????

        if(insertcheck==1){
            binding.fragmentBookAuthorTx.visibility=View.INVISIBLE
            Thread {
                if(PAGE_AUTHOR <10){
                    PAGE_AUTHOR++
                }
                handler.post{
                    getBookData(ALADIN_API.CLIENT_KEY,keyword,"Author",ALADIN_API.BESTSELLER_MAX_RESULTS,
                        PAGE_AUTHOR.toString(),"Book","js",ALADIN_API.VERSION)
                }
            }.start()
        }else{
            binding.fragmentBookAuthorTx.visibility=View.VISIBLE
        }


        //???????????? ??? ?????? ????????? ????????? ?????? ?????? ??? ???????????? ?????????
        binding.fragmentBookAuthorRcView.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val lastVisibleItemPosition =
                    (recyclerView.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition()
                val itemTotalCount = recyclerView.adapter!!.itemCount-1


                if(!binding.fragmentBookAuthorRcView.canScrollVertically(1)&&lastVisibleItemPosition==itemTotalCount){ // ????????? ???????????? ??? ?????? ?????? ?????? ??? -1??? ?????? ?????????
                    if(PAGE_AUTHOR <10) PAGE_AUTHOR++

                    productAdapter.deleteLoading()

                    var addList = mutableListOf<BookItem>()

                    var handler = android.os.Handler()
                    handler.postDelayed({
                        aladinBookInterface.getBookSearch(ALADIN_API.CLIENT_KEY,keyword,"Author",ALADIN_API.BESTSELLER_MAX_RESULTS,
                            PAGE_AUTHOR.toString(),"Book","js",ALADIN_API.VERSION).enqueue(
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
                                            PAGE_AUTHOR *20,20)
                                    }else{
                                        Log.d("MainActivity","getBookBestseller - onResponse : Error code ${response.errorBody()}")
                                    }
                                }
                                override fun onFailure(call: Call<AladinBookInfo>, t: Throwable) {
                                    Log.d("MainActivity",t.message ?: "????????????")
                                }
                            })
                    },1000)
                }
            }
        })

        return binding.root
    }



    //??????????????? ??? ?????? API???????????? ??????
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
                            binding.fragmentBookAuthorRcView.layoutManager = LinearLayoutManager(
                                context,
                                LinearLayoutManager.VERTICAL, false
                            ) //????????????????????? ???????????????????????? ??????
                            productAdapter = ProductAdapter(aladinBookList)
                            binding.fragmentBookAuthorRcView.adapter =
                                productAdapter //????????????????????? ???????????? ??????
                            customProgressDialog.dismiss()
                        }else{
                            binding.fragmentBookAuthorTx.text="?????? ????????? ????????????."
                            customProgressDialog.dismiss()
                            binding.fragmentBookAuthorTx.visibility=View.VISIBLE
                        }
                    } else {
                        Log.d("MainActivity", "getBookBestseller - onResponse : Error code ${response.errorBody()}")
                    }
                }

                override fun onFailure(call: Call<AladinBookInfo>, t: Throwable) {
                    Log.d("MainActivity", t.message ?: "????????????")
                }
            })
    }

}