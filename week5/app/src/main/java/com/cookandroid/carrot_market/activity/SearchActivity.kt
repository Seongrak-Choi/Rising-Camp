package com.cookandroid.carrot_market.activity

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.cookandroid.carrot_market.R
import com.cookandroid.carrot_market.adapter.SearchPagerAdapter
import com.cookandroid.carrot_market.databinding.ActivitySearchBinding
import com.google.android.material.tabs.TabLayout


class SearchActivity : AppCompatActivity(){
    private lateinit var binding : ActivitySearchBinding
    var customPagerAdapter = SearchPagerAdapter(supportFragmentManager)
    private lateinit var fragmentBookTitle: FragmentBookTitle
    private lateinit var fragmentBookAuthor: FragmentBookAuthor
    private lateinit var fragmentBookPublisher: FragmentBookPublisher


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.activitySearchViewPager.adapter = customPagerAdapter
//        binding.activitySearchTabLayout.setupWithViewPager(binding.activitySearchViewPager)

        fragmentBookTitle = FragmentBookTitle()
        fragmentBookAuthor =FragmentBookAuthor()
        fragmentBookPublisher = FragmentBookPublisher()
        supportFragmentManager.beginTransaction().add(R.id.activity_search_framelayout,fragmentBookTitle).commit()

        var tabs =binding.activitySearchTabLayout
        tabs.addTab(tabs.newTab().setText("책 제목"))
        tabs.addTab(tabs.newTab().setText("저 자"))
        tabs.addTab(tabs.newTab().setText("출판사"))



        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{

            override fun onTabSelected(tab: TabLayout.Tab?) {
                val fm = supportFragmentManager
                val transaction = fm.beginTransaction()

                when(tab!!.position){
                    0->{
                        transaction.replace(R.id.activity_search_framelayout,fragmentBookTitle).commit()
                    }
                    1->{
                        transaction.replace(R.id.activity_search_framelayout,fragmentBookAuthor).commit()
                    }
                    2->{
                        transaction.replace(R.id.activity_search_framelayout,fragmentBookPublisher).commit()
                    }
                }

            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })

        binding.activitySearchImgBack.setOnClickListener {
            this.finish()
        }

        binding.activitySearchEdtSearch.setOnEditorActionListener { v, actionId, event ->  //키패드의 확인 버튼을 누르면 동작하는 부분
            var handled = false
            if(actionId == EditorInfo.IME_ACTION_SEARCH){
                val manager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                manager.hideSoftInputFromWindow(
                    currentFocus!!.windowToken,
                    InputMethodManager.HIDE_NOT_ALWAYS
                )
                var fragmentBook = supportFragmentManager.findFragmentById(R.id.activity_search_framelayout)
                if(fragmentBook is FragmentBookTitle)
                    supportFragmentManager.beginTransaction().replace(R.id.activity_search_framelayout,FragmentBookTitle()).commit()
                if(fragmentBook is FragmentBookAuthor)
                    supportFragmentManager.beginTransaction().replace(R.id.activity_search_framelayout,FragmentBookAuthor()).commit()
                if(fragmentBook is FragmentBookPublisher)
                    supportFragmentManager.beginTransaction().replace(R.id.activity_search_framelayout,FragmentBookPublisher()).commit()

                handled =true
            }
            handled
        }

    }

}