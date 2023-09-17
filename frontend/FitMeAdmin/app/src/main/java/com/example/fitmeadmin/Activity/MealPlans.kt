package com.example.fitmeadmin.Activity

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
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
            openModal()
        }
    }

    private fun openModal(){
        val dialog = Dialog(this@MealPlans)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.bottom_sheet_meal_detail_layout)

        dialog.show()
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.setGravity(Gravity.BOTTOM)
    }
}