package com.example.tikitaka_android.home.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.tikitaka_android.home.ui.fragment.SearchFragment
import com.example.tikitaka_android.profile.ui.MyProfileFragment
import com.example.tikitaka_android.R
import com.example.tikitaka_android.databinding.ActivityHomeBinding
import com.example.tikitaka_android.home.ui.fragment.FriendListFragment
import com.example.tikitaka_android.home.ui.fragment.RoomListFragment
import com.example.tikitaka_android.profile.ui.ProfileFragment

class HomeActivity : AppCompatActivity() {
    private var mBinding : ActivityHomeBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(SearchFragment())
        bottomNavigationItemClick()
    }

    fun bottomNavigationItemClick(){
        binding.homeBottomAppbar.setOnNavigationItemReselectedListener {
            when(it.itemId){
                R.id.menu_home_list -> {
                    replaceFragment(FriendListFragment())
                }

                R.id.menu_home_chat -> {
                    replaceFragment(RoomListFragment())
                }

                R.id.menu_home_profile -> {
                    replaceFragment(MyProfileFragment())
                }

                R.id.menu_home_search -> {
                    replaceFragment(SearchFragment())
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