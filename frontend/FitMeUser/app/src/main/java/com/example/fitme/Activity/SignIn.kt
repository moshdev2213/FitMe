package com.example.fitme.Activity

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.fitme.APIServices.AuthService
import com.example.fitme.DialogAlerts.ProgressLoader
import com.example.fitme.Entity.UserRecord
import com.example.fitme.EntityDao.AuthPassEmail
import com.example.fitme.FormData.UserLoginForm
import com.example.fitme.R
import com.example.fitme.RetrofitService.RetrofitService
import com.example.fitme.Validation.ValidationResult
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignIn : AppCompatActivity() {
    private lateinit var tvSignUp:TextView
    private lateinit var tvForgotPassword:TextView
    private lateinit var cvSignInFb:CardView
    private lateinit var cvSignInGl:CardView
    private lateinit var cvSignBtn:CardView
    private lateinit var etSignInEmail:EditText
    private lateinit var etSignInPassword:EditText
    private var count = 0;
    private lateinit var progressLoader: ProgressLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        askPermissionForPushNotification()//permission getting

        tvSignUp = findViewById(R.id.tvSignUp)
        tvForgotPassword = findViewById(R.id.tvForgotPassword)
        cvSignInFb = findViewById(R.id.cvSignInFb)
        cvSignInGl = findViewById(R.id.cvSignInGl)
        cvSignBtn = findViewById(R.id.cvSignBtn)
        etSignInEmail = findViewById(R.id.etSignInEmail)
        etSignInPassword = findViewById(R.id.etSignInPassword)

        cvSignInFb.setOnClickListener {
            Toast.makeText(this,"Under Dev",Toast.LENGTH_SHORT).show()
        }
        cvSignInGl.setOnClickListener {
            Toast.makeText(this,"Under Dev",Toast.LENGTH_SHORT).show()
        }
        cvSignBtn.setOnClickListener {
            authEmail(etSignInEmail.text.toString(),etSignInPassword.text.toString())
        }
        tvSignUp.setOnClickListener{
            startActivity(Intent(this,SignUp::class.java))
        }
        tvForgotPassword.setOnClickListener {
            startActivity(Intent(this,SignInOTP::class.java))
        }

    }
    private fun authEmail(email: String, password: String){
        loginValidator(email,password)

        if(count==2){
            progressLoader = ProgressLoader(
                this@SignIn,"Verifying Login","Please Wait"
            )
            progressLoader.startProgressLoader()
            val retrofitService = RetrofitService()
            val authService: AuthService = retrofitService.getRetrofit().create(AuthService::class.java)

            val call: Call<UserRecord> = authService.getUserAuth(
                AuthPassEmail(email,password)
            )
            call.enqueue(object : Callback<UserRecord> {
                override fun onResponse(call: Call<UserRecord>, response: Response<UserRecord>) {
                    if (response.isSuccessful) {
                        val user = response.body()
                        if (user != null) {
                            val intent = Intent(this@SignIn, Home::class.java)
                            intent.putExtra("user", user) // Assuming "patient" is Parcelable or Serializable
                            startActivity(intent)
                            progressLoader.dismissProgressLoader()
                            finish()
                        }
                    } else {
                        Toast.makeText(this@SignIn, "Invalid Credentials", Toast.LENGTH_SHORT).show()
                        progressLoader.dismissProgressLoader()
                    }
                }

                override fun onFailure(call: Call<UserRecord>, t: Throwable) {
                    Toast.makeText(this@SignIn, "Server Error", Toast.LENGTH_SHORT).show()
                    progressLoader.dismissProgressLoader()
                }
            })
            count=0;
        }
        count=0;

    }
    private fun loginValidator(email: String,password: String){
        val userLoginForm = UserLoginForm(
            email,
            password
        )
        val emailValidation =userLoginForm.validateEmail()
        val passwordValidation =userLoginForm.validatePassword()

        when(emailValidation){
            is ValidationResult.Valid ->{ count ++ }
            is ValidationResult.Invalid ->{
                etSignInEmail.error =emailValidation.errorMsg
            }
            is ValidationResult.Empty ->{
                etSignInEmail.error =emailValidation.errorMsg
            }
        }

        when(passwordValidation){
            is ValidationResult.Valid ->{ count ++ }
            is ValidationResult.Invalid ->{
                etSignInPassword.error =passwordValidation.errorMsg

            }
            is ValidationResult.Empty ->{
                etSignInPassword.error =passwordValidation.errorMsg

            }
        }
    }
    private fun askPermissionForPushNotification(){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.TIRAMISU){
            if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.POST_NOTIFICATIONS)!= PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),101)
            }
        }
    }
}