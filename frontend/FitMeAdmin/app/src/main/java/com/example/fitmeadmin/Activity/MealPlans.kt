package com.example.fitmeadmin.Activity

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitmeadmin.APIServices.MealService
import com.example.fitmeadmin.Adapter.MealAdapter
import com.example.fitmeadmin.Adapter.WorkoutAdapter
import com.example.fitmeadmin.EntityDao.MealItem
import com.example.fitmeadmin.EntityDao.MealRes
import com.example.fitmeadmin.R
import com.example.fitmeadmin.RetrofitService.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealPlans : AppCompatActivity() {
    private lateinit var btnBackMealPlan:ImageView
    private lateinit var cvBeverageOpen:CardView
    private lateinit var cvProtienOpen:CardView
    private lateinit var cvMealPackOpen:CardView

    private lateinit var rvmeals: RecyclerView
    private lateinit var mealAdapter: MealAdapter
   // private lateinit var addbtn: ImageView
  //  private lateinit var addworkout: Button
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
       initRecyclerView()
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

    private fun initRecyclerView() {
        rvmeals = findViewById(R.id.mealRv)

        // Use this as the context
        val context = this

        rvmeals.layoutManager = LinearLayoutManager(context)

        mealAdapter = MealAdapter({ mealItem ->
            mealCardClicked(mealItem)
        }, context)

        rvmeals.adapter = mealAdapter
        fetchMealDetails()
    }

    private fun mealCardClicked(mealItem: MealItem) {
        val bundle = Bundle().apply {
            putSerializable("meal", mealItem)
        }

        val intent = Intent(this, MealDetails::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    private fun fetchMealDetails() {
        val retrofitService = RetrofitService()
        val getList = retrofitService.getRetrofit().create(MealService::class.java)
        val call: Call<MealRes> = getList.getAllMeals()

        call.enqueue(object : Callback<MealRes> {
            override fun onResponse(call: Call<MealRes>, response: Response<MealRes>) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        val mealRes = response.body()
                        val mealItems = mealRes?.items  // select items
                        mealAdapter.setList(mealItems!!)
                        // Handle your UI elements here, if needed.
                    }
                } else {
                    // Use requireContext() to get the context
                    Toast.makeText(this@MealPlans, "Invalid response", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<MealRes>, t: Throwable) {
                // Use requireContext() to get the context
                Toast.makeText(this@MealPlans, "Server Error", Toast.LENGTH_SHORT).show()
            }
        })
    }





}