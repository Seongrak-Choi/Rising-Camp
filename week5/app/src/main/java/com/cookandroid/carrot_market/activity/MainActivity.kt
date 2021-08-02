package com.cookandroid.carrot_market.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager.widget.ViewPager
import com.cookandroid.carrot_market.R
import com.cookandroid.carrot_market.adapter.MainPagerAdapter
import com.cookandroid.carrot_market.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

private const val TAG_HOME_FRAGMENT = "fragment_product_listview"
private const val TAG_TOWN_LIFE_FRAGMENT = "fragment_town_life"
private const val TAG_NEAR_FRAGMENT = "fragment_my_near"
private const val TAG_CHAT_FRAGMENT = "fragment_chatting"
private const val TAG_CARROT_FRAGMENT = "fragment_my_carrot"

class MainActivity : AppCompatActivity() , BottomNavigationView.OnNavigationItemSelectedListener{

    private lateinit var binding : ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainViewPager.adapter = MainPagerAdapter(supportFragmentManager)

        binding.mainBottomNavView.setOnNavigationItemSelectedListener(this)

        binding.mainViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                binding.mainBottomNavView.menu.getItem(position).isChecked = true
            }

            override fun onPageScrollStateChanged(state: Int) {

            }

        })

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_home ->{
                binding.mainViewPager.currentItem=0
                return true
            }
            R.id.menu_town_life -> {
                binding.mainViewPager.currentItem=1
                return true
            }
            R.id.menu_my_near ->{
                binding.mainViewPager.currentItem=2
                return true
            }
            R.id.menu_chatting ->{
                binding.mainViewPager.currentItem=3
                return true
            }
            R.id.menu_my_carrot ->{
                binding.mainViewPager.currentItem=4
                return true
            }
        }
        return false
    }

//    private fun setFragment(tag:String, fragment:Fragment){
//        val manager: FragmentManager = supportFragmentManager
//        val ft:FragmentTransaction = manager.beginTransaction()
//
//        if(manager.findFragmentByTag(tag)==null){
//            ft.add(R.id.main_viewPager,fragment,tag)
//        }
//
//        val home = manager.findFragmentByTag(TAG_HOME_FRAGMENT)
//        val town = manager.findFragmentByTag(TAG_TOWN_LIFE_FRAGMENT)
//        val near = manager.findFragmentByTag(TAG_NEAR_FRAGMENT)
//        val chat = manager.findFragmentByTag(TAG_CHAT_FRAGMENT)
//        val carrot = manager.findFragmentByTag(TAG_CARROT_FRAGMENT)
//
//        if(home !=null){
//            ft.hide(home)
//        }
//        if(town != null){
//            ft.hide(town)
//        }
//        if(near != null){
//            ft.hide(near)
//        }
//        if(chat != null){
//            ft.hide(chat)
//        }
//        if(carrot != null){
//            ft.hide(carrot)
//        }
//
//        if (tag == TAG_HOME_FRAGMENT) {
//            if (home != null) {
//                ft.show(home)
//            }
//        }
//        if (tag == TAG_TOWN_LIFE_FRAGMENT) {
//            if (town != null) {
//                ft.show(town)
//            }
//        }
//        if (tag == TAG_NEAR_FRAGMENT) {
//            if (near != null) {
//                ft.show(near)
//            }
//        }
//        if (tag == TAG_CHAT_FRAGMENT) {
//            if (chat != null) {
//                ft.show(chat)
//            }
//        }
//        if (tag == TAG_CARROT_FRAGMENT) {
//            if (carrot != null) {
//                ft.show(carrot)
//            }
//        }
//        ft.commitAllowingStateLoss()
//    }


    override fun onBackPressed() {
        super.onBackPressed()
        ActivityCompat.finishAffinity(this)
        System.exit(0)
    }

//    private fun setFrag(fragNum: Int) {
//
//        val ft = supportFragmentManager.beginTransaction()
//
//        when(fragNum){
//            0->{
//                ft.replace(R.id.main_frame, FragmentProductListView()).commit()
//            }
//            1->{
//                ft.replace(R.id.main_frame, FragmentTownLife()).commit()
//            }
//            2->{
//                ft.replace(R.id.main_frame, FragmentMyNear()).commit()
//            }
//            3->{
//                ft.replace(R.id.main_frame, FragmentChatting()).commit()
//            }
//            4->{
//                ft.replace(R.id.main_frame, FragmentMyCarrot()).commit()
//            }
//        }
//    }



}