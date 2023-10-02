package com.example.fitme.Activity

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import com.example.fitme.Entity.UserRecord
import com.example.fitme.Fragment.IndexFragment
import com.example.fitme.Fragment.MealFragment
import com.example.fitme.Fragment.PaymentFragment
import com.example.fitme.Fragment.ProfileFragment
import com.example.fitme.Fragment.TrainerFragment
import com.example.fitme.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class Home : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    private var userObj:UserRecord?=null
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        //for the data incoming from the login
        val receivedUser = intent.getSerializableExtra("user", UserRecord::class.java)
        userObj = receivedUser

        bottomNavigationView = findViewById(R.id.bottomNavigationView)

        replaceFrag(IndexFragment(),userObj)

        bottomNavigationView.setOnItemSelectedListener{
            when(it.itemId){
                R.id.profile->replaceFrag(ProfileFragment(),userObj)
                R.id.home->replaceFrag(IndexFragment(),userObj)
                R.id.meals->replaceFrag(MealFragment(),userObj)
                R.id.trainers->replaceFrag(TrainerFragment(),userObj)
                R.id.payment->replaceFrag(PaymentFragment(),userObj)

                else->{

                }
            }
            true
        }
    }
    private fun replaceFrag(fragment: Fragment,userObj:UserRecord?=null){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        if(userObj !=null){
            val bundle = Bundle()
            bundle.putSerializable("user",userObj)
            fragment.arguments = bundle
        }

        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
    }

}