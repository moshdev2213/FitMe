package com.example.fitmeadmin.Activity

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.fitmeadmin.R

class MealPlans : AppCompatActivity() {
    private lateinit var btnBackMealPlan:ImageView
    private lateinit var cvBeverageOpen:CardView
    private lateinit var cvProtienOpen:CardView
    private lateinit var cvMealPackOpen:CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_plans)



        btnBackMealPlan = findViewById(R.id.btnBackMealPlan)
        cvMealPackOpen = findViewById(R.id.cvMealPackOpen)
        cvBeverageOpen = findViewById(R.id.cvBeverageOpen)
        cvProtienOpen = findViewById(R.id.cvProtienOpen)

        btnBackMealPlan.setOnClickListener {
            finish()
        }
        cvBeverageOpen.setOnClickListener {
            val text = "Energy drinks are beverages containing caffeine, sugar, and other stimulants, providing a quick energy boost but should be consumed in moderation."

            openModal(text,R.drawable.mountain)
        }
        cvMealPackOpen.setOnClickListener {
            val text = "Meal packs are convenient food bundles with balanced nutrition, perfect for on-the-go lifestyles, but portion control is essential for a healthy diet"

            openModal(text,R.drawable.mealsingle)
        }
        cvProtienOpen.setOnClickListener {
            val text = "Whey protein is a high-quality protein source derived from milk, aiding in muscle recovery and growth when used as a supplement in a balanced diet."

            openModal(text,R.drawable.wheysingle)
        }
    }

    private fun openModal(texts:String,image:Int){
        val dialog = Dialog(this@MealPlans)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.bottom_sheet_meal_detail_layout)


       val text = dialog.findViewById<TextView>(R.id.tvAboutMealType)
       val img = dialog.findViewById<ImageView>(R.id.imgChangeMealType)
        text.text = texts
        img.setImageResource(image)


        dialog.show()
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.setGravity(Gravity.BOTTOM)
    }
}