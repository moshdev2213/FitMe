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
import com.example.fitme.EntityDao.OtpMsg
import com.example.fitme.EntityDao.SendLK
import com.example.fitme.EntityDao.SendLkRes
import com.example.fitme.FormData.UserSignByNum
import com.example.fitme.FormData.UserSignUp
import com.example.fitme.R
import com.example.fitme.RetrofitService.RetrofitService
import com.example.fitme.RetrofitService.SmsService
import com.example.fitme.Validation.ValidationResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInNumber : AppCompatActivity() {
    private lateinit var cvSignInNumber:CardView
    private lateinit var tvSwitchSignIn:TextView
    private lateinit var etSignInNumber:EditText
    private var count =0
    private lateinit var progressLoader: ProgressLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in_number)

        etSignInNumber = findViewById(R.id.etSignInNumber)
        cvSignInNumber = findViewById(R.id.cvSignInNumber)
        tvSwitchSignIn = findViewById(R.id.tvSwitchSignIn)

        tvSwitchSignIn.setOnClickListener {
            startActivity(Intent(this,SignIn::class.java))
            finish()
        }
        cvSignInNumber.setOnClickListener {
            authSignByNumber(etSignInNumber.text.toString())
        }
    }
    private fun authSignByNumber(tel:String){
        validateNumber(tel)
        if(count==1){
            val genRanNum = generateRandomNumber().toString()
            progressLoader = ProgressLoader(
                this@SignInNumber,"Verifying Login","Please Wait"
            )
            progressLoader.startProgressLoader()
            val smsService = SmsService()
            val authService: AuthService = smsService.getRetrofit().create(AuthService::class.java)

            val call: Call<SendLkRes> = authService.sendUserOtpMessage(
                "Bearer 1102|63zKUEcqXIOjKCbQFmI3PJajH0v1Q0mIkUHg7fNZ",
                SendLK(
                    tel,
                    "sendTest",
                    OtpMsg(genRanNum).getMsg()
                )
            )
            call.enqueue(object : Callback<SendLkRes> {
                override fun onResponse(call: Call<SendLkRes>, response: Response<SendLkRes>) {
                    if (response.isSuccessful) {
                        val user = response.body()
                        if (user != null) {
                            val intent = Intent(this@SignInNumber, SignInOTP::class.java)
                            intent.putExtra("otp", genRanNum)
                            startActivity(intent)
                            progressLoader.dismissProgressLoader()
                            finish()
                        }
                    } else {
                        Toast.makeText(this@SignInNumber, "Invalid Credentials", Toast.LENGTH_SHORT).show()
                        progressLoader.dismissProgressLoader()
                    }
                }

                override fun onFailure(call: Call<SendLkRes>, t: Throwable) {
                    Toast.makeText(this@SignInNumber, "Server Error", Toast.LENGTH_SHORT).show()
                    progressLoader.dismissProgressLoader()
                }
            })
            count=0;
        }
        count=0;
    }

    private fun validateNumber(tel:String){
        val userSignInNumber = UserSignByNum(
            tel
        )
        val telValidation =userSignInNumber.validateTel()

        when(telValidation){
            is ValidationResult.Valid ->{ count ++ }
            is ValidationResult.Invalid ->{
                etSignInNumber.error =telValidation.errorMsg
            }
            is ValidationResult.Empty ->{
                etSignInNumber.error =telValidation.errorMsg
            }
        }
    }
    fun generateRandomNumber(): Int {
        val random = java.util.Random()
        return 1_000 + random.nextInt(9_000)
    }
}