package com.example.tikitaka_android.Home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.tikitaka_android.Profile.Ui.MyProfileFragment
import com.example.tikitaka_android.R
import com.example.tikitaka_android.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private var mBinding : ActivityHomeBinding? = null
    private val binding = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(MyProfileFragment())
        bottomNavigationItemClick()
    }

    fun bottomNavigationItemClick(){
        binding.homeBottomAppbar.setOnNavigationItemReselectedListener {
            when(it.itemId){
                R.id.menu_home_list -> {

                }

                R.id.menu_home_chat -> {

                }

                R.id.menu_home_profile -> {
                    replaceFragment(MyProfileFragment())
                }

                R.id.menu_home_addUser -> {

                }
            }
        }
    }

    fun replaceFragment(fragment: Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.home_fragment, fragment)
        fragmentTransaction.commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }
}