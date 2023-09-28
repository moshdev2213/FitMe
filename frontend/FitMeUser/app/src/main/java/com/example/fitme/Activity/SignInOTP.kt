package com.example.fitme.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.example.fitme.R

class SignInOTP : AppCompatActivity() {
    private lateinit var tvSwitchSignIn:TextView
    private lateinit var cvSignInOtp:CardView
    private lateinit var etSignInOtp01:EditText
    private lateinit var etSignInOtp02:EditText
    private lateinit var etSignInOtp03:EditText
    private lateinit var etSignInOtp04:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in_otp)
        val intent = intent
        val otpIntent = intent.getStringExtra("otp")

        tvSwitchSignIn = findViewById(R.id.tvSwitchSignIn)
        cvSignInOtp = findViewById(R.id.cvSignInOtp)
        etSignInOtp01 = findViewById(R.id.etSignInOtp01)
        etSignInOtp02 = findViewById(R.id.etSignInOtp02)
        etSignInOtp03 = findViewById(R.id.etSignInOtp03)
        etSignInOtp04 = findViewById(R.id.etSignInOtp04)

        cvSignInOtp.setOnClickListener {
            if (otpIntent != null) {
                validateFields(otpIntent)
            }
        }
        tvSwitchSignIn.setOnClickListener {
            startActivity(Intent(this,SignInNumber::class.java))
            finish()
        }

    }
    private fun validateFields(otpIntent:String){
        val otp01 = etSignInOtp01.text.toString()
        val otp02 = etSignInOtp02.text.toString()
        val otp03 = etSignInOtp03.text.toString()
        val otp04 = etSignInOtp04.text.toString()

        when{
            otp01.isEmpty()->{
                etSignInOtp01.error = "Required"
            }
            otp02.isEmpty()->{
                etSignInOtp02.error = "Required"
            }
            otp03.isEmpty()->{
                etSignInOtp03.error = "Required"
            }
            otp04.isEmpty()->{
                etSignInOtp04.error = "Required"
            }
            else->{
                val combinedOtp = otp01 + otp02 + otp03 + otp04
                authUser(combinedOtp,otpIntent)
            }
        }
    }
    private fun authUser(otp:String,otpIntent:String){
//        println(otpIntent +" "+otp)
        if(otp==otpIntent){
            startActivity(Intent(this@SignInOTP,Home::class.java))
            finish()
        }else{
            Toast.makeText(this@SignInOTP,"OTP Mismatch",Toast.LENGTH_SHORT).show()
        }
    }
}