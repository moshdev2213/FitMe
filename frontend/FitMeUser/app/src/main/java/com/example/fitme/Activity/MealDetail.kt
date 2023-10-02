package com.example.fitme.Activity

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.fitme.Entity.UserRecord
import com.example.fitme.EntityDao.ExerciseItem
import com.example.fitme.EntityDao.MealItem
import com.example.fitme.R

class MealDetail : AppCompatActivity() {
    private lateinit var mealItem: MealItem
    private lateinit var userRecord: UserRecord

    private lateinit var imgBackBtn: ImageView
    private lateinit var imgBannerMealDetail: ImageView
    private lateinit var tvPriceMealDetail: TextView
    private lateinit var tvMealNameDetail: TextView
    private lateinit var tvMealCarb: TextView
    private lateinit var tvMealFat: TextView
    private lateinit var tvMealCal: TextView

    private lateinit var tvMealType: TextView
    private lateinit var tvMealWeight: TextView
    private lateinit var tvMealDescLong: TextView

    private lateinit var tvMealWeightNutFact: TextView
    private lateinit var tvFatMealNutFact: TextView
    private lateinit var tvCarbMealNutFact: TextView
    private lateinit var tvProtienMealNutFact: TextView
    private lateinit var tvCaloMealNutFact: TextView
    private lateinit var tvMealDescNutFact: TextView

    private lateinit var cvBackBtn: CardView
    private lateinit var cvBuyBtn: CardView

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_detail)

        val bundle = intent.extras
        mealItem = bundle?.getSerializable("meal", MealItem::class.java)!!
        userRecord = bundle?.getSerializable("user", UserRecord::class.java)!!


        imgBackBtn = findViewById(R.id.imgBackBtn)
        cvBackBtn = findViewById(R.id.cvBackBtn)

        imgBannerMealDetail = findViewById(R.id.imgBannerMealDetail)
        tvMealDescLong = findViewById(R.id.tvMealDescLong)
        tvPriceMealDetail = findViewById(R.id.tvPriceMealDetail)
        tvMealNameDetail = findViewById(R.id.tvMealNameDetail)
        tvMealCarb = findViewById(R.id.tvMealCarb)
        tvMealFat = findViewById(R.id.tvMealFat)
        tvMealCal = findViewById(R.id.tvMealCal)
        tvMealType = findViewById(R.id.tvMealType)
        tvMealWeight = findViewById(R.id.tvMealWeight)
        tvMealWeightNutFact = findViewById(R.id.tvMealWeightNutFact)
        tvFatMealNutFact = findViewById(R.id.tvFatMealNutFact)
        tvCarbMealNutFact = findViewById(R.id.tvCarbMealNutFact)
        tvProtienMealNutFact = findViewById(R.id.tvProtienMealNutFact)
        tvCaloMealNutFact = findViewById(R.id.tvCaloMealNutFact)
        tvMealDescNutFact = findViewById(R.id.tvMealDescNutFact)
        cvBuyBtn = findViewById(R.id.cvBuyBtn)

        imgBackBtn.setOnClickListener {
            finish()
        }
        cvBackBtn.setOnClickListener {
            finish()
        }
        cvBuyBtn.setOnClickListener {
            val bundle = Bundle().apply {
                putSerializable("meal", mealItem)
                putSerializable("user", userRecord)
            }
            val intent = Intent(this@MealDetail, Reservation::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }

        //load main banner with glide
        Glide.with(this@MealDetail)
            .load(mealItem.bannerImg)
            .fitCenter()
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .placeholder(R.drawable.animegife)
            .into(imgBannerMealDetail)

        tvMealDescLong.text = mealItem.description.capitalize()
        tvPriceMealDetail.text = "Rs ${mealItem.price}.00"
        tvMealNameDetail.text = mealItem.name.capitalize()
        tvMealCarb.text = "Carbs  ${ mealItem.carbs.toString().capitalize() }% 🍞"
        tvMealFat.text = "Fat ${mealItem.fat.toString().capitalize()}% 💧"
        tvMealType.text = mealItem.mealType.capitalize()
        tvMealWeight.text = "${ mealItem.weight }g"
        tvMealWeightNutFact.text = "${ mealItem.weight }g"
        tvFatMealNutFact.text = "${ mealItem.fat }g"
        tvCarbMealNutFact.text = "${ mealItem.carbs }g"
        tvProtienMealNutFact.text = "${ mealItem.protien }g"
        tvCaloMealNutFact.text = "${ mealItem.calories }g"
        tvMealDescNutFact.text = mealItem.factDescription.capitalize()
    }
}