package com.example.fitme.Activity

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import com.example.fitme.APIServices.AuthService
import com.example.fitme.APIServices.PaymentService
import com.example.fitme.DialogAlerts.OkNoDialog
import com.example.fitme.DialogAlerts.ProgressLoader
import com.example.fitme.Entity.UserRecord
import com.example.fitme.EntityDao.AuthPassEmail
import com.example.fitme.EntityDao.MealItem
import com.example.fitme.EntityDao.PaymentReq
import com.example.fitme.EntityDao.PaymentRes
import com.example.fitme.EntityDao.TrainerItem
import com.example.fitme.FormData.UserLoginForm
import com.example.fitme.FormData.UserPayment
import com.example.fitme.R
import com.example.fitme.RetrofitService.RetrofitService
import com.example.fitme.Validation.ValidationResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Payment : AppCompatActivity() {
    private  var trainerItem:TrainerItem?=null
    private  var mealItem:MealItem?=null
    private lateinit var userRecord: UserRecord
    private lateinit var progressLoader: ProgressLoader
    private var count: Int=0
    private var total: Int=0
    private var forValue: String=""
    private var id: String=""
    private lateinit var globalPaymentReq: PaymentReq

    private lateinit var btnBackReservation2: ImageView
    private lateinit var etPayCardHolderName: EditText
    private lateinit var etPayCardNumberPay: EditText
    private lateinit var etExpireDateCard: EditText
    private lateinit var etCvcCard: EditText
    private lateinit var cvPayBtn: CardView

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        val bundle = intent.extras
        userRecord = bundle?.getSerializable("user", UserRecord::class.java)!!
        trainerItem = bundle.getSerializable("trainer", TrainerItem::class.java)
        mealItem = bundle.getSerializable("meal", MealItem::class.java)

        btnBackReservation2 = findViewById(R.id.btnBackReservation2)
        etPayCardHolderName = findViewById(R.id.etPayCardHolderName)
        etPayCardNumberPay = findViewById(R.id.etPayCardNumberPay)
        etExpireDateCard = findViewById(R.id.etExpireDateCard)
        etCvcCard = findViewById(R.id.etCvcCard)
        cvPayBtn = findViewById(R.id.cvPayBtn)

        btnBackReservation2.setOnClickListener {
            finish()
        }
        cvPayBtn.setOnClickListener {
            insertPaymentInfo()
        }
    }
    private fun insertPaymentInfo(){

        validateCard(etCvcCard.text.toString(),
            etPayCardNumberPay.text.toString(),
            etPayCardHolderName.text.toString(),
            etExpireDateCard.text.toString()
        )

        if(trainerItem!=null){
            globalPaymentReq= PaymentReq(
                etCvcCard.text.toString().toInt(),
                etExpireDateCard.text.toString(),
                etPayCardHolderName.text.toString(),
                etPayCardNumberPay.text.toString(),
                "trainer",
                userRecord.record.email,
                "",
                trainerItem!!.charges,
                trainerItem!!.id
            )

        }else if(mealItem!=null){
            globalPaymentReq= PaymentReq(
                etCvcCard.text.toString().toInt(),
                etExpireDateCard.text.toString(),
                etPayCardHolderName.text.toString(),
                etPayCardNumberPay.text.toString(),
                "meal",
                userRecord.record.email,
                mealItem!!.id,
                mealItem!!.price,
                ""
            )
        }

        if(count==4){

            progressLoader = ProgressLoader(
                this@Payment,"Verifying Card","Please Wait...."
            )
            progressLoader.startProgressLoader()
            val retrofitService = RetrofitService()
            val paymentService = retrofitService.getRetrofit().create(PaymentService::class.java)

            val call: Call<PaymentRes> = paymentService.insertPayment(globalPaymentReq)
            call.enqueue(object : Callback<PaymentRes> {
                override fun onResponse(call: Call<PaymentRes>, response: Response<PaymentRes>) {
                    if (response.isSuccessful) {
                        val user = response.body()
                        if (user != null) {
                            progressLoader.dismissProgressLoader()
                            OkNoDialog(this@Payment).dialogWithSuccess("Your Payment Is Made"){
                                val intent = Intent(this@Payment, Home::class.java)
                                intent.putExtra("user", userRecord) // Assuming "user" is Parcelable or Serializable
                                startActivity(intent)
                                finish()
                            }
                        }
                    } else {
                        Toast.makeText(this@Payment, "Invalid Credentials", Toast.LENGTH_SHORT).show()
                        progressLoader.dismissProgressLoader()
                        finish()
                    }
                }

                override fun onFailure(call: Call<PaymentRes>, t: Throwable) {
                    Toast.makeText(this@Payment, "Server Error", Toast.LENGTH_SHORT).show()
                    progressLoader.dismissProgressLoader()
                    finish()
                }
            })

            count=0
        }
        count=0
    }

    private fun validateCard( cvc:String,cNum:String,cName:String,cExpire:String){
        val userPayment = UserPayment(
            cvc,
            cNum,
            cName,
            cExpire
        )
        val cNameValidation =userPayment.validateCardName()
        val cNumValidation =userPayment.validateCardNumber()
        val cvcValidation =userPayment.validateCvc()
        val cExpireValidation =userPayment.validateCardExpiration()

        when(cNameValidation){
            is ValidationResult.Valid ->{ count ++ }
            is ValidationResult.Invalid ->{
                etPayCardHolderName.error =cNameValidation.errorMsg
            }
            is ValidationResult.Empty ->{
                etPayCardHolderName.error =cNameValidation.errorMsg
            }
        }

        when(cNumValidation){
            is ValidationResult.Valid ->{ count ++ }
            is ValidationResult.Invalid ->{
                etPayCardNumberPay.error =cNumValidation.errorMsg

            }
            is ValidationResult.Empty ->{
                etPayCardNumberPay.error =cNumValidation.errorMsg

            }
        }

        when(cvcValidation){
            is ValidationResult.Valid ->{ count ++ }
            is ValidationResult.Invalid ->{
                etCvcCard.error =cvcValidation.errorMsg

            }
            is ValidationResult.Empty ->{
                etCvcCard.error =cvcValidation.errorMsg

            }
        }

        when(cExpireValidation){
            is ValidationResult.Valid ->{ count ++ }
            is ValidationResult.Invalid ->{
                etExpireDateCard.error =cExpireValidation.errorMsg

            }
            is ValidationResult.Empty ->{
                etExpireDateCard.error =cExpireValidation.errorMsg

            }
        }
    }

}