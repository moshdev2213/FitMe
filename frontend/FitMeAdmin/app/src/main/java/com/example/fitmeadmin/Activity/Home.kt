package com.example.fitmeadmin.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.fitmeadmin.Fragment.GeneralFragment
import com.example.fitmeadmin.Fragment.HomeFragment
import com.example.fitmeadmin.Fragment.PaymentFragment
import com.example.fitmeadmin.Fragment.UsersFragment
import com.example.fitmeadmin.Fragment.WorkoutFragment
import com.example.fitmeadmin.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class Home : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        replaceFrag(HomeFragment())
        bottomNavigationView.setOnItemSelectedListener{
            when(it.itemId){
                R.id.home->replaceFrag(HomeFragment())
                R.id.users->replaceFrag(UsersFragment())
                R.id.workouts->replaceFrag(WorkoutFragment())
                R.id.payment->replaceFrag(PaymentFragment())
                R.id.general->replaceFrag(GeneralFragment())
                else->{
                }
            }
            true
        }
    }
    private fun replaceFrag(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
    }
}