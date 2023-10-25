package com.example.fitmeadmin.Activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.fitmeadmin.APIServices.MealService
import com.example.fitmeadmin.APIServices.WorkoutService
import com.example.fitmeadmin.Activity.validation.ValidationResult
import com.example.fitmeadmin.Activity.validation.modelVal.addMealForm
import com.example.fitmeadmin.Activity.validation.modelVal.addWorkForm
import com.example.fitmeadmin.EntityDao.Addmeal
import com.example.fitmeadmin.EntityDao.Addwork
import com.example.fitmeadmin.R
import com.example.fitmeadmin.RetrofitService.RetrofitService
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealAdd : AppCompatActivity() {
    private lateinit var imgBackBtnDetail: ImageView
    private lateinit var banner: ImageView
    private lateinit var price: TextInputEditText
    private lateinit var tvCardFatDetail: TextInputEditText
    private lateinit var tvCardCarbDetail: TextInputEditText
    private lateinit var tvCardCalDetail: TextInputEditText
    private lateinit var descropt: TextInputEditText
   // private lateinit var descroptS: TextInputEditText
    private lateinit var serv: TextInputEditText
    private lateinit var weight: TextInputEditText
    private lateinit var protien: TextInputEditText
    private lateinit var calori: TextInputEditText
    private lateinit var name: TextInputEditText
    private lateinit var type: TextInputEditText
    private lateinit var cancel: TextView
    private lateinit var edit: TextView
    private lateinit var mid: TextView
    private var count=0
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_add)


        banner = findViewById(R.id.imgId)
        mid = findViewById(R.id.wid)
        price = findViewById(R.id.priceM)
        tvCardFatDetail = findViewById(R.id.fatidadd)
        tvCardCarbDetail = findViewById(R.id.carbssadd)
        tvCardCalDetail = findViewById(R.id.calariesadd)
        //tvCardCalDetail =findViewById(R.id.calD)
        descropt = findViewById(R.id.descriptM)
        serv = findViewById(R.id.serveM)
        weight = findViewById(R.id.spinnerGenderSelect)
        protien = findViewById(R.id.proM)
        name = findViewById(R.id.nameM)
        cancel = findViewById(R.id.cancelbt)
        edit = findViewById(R.id.addcnfm)
        type = findViewById(R.id.type)
        imgBackBtnDetail = findViewById(R.id.imbBackBtn4)

        imgBackBtnDetail.setOnClickListener {
            finish()
        }
        cancel.setOnClickListener {
            finish()
        }




        edit.setOnClickListener {
            submit()

        }
    }

    private fun addMeal(){
        val addmeal = Addmeal(
            name = name.text.toString(),
            price = price.text.toString().toInt(),
            description = descropt.text.toString(),
            mealType = "beverage",
            weight = weight.text.toString().toInt(),
            calories = tvCardCalDetail.text.toString().toInt(),
            carbs = tvCardCarbDetail.text.toString().toInt(),
            factDescription =  "your_description_here", // Replace with actual description
            fat = tvCardFatDetail.text.toString().toInt(),
            protien = protien.text.toString().toInt(),
            serving = serv.text.toString(),
            bannerImg = "https://i.ibb.co/tCL0WY8/i-shgcdn.webp"
        )

        val retrofitService = RetrofitService()
        val add = retrofitService.getRetrofit().create(MealService::class.java)
        val call = add.addMeal(addmeal)

        call.enqueue(object : Callback<Addmeal> {
            override fun onResponse(call: Call<Addmeal>, response: Response<Addmeal>) {
                if (response.isSuccessful) {
                    // Workout added successfully
                    // You can handle success here, for example, show a success message.
                    Toast.makeText(this@MealAdd, "added", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@MealAdd, "failed", Toast.LENGTH_SHORT).show()
                    Log.e("API_ERROR", "Response code: ${response.code()}, Message: ${response.message()}")

                }
            }

            override fun onFailure(call: Call<Addmeal>, t: Throwable) {
                // Handle network errors or failures here
                Toast.makeText(this@MealAdd, "Network error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })

    }


    fun submit(){

        val mForm = addMealForm(
            name.text.toString(),
            tvCardCalDetail.text.toString(),
            tvCardFatDetail.text.toString(),
            tvCardCarbDetail.text.toString()

        )


        val namevali = mForm.validatemuscle()
        val calvali = mForm.validatecal()
        val fatvali = mForm.validatefat()
        val carbvali = mForm.validatecarbs()

        when (namevali){

            is ValidationResult.Valid ->{
                count ++


            }
            is ValidationResult.Invalid ->{
                name.error = namevali.errorMessage

            }
            is ValidationResult.Empty ->{
                name.error = namevali.errorMessage

            }


        }

        when (calvali){

            is ValidationResult.Valid ->{
                count ++


            }
            is ValidationResult.Invalid ->{
                tvCardCalDetail.error = calvali.errorMessage

            }
            is ValidationResult.Empty ->{
                tvCardCalDetail.error = calvali.errorMessage

            }


        }

        when (fatvali){

            is ValidationResult.Valid ->{
                count ++


            }
            is ValidationResult.Invalid ->{
                tvCardFatDetail.error = fatvali.errorMessage

            }
            is ValidationResult.Empty ->{
                tvCardFatDetail.error = fatvali.errorMessage

            }


        }
        when (carbvali){

            is ValidationResult.Valid ->{
                count ++


            }
            is ValidationResult.Invalid ->{
                tvCardCarbDetail.error = carbvali.errorMessage

            }
            is ValidationResult.Empty ->{
                tvCardCarbDetail.error = carbvali.errorMessage

            }


        }



        if(count==4){
            displayAlert("Success","You have successfully Added meal")

        }




    }


    fun displayAlert(title:String, message:String){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("OK") { dialog, which ->
            // Do something when the "OK" button is clicked
            addMeal()
        }
        val dialog = builder.create()
        dialog.show()
    }

}