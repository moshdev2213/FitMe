package com.example.fitme.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.example.fitme.APIServices.AuthService
import com.example.fitme.DialogAlerts.ProgressLoader
import com.example.fitme.Entity.UserRecord
import com.example.fitme.EntityDao.AuthPassEmail
import com.example.fitme.EntityDao.AuthSignUp
import com.example.fitme.EntityDao.AuthSignUpRes
import com.example.fitme.FormData.UserLoginForm
import com.example.fitme.FormData.UserSignUp
import com.example.fitme.R
import com.example.fitme.RetrofitService.RetrofitService
import com.example.fitme.Validation.ValidationResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUp : AppCompatActivity() {
    private lateinit var cvSignUpGl:CardView
    private lateinit var cvSignUpFb:CardView
    private lateinit var cvSignUpBtn:CardView
    private lateinit var etSignUpTel:EditText
    private lateinit var etSignUpPassword:EditText
    private lateinit var etSignUpEmail:EditText
    private lateinit var etSignUpRePassword:EditText
    private lateinit var tvSignIn:TextView
    private var count = 0;
    private lateinit var progressLoader: ProgressLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        tvSignIn = findViewById(R.id.tvSignIn)
        cvSignUpGl = findViewById(R.id.cvSignUpGl)
        cvSignUpFb = findViewById(R.id.cvSignUpFb)
        cvSignUpBtn = findViewById(R.id.cvSignUpBtn)
        etSignUpTel = findViewById(R.id.etSignUpTel)
        etSignUpPassword = findViewById(R.id.etSignUpPassword)
        etSignUpEmail = findViewById(R.id.etSignUpEmail)
        etSignUpRePassword = findViewById(R.id.etSignUpRePassword)

        cvSignUpFb.setOnClickListener {
            Toast.makeText(this,"Under Dev", Toast.LENGTH_SHORT).show()
        }
        cvSignUpGl.setOnClickListener {
            Toast.makeText(this,"Under Dev",Toast.LENGTH_SHORT).show()
        }
        cvSignUpBtn.setOnClickListener {
            authSighUp(etSignUpTel.text.toString(),
                etSignUpEmail.text.toString(),
                etSignUpPassword.text.toString(),
                etSignUpRePassword.text.toString()
            )
        }
    }
    private fun authSighUp(tel:String,email:String,password:String,repass:String){
        validateSignUp(tel,email,password,repass)
        if(count==4){
            progressLoader = ProgressLoader(
                this@SignUp,"Verifying Login","Please Wait"
            )
            progressLoader.startProgressLoader()
            val retrofitService = RetrofitService()
            val authService: AuthService = retrofitService.getRetrofit().create(AuthService::class.java)

            val call: Call<AuthSignUpRes> = authService.createUserAuth(
                AuthSignUp(tel,email,password,repass)
            )
            call.enqueue(object : Callback<AuthSignUpRes> {
                override fun onResponse(call: Call<AuthSignUpRes>, response: Response<AuthSignUpRes>) {
                    println(response.body())
                    if (response.isSuccessful) {
                        val user = response.body()
                        if (user != null) {
                            val intent = Intent(this@SignUp, SignIn::class.java)
                            startActivity(intent)
                            progressLoader.dismissProgressLoader()
                            finish()
                        }
                    } else {
                        Toast.makeText(this@SignUp, "Invalid Credentials", Toast.LENGTH_SHORT).show()
                        progressLoader.dismissProgressLoader()
                    }
                }

                override fun onFailure(call: Call<AuthSignUpRes>, t: Throwable) {
                    Toast.makeText(this@SignUp, "Server Error", Toast.LENGTH_SHORT).show()
                    progressLoader.dismissProgressLoader()
                }
            })
            count=0;
        }
        count=0;
    }

    private fun validateSignUp(tel:String,email:String,password:String,repass:String){
        val userSignUp = UserSignUp(
            tel,
            email,
            password,
            repass
        )
        val emailValidation =userSignUp.validateEmail()
        val passwordValidation =userSignUp.validatePassword()
        val rePasswordValidation =userSignUp.validateRePass()
        val telValidation =userSignUp.validateTelephone()

        when(telValidation){
            is ValidationResult.Valid ->{ count ++ }
            is ValidationResult.Invalid ->{
                etSignUpTel.error =telValidation.errorMsg
            }
            is ValidationResult.Empty ->{
                etSignUpTel.error =telValidation.errorMsg
            }
        }

        when(emailValidation){
            is ValidationResult.Valid ->{ count ++ }
            is ValidationResult.Invalid ->{
                etSignUpEmail.error =emailValidation.errorMsg

            }
            is ValidationResult.Empty ->{
                etSignUpEmail.error =emailValidation.errorMsg

            }
        }
        when(rePasswordValidation){
            is ValidationResult.Valid ->{ count ++ }
            is ValidationResult.Invalid ->{
                etSignUpRePassword.error =rePasswordValidation.errorMsg
            }
            is ValidationResult.Empty ->{
                etSignUpRePassword.error =rePasswordValidation.errorMsg

            }
        }
        when(passwordValidation){
            is ValidationResult.Valid ->{ count ++ }
            is ValidationResult.Invalid ->{
                etSignUpPassword.error =passwordValidation.errorMsg

            }
            is ValidationResult.Empty ->{
                etSignUpPassword.error =passwordValidation.errorMsg

            }
        }
    }
}