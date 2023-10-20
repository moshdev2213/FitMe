package com.example.fitmeadmin.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.cardview.widget.CardView
import retrofit2.Callback
import retrofit2.Response

import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.fitmeadmin.APIServices.AdminAPI
import com.example.fitmeadmin.Entity.Admin
import com.example.fitmeadmin.R
import com.example.fitmeadmin.RetrofitService.RetrofitService
import retrofit2.Call

class SignIn : AppCompatActivity() {
    private lateinit var cvSignBtn: CardView
    private lateinit var etSignInEmail: EditText
    private lateinit var etSignInPassword:EditText



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(2000)
        installSplashScreen()
        setContentView(R.layout.activity_sign_in)

        cvSignBtn = findViewById(R.id.cvSignbtn)
        etSignInEmail = findViewById(R.id.InputEmail)
        etSignInPassword = findViewById(R.id.Inputpwd)

        cvSignBtn.setOnClickListener {


            val intent = Intent(this, Home::class.java)
            startActivity(intent)
//            val email = etSignInEmail.text.toString()
//            val password = etSignInPassword.text.toString()
//
//            // Call the `getAdminExist` function with email and password
//            val call = adminapi.getAdminExist(email)
//
//            call.enqueue(object : Callback<Admin> {
//                override fun onResponse(call: Call<Admin>, response: Response<Admin>) {
//                    if (response.isSuccessful) {
//                        // User with the provided email and password exists in the database
//                        val admin = response.body()
//                        // You can navigate to another activity or perform other actions here.
//                        // For example, if the response contains user details, you can use them.
//                    } else {
//                        // User does not exist or login failed, handle the error
//                        // You can show an error message or perform any other action as needed.
//                        Toast.makeText(applicationContext, "Login failed", Toast.LENGTH_SHORT).show()
//                    }
//                }
//
//                override fun onFailure(call: Call<Admin>, t: Throwable) {
//                    // Request failed, handle the error
//                    // You can show an error message or perform any other action as needed.
//                    Toast.makeText(applicationContext, "Request failed: ${t.message}", Toast.LENGTH_SHORT).show()
//                }
//            })
        }

    }





}