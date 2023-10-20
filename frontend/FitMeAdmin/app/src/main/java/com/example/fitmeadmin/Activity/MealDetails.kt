package com.example.fitmeadmin.Activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.fitmeadmin.EntityDao.ExerciseItem
import com.example.fitmeadmin.EntityDao.MealItem
import com.example.fitmeadmin.R

class MealDetails : AppCompatActivity() {

    private lateinit var meal: MealItem
    private lateinit var imgBackBtnDetail: ImageView
    private lateinit var banner: ImageView
    private lateinit var price: TextView
    private lateinit var tvCardFatDetail:TextView
    private lateinit var tvCardCarbDetail:TextView
    private lateinit var tvCardCalDetail:TextView
    private lateinit var descroptF:TextView
    private lateinit var descroptS:TextView
    private lateinit var serv:TextView
    private lateinit var weight:TextView
    private lateinit var protien:TextView
    private lateinit var calori:TextView
    private lateinit var cancel:TextView
    private lateinit var edit:TextView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_details)

        imgBackBtnDetail = findViewById(R.id.imgBackBtn2)
        banner = findViewById(R.id.imgTargetMuscle)
        price = findViewById(R.id.priced)
        tvCardFatDetail = findViewById(R.id.fatdet)
        tvCardCarbDetail = findViewById(R.id.carbD)
        tvCardCalDetail = findViewById(R.id.calD)
        descroptF = findViewById(R.id.tvDocDetailText2)
        descroptS = findViewById(R.id.tvDocDetailText3)
        serv = findViewById(R.id.serve)
        weight = findViewById(R.id.weight)
        protien = findViewById(R.id.tvDocExpDetail9)
        calori = findViewById(R.id.tvDocExpDetail10)
        cancel = findViewById(R.id.delbtn)
        edit = findViewById(R.id.edtbtn)

        val bundle = intent.extras
        meal = bundle?.getSerializable("meal") as MealItem


        tvCardCarbDetail.text = "Carbs  ${ meal.carbs.toString().capitalize() }% 🍞"
        tvCardCalDetail.text = "${ meal.calories.toString().capitalize() }Kcal 🔥"
        tvCardFatDetail.text = "Fat ${meal.fat.toString().capitalize()}% 💧"
        price.text = meal.price.toString().capitalize()
        descroptF.text = meal.factDescription
        descroptS.text = meal.description
        serv.text = "${meal.serving} Servings per container"
        weight.text = "${meal.weight.toString()} g"
        protien.text ="${meal.protien.toString()} g"
        calori.text="${meal.calories.toString()} g"


        Glide.with(this@MealDetails)
            .load(meal.bannerImg)
            .fitCenter()
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .placeholder(R.drawable.animegife)
            .into(banner)

        imgBackBtnDetail.setOnClickListener{
            finish()
        }

        cancel.setOnClickListener {
            finish()
        }

        edit.setOnClickListener {
            val intent = Intent(this, MealEdit::class.java)
            intent.putExtra("meal", meal)
            startActivity(intent)
        }

    }
}