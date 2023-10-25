package com.example.fitmeadmin.Activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//import android.support.v7.widget.CardView
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.fitmeadmin.EntityDao.ExerciseItem
import com.example.fitmeadmin.R

class WorkoutDetail : AppCompatActivity() {
    private lateinit var workout: ExerciseItem

    private lateinit var imgBackBtnDetail: ImageView
    private lateinit var imgMainWrkBanner:ImageView
    private lateinit var tvExNameDetail: TextView
    private lateinit var tvTargetMuscDetail:TextView
    private lateinit var tvCardDetail:TextView
    private lateinit var tvCardFatDetail:TextView
    private lateinit var tvExDescDetail:TextView
    private lateinit var tvCardCalDetail:TextView
    private lateinit var tvExrDurationDetail:TextView
    private lateinit var tvBodyPartDetail:TextView
    private lateinit var imgTargetMuscle:ImageView
//    private lateinit var cvWrkDetailTrainer: CardView
//    private lateinit var cvMealWrkDetail:CardView
//    private lateinit var cvGoBackBtn:CardView
    private lateinit var cvEdt:ImageView
    private lateinit var cvDel:TextView
    @SuppressLint("MissingInflatedId")
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_detail)
        val bundle = intent.extras
        workout = bundle?.getSerializable("exercise") as ExerciseItem


        imgBackBtnDetail = findViewById(R.id.imgBackBtn4)
//        cvGoBackBtn = findViewById(R.id.cvGoBackBtn)

        imgMainWrkBanner = findViewById(R.id.imgMainWrkBanner)
        tvExNameDetail = findViewById(R.id.priced)
        tvTargetMuscDetail = findViewById(R.id.targeting)
        tvCardDetail = findViewById(R.id.fatdet)
        tvExDescDetail = findViewById(R.id.tvExDescDetail)
        tvExrDurationDetail = findViewById(R.id.tvExrDurationDetail)
        imgTargetMuscle = findViewById(R.id.imgTargetMuscle)
        tvCardCalDetail = findViewById(R.id.calaries)
        tvCardFatDetail = findViewById(R.id.fatd)
        tvBodyPartDetail = findViewById(R.id.bodypart)
//        cvWrkDetailTrainer = findViewById(R.id.cvWrkDetailTrainer)
//        cvMealWrkDetail = findViewById(R.id.cvMealWrkDetail)
        cvEdt = findViewById(R.id.edtnav)
        cvDel = findViewById(R.id.edtbtn)

        imgBackBtnDetail.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }
//        cvGoBackBtn.setOnClickListener {
//            finish()
//        }

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

//        cvWrkDetailTrainer.setOnClickListener {
//            startActivity(Intent(this@WorkoutDetail,TrainerFragment::class.java))
//        }
//        cvMealWrkDetail.setOnClickListener {
//            startActivity(Intent(this@WorkoutDetail,MealFragment::class.java))
//
//        }
        cvEdt.setOnClickListener {
            // Assuming you have already obtained the "workout" object
            val intent = Intent(this, WorkoutEdit::class.java)
            intent.putExtra("exercise", workout)
            startActivity(intent)
        }
        cvDel.setOnClickListener {
            finish()
            Toast.makeText(this@WorkoutDetail, "Deleted Successfully", Toast.LENGTH_SHORT).show()
        }

    }


//    override fun onResume() {
//
//
//        Glide.get(this).clearMemory()
//        // Update the UI components with the latest data
//        tvExNameDetail.text = workout.name.capitalize()
//        tvTargetMuscDetail.text = workout.target.capitalize()
//        tvCardDetail.text = "Carbs  ${ workout.carbs.toString().capitalize() }% 🍞"
//        tvCardCalDetail.text = "${ workout.calories.toString().capitalize() }Kcal 🔥"
//        tvCardFatDetail.text = "Fat ${workout.fat.toString().capitalize()}% 💧"
//        tvExDescDetail.text = workout.description.toString().capitalize()
//        tvBodyPartDetail.text = workout.bodyPart.toString().capitalize()
//        tvExrDurationDetail.text = "${ workout.duration.toString().capitalize() } min/day"
//    }
}