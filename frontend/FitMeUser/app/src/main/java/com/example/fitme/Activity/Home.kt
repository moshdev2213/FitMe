package com.example.fitme.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.fitme.Fragment.IndexFragment
import com.example.fitme.Fragment.MealFragment
import com.example.fitme.Fragment.PaymentFragment
import com.example.fitme.Fragment.ProfileFragment
import com.example.fitme.Fragment.TrainerFragment
import com.example.fitme.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class Home : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bottomNavigationView = findViewById(R.id.bottomNavigationView)

        replaceFrag(IndexFragment())

        bottomNavigationView.setOnItemSelectedListener{
            when(it.itemId){
                R.id.profile->replaceFrag(ProfileFragment())
                R.id.home->replaceFrag(IndexFragment())
                R.id.meals->replaceFrag(MealFragment())
                R.id.trainers->replaceFrag(TrainerFragment())
                R.id.payment->replaceFrag(PaymentFragment())

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