package com.example.se_aol

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.se_aol.fragment.CommunityFragment
import com.example.se_aol.fragment.HomeFragment
import com.example.se_aol.fragment.ProfileFragment
import com.example.se_aol.fragment.StudyTipsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val homeFragment = HomeFragment()
        replaceFragment(homeFragment)

        val studyFragment = StudyTipsFragment()
        val communityFragment = CommunityFragment()
        val profileFragment = ProfileFragment()

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation) as BottomNavigationView
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                // TODO: nav ke homefragment
                R.id.nav_home -> {
                    replaceFragment(homeFragment)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_tips -> {
                    replaceFragment(studyFragment)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_community -> {
                    replaceFragment(communityFragment)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_profile -> {
                    replaceFragment(profileFragment)
                    return@setOnNavigationItemSelectedListener true
                }
                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
    }
}