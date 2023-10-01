package com.example.fitme.Activity

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.fitme.EntityDao.ExerciseItem
import com.example.fitme.Fragment.MealFragment
import com.example.fitme.Fragment.TrainerFragment
import com.example.fitme.R

class WorkoutDetail : AppCompatActivity() {
    private lateinit var workout:ExerciseItem

    private lateinit var imgBackBtnDetail:ImageView
    private lateinit var imgMainWrkBanner:ImageView
    private lateinit var tvExNameDetail:TextView
    private lateinit var tvTargetMuscDetail:TextView
    private lateinit var tvCardDetail:TextView
    private lateinit var tvCardFatDetail:TextView
    private lateinit var tvExDescDetail:TextView
    private lateinit var tvCardCalDetail:TextView
    private lateinit var tvExrDurationDetail:TextView
    private lateinit var tvBodyPartDetail:TextView
    private lateinit var imgTargetMuscle:ImageView
    private lateinit var cvWrkDetailTrainer:CardView
    private lateinit var cvMealWrkDetail:CardView
    private lateinit var cvGoBackBtn:CardView

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_detail)

        val bundle = intent.extras
        workout = bundle?.getSerializable("exercise", ExerciseItem::class.java)!!

        imgBackBtnDetail = findViewById(R.id.imgBackBtnDetail)
        cvGoBackBtn = findViewById(R.id.cvGoBackBtn)

        imgMainWrkBanner = findViewById(R.id.imgMainWrkBanner)
        tvExNameDetail = findViewById(R.id.tvExNameDetail)
        tvTargetMuscDetail = findViewById(R.id.tvTargetMuscDetail)
        tvCardDetail = findViewById(R.id.tvCardDetail)
        tvExDescDetail = findViewById(R.id.tvExDescDetail)
        tvExrDurationDetail = findViewById(R.id.tvExrDurationDetail)
        imgTargetMuscle = findViewById(R.id.imgTargetMuscle)
        tvCardCalDetail = findViewById(R.id.tvCardCalDetail)
        tvCardFatDetail = findViewById(R.id.tvCardFatDetail)
        tvBodyPartDetail = findViewById(R.id.tvBodyPartDetail)
        cvWrkDetailTrainer = findViewById(R.id.cvWrkDetailTrainer)
        cvMealWrkDetail = findViewById(R.id.cvMealWrkDetail)

        imgBackBtnDetail.setOnClickListener {
            finish()
        }
        cvGoBackBtn.setOnClickListener {
            finish()
        }

        //load main banner with glide
        Glide.with(this@WorkoutDetail)
            .load(workout.gifUrl)
            .fitCenter()
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .placeholder(R.drawable.animegife)
            .into(imgMainWrkBanner)
        tvExNameDetail.text = workout.name.capitalize()
        tvTargetMuscDetail.text = workout.target.capitalize()
        tvCardDetail.text = "Carbs  ${ workout.carbs.toString().capitalize() }% 🍞"
        tvCardCalDetail.text = "${ workout.calories.toString().capitalize() }Kcal 🔥"
        tvCardFatDetail.text = "Fat ${workout.fat.toString().capitalize()}% 💧"
        tvExDescDetail.text = workout.description.toString().capitalize()
        tvBodyPartDetail.text = workout.bodyPart.toString().capitalize()
        tvExrDurationDetail.text = "${ workout.duration.toString().capitalize() } min/day"

        //imgTargetMuscle
        Glide.with(this@WorkoutDetail)
            .load(workout.muscle)
            .fitCenter()
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .placeholder(R.drawable.animegife)
            .into(imgTargetMuscle)
        //ends imgTargetMuscle

        cvWrkDetailTrainer.setOnClickListener {
            startActivity(Intent(this@WorkoutDetail,TrainerFragment::class.java))
        }
        cvMealWrkDetail.setOnClickListener {
            startActivity(Intent(this@WorkoutDetail,MealFragment::class.java))

        }
    }
}