package com.cookandroid.carrot_market.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.cookandroid.carrot_market.activity.FragmentBookAuthor
import com.cookandroid.carrot_market.activity.FragmentBookPublisher
import com.cookandroid.carrot_market.activity.FragmentBookTitle

class SearchPagerAdapter(fm : FragmentManager) : FragmentStatePagerAdapter(fm) {
    override fun getCount(): Int = 3

    override fun getItem(position: Int): Fragment {
        return when(position){
            0->FragmentBookTitle()
            1->FragmentBookAuthor()
            else->FragmentBookPublisher()
        }
    }
    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0->"책 제목"
            1->"저자"
            else->"출판사"
        }
    }

    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE
    }

}