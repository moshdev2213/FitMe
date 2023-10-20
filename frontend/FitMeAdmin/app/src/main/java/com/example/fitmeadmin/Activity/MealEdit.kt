package com.example.fitmeadmin.Activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.fitmeadmin.APIServices.MealService
import com.example.fitmeadmin.APIServices.WorkoutService
import com.example.fitmeadmin.EntityDao.ExerciseItem
import com.example.fitmeadmin.EntityDao.MealItem
import com.example.fitmeadmin.R
import com.example.fitmeadmin.RetrofitService.RetrofitService
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealEdit : AppCompatActivity() {
    private lateinit var meal: MealItem
    private lateinit var imgBackBtnDetail: ImageView
    private lateinit var banner: ImageView
    private lateinit var price: TextInputEditText
    private lateinit var tvCardFatDetail: TextInputEditText
    private lateinit var tvCardCarbDetail: TextInputEditText
    private lateinit var tvCardCalDetail: TextInputEditText
    private lateinit var descroptF: TextInputEditText
    private lateinit var descroptS: TextInputEditText
    private lateinit var serv: TextInputEditText
    private lateinit var weight: TextInputEditText
    private lateinit var protien: TextInputEditText
    private lateinit var calori: TextInputEditText
    private lateinit var name: TextInputEditText
    private lateinit var type: TextInputEditText
    private lateinit var cancel: TextView
    private lateinit var edit: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_edit)

        val bundle = intent.extras
        val meal = bundle?.getSerializable("meal") as? MealItem

        // targetMuscle = findViewById(R.id.tmuscle)
        banner = findViewById(R.id.imgId)
        price = findViewById(R.id.priced)
        tvCardFatDetail = findViewById(R.id.fatd)
        tvCardCarbDetail = findViewById(R.id.carbD)
        tvCardCalDetail = findViewById(R.id.calD)
        //tvCardCalDetail =findViewById(R.id.calD)
        descroptF = findViewById(R.id.descD)
        serv = findViewById(R.id.served)
        weight = findViewById(R.id.weitD)
        protien = findViewById(R.id.protin)
        name = findViewById(R.id.named)
        cancel = findViewById(R.id.canD)
        edit = findViewById(R.id.edtcnfrm)
        type = findViewById(R.id.typ)


        if (meal != null) {

            //targetMuscle.setText(workout.target)
            price.setText(meal.price.toString())
            tvCardCarbDetail.setText(meal.carbs.toString())
            tvCardCalDetail.setText(meal.calories.toString())
            tvCardFatDetail.setText(meal.fat.toString())
            descroptF.setText(meal.description)
            serv.setText(meal.serving)
            weight.setText(meal.weight.toString())
            protien.setText(meal.protien.toString())
            name.setText(meal.name)
            type.setText(meal.mealType.toString())


        }

        cancel.setOnClickListener {
            finish()
        }

        edit.setOnClickListener {
            val updatemeal = meal?.let { it1 ->
                MealItem(
                    id = meal.id,
                    collectionId = meal.collectionId,
                    collectionName = meal.collectionName,
                    created = meal.created,
                    updated = meal.updated,
                    name = name.text.toString(),
                    price = price.text.toString().toInt(),
                    description = descroptF.text.toString(),
                    bannerImg = meal.bannerImg,
                    mealType = type.text.toString(),
                    weight = weight.text.toString().toInt(),
                    calories = tvCardCalDetail.text.toString().toInt(),
                    carbs = tvCardCarbDetail.text.toString().toInt(),
                    factDescription = meal.factDescription,
                    fat = tvCardFatDetail.text.toString().toInt(),
                    protien = protien.text.toString().toInt(),
                    serving = serv.text.toString()


                )
            }
            val retrofitService = RetrofitService()
            val update = retrofitService.getRetrofit().create(MealService::class.java)
            val call: Call<MealItem>? = updatemeal?.let { it1 -> update.updateMeals(meal.id, it1) }
            if (call != null) {
                call.enqueue(object : Callback<MealItem> {
                    override fun onResponse(call: Call<MealItem>, response: Response<MealItem>) {
                        if (response.isSuccessful) {
                            // Handle a successful response here
                            Toast.makeText(this@MealEdit, "updated", Toast.LENGTH_SHORT).show()
                        } else {
                            // Handle an unsuccessful response here
                            Toast.makeText(this@MealEdit, "server error", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<MealItem>, t: Throwable) {
                        // Handle failure here
                        Toast.makeText(this@MealEdit, "error", Toast.LENGTH_SHORT).show()
                    }
                })
            }

            val intent = Intent(this, MealDetails::class.java)
            intent.putExtra("meal", updatemeal)
            startActivity(intent)

        }
    }
}