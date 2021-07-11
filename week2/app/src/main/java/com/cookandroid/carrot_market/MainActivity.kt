package com.cookandroid.carrot_market

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.cookandroid.carrot_market.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() , BottomNavigationView.OnNavigationItemSelectedListener{

    private lateinit var binding : ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainBottomNavView.setOnNavigationItemSelectedListener(this)
        setFrag(0)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_home ->{
                setFrag(0)
                return true
            }
            R.id.menu_my_carrot->{
                setFrag(4)
                return true
            }

        }
        return false
    }


    private fun setFrag(fragNum: Int) {

        val ft = supportFragmentManager.beginTransaction()

        when(fragNum){
            0->{
                ft.replace(R.id.main_frame, FragmentProductListView()).commit()
            }
            1->{
            }
            2->{
            }
            3->{
            }
            4->{
                ft.replace(R.id.main_frame, FragmentMyCarrot()).commit()
            }
            5->{

            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        ActivityCompat.finishAffinity(this)
        System.exit(0)
    }

}