package com.example.fitmeadmin.Activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//import android.support.v7.widget.CardView
//import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.fitmeadmin.APIServices.WorkoutService
import com.example.fitmeadmin.EntityDao.ExerciseItem
import com.example.fitmeadmin.EntityDao.WrkOutRes
import com.example.fitmeadmin.R
import com.example.fitmeadmin.RetrofitService.RetrofitService
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call

class WorkoutEdit : AppCompatActivity() {
    private lateinit var targetMuscle:TextInputEditText
    private lateinit var durationTime:TextInputEditText
    private lateinit var carbs:TextInputEditText
    private lateinit var fat:TextInputEditText
    private lateinit var calories:TextInputEditText
    private lateinit var name:TextInputEditText
    private lateinit var bodyPart:TextInputEditText
    private lateinit var description:TextInputEditText
    private lateinit var workoutn:TextView
    private lateinit var wimage:ImageView
    private lateinit var cancelbutton:TextView
    private lateinit var editbtn:TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_edit)



        val bundle = intent.extras
        val workout = bundle?.getSerializable("exercise") as? ExerciseItem

        targetMuscle = findViewById(R.id.tmuscle)
        durationTime = findViewById(R.id.durationT)
        carbs = findViewById(R.id.carbss)
        fat = findViewById(R.id.fatid)
        calories =findViewById(R.id.calaries)
        name = findViewById(R.id.nameid)
        bodyPart = findViewById(R.id.bPart)
        description = findViewById(R.id.descript)
        wimage = findViewById(R.id.imgId)
        workoutn = findViewById(R.id.workout) //header
        cancelbutton = findViewById(R.id.ctext)
        editbtn = findViewById(R.id.edtcnfrm)



        if (workout != null) {

//            Toast.makeText(this,workout.carbs,Toast.LENGTH_SHORT).show()
            targetMuscle.setText(workout.target)
            durationTime.setText(workout.duration)
            carbs.setText(workout.carbs.toString())

            fat.setText(workout.fat.toString())
            calories.setText(workout.calories.toString())
            name.setText(workout.name)
            bodyPart.setText(workout.bodyPart)
            description.setText(workout.description)


            Glide.with(this@WorkoutEdit)
                .load(workout.muscle)
                .fitCenter()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .placeholder(R.drawable.animegife)
                .into(wimage)
        }

        cancelbutton.setOnClickListener {
           finish()
        }

        editbtn.setOnClickListener {
            // Get the updated values from the EditText fields
            val updatedWorkout = workout?.let { it1 ->
                ExerciseItem(


                    bodyPart = bodyPart.text.toString(),
                    calories = calories.text.toString().toInt(),
                    carbs = carbs.text.toString().toInt(),
                    collectionId = it1.collectionId,
                    collectionName = workout.collectionName,
                    created = workout.created,
                    description = description.text.toString(),
                    duration = durationTime.text.toString(),
                    equipment = workout.equipment,
                    fat = fat.text.toString().toInt(),
                    gifUrl = workout.gifUrl,
                    id = workout.id,
                    muscle = workout.muscle,
                    name = name.text.toString(),
                    target = targetMuscle.text.toString(),
                    updated = workout.updated


                    // Assuming 'carbs' is a Double
                    // Assuming 'fat' is a Double
                    // Assuming 'calories' is a Double


                )
            }

            val retrofitService = RetrofitService()
            val update = retrofitService.getRetrofit().create(WorkoutService::class.java)
            val call: Call<ExerciseItem>? =
                updatedWorkout?.let { it1 -> update.updateWorkout(workout.id, it1) }

            if (call != null) {
                call.enqueue(object : retrofit2.Callback<ExerciseItem> {
                    override fun onResponse(call: Call<ExerciseItem>, response: retrofit2.Response<ExerciseItem>) {
                        if (response.isSuccessful) {
                            // Update was successful, handle the response as needed
                            // You can finish the activity or perform any other action here
                            //finish()


                            Toast.makeText(this@WorkoutEdit, "updated", Toast.LENGTH_SHORT).show()
                        } else {
                            // Handle the error response here
                            Toast.makeText(this@WorkoutEdit, "Failed to update workout", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<ExerciseItem>, t: Throwable) {
                        Toast.makeText(this@WorkoutEdit, "Network error: ${t.message}", Toast.LENGTH_SHORT).show()
                    }
                })
            }

            val intent = Intent(this, WorkoutDetail::class.java)
            intent.putExtra("exercise", updatedWorkout)
            startActivity(intent)
        }





    }
}