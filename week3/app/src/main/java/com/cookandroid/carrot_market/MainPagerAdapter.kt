package com.cookandroid.carrot_market

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MainPagerAdapter(fm : FragmentManager) : FragmentPagerAdapter(fm,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getCount(): Int = 5

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> FragmentProductListView()
            1 -> FragmentTownLife()
            2 -> FragmentMyNear()
            3 -> FragmentChatting()
            else -> FragmentMyCarrot()
        }
    }
}