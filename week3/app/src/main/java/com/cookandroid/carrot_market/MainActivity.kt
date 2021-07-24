package com.cookandroid.carrot_market

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.cookandroid.carrot_market.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

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
            R.id.menu_my_carrot->{
                binding.mainViewPager.currentItem=4
                return true
            }
        }
        return false
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

    override fun onBackPressed() {
        super.onBackPressed()
        ActivityCompat.finishAffinity(this)
        System.exit(0)
    }

}