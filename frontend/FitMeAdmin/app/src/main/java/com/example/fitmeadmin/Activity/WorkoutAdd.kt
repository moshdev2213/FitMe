package com.example.fitmeadmin.Activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.fitmeadmin.APIServices.WorkoutService
import com.example.fitmeadmin.Activity.validation.ValidationResult
import com.example.fitmeadmin.Activity.validation.modelVal.addWorkForm
import com.example.fitmeadmin.EntityDao.Addwork
import com.example.fitmeadmin.EntityDao.ExerciseItem
import com.example.fitmeadmin.R
import com.example.fitmeadmin.RetrofitService.RetrofitService
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WorkoutAdd : AppCompatActivity() {

    private lateinit var targetMuscle: TextInputEditText
    private lateinit var durationTime: TextInputEditText
    private lateinit var carbs: TextInputEditText
    private lateinit var fat: TextInputEditText
    private lateinit var calories: TextInputEditText
    private lateinit var name: TextInputEditText
    private lateinit var bodyPart: TextInputEditText
    private lateinit var description: TextInputEditText
    private lateinit var equ: TextInputEditText
    //private lateinit var workoutn: TextView
    private lateinit var wimage: ImageView
    private lateinit var cancelbutton: TextView
    private lateinit var editbtn: TextView
    private var count=0
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_add)

        targetMuscle = findViewById(R.id.tmuscleadd)
        durationTime = findViewById(R.id.durationTadd)
        carbs = findViewById(R.id.carbssadd)
        fat = findViewById(R.id.fatidadd)
        calories =findViewById(R.id.calariesadd)
        name = findViewById(R.id.nameId)
        bodyPart = findViewById(R.id.spinnerGenderSelect)
        description = findViewById(R.id.descriptadd)
        wimage = findViewById(R.id.imgId)
        //workoutn = findViewById(R.id.workout) //header
        cancelbutton = findViewById(R.id.cancelbt)
        editbtn = findViewById(R.id.addcnfm)
        equ = findViewById(R.id.eqId)


        editbtn.setOnClickListener {
            // Get the data from your TextInputEditText views
            submit()

        }



    }

    private fun addWork(){
        val newWorkout = Addwork(
            bodyPart = bodyPart.text.toString(),
            calories = calories.text.toString().toInt(),
            carbs = carbs.text.toString().toInt(),
            description = description.text.toString(),
            duration = durationTime.text.toString(),
            equipment = equ.text.toString(), // Replace with the actual value
            fat = fat.text.toString().toInt(), // Replace with the actual value
            gifUrl = "https://i.ibb.co/sH2XMgH/My5awbi-WZn-JBwh.gif", // Replace with the actual value
            muscle = "https://i.ibb.co/nLYhdq2/abs.jpg", // Replace with the actual value
            name = name.text.toString(),
            target = targetMuscle.text.toString() // Replace with the actual value
        )


        // Call the addWorkout method from your API service
        val retrofitService = RetrofitService()
        val add = retrofitService.getRetrofit().create(WorkoutService::class.java)
        val call = add.addWorkout(newWorkout)

        // Enqueue the call and handle the response
        call.enqueue(object : Callback<Addwork> {
            override fun onResponse(call: Call<Addwork>, response: Response<Addwork>) {
                if (response.isSuccessful) {
                    // Workout added successfully
                    // You can handle success here, for example, show a success message.
                    Toast.makeText(this@WorkoutAdd, "added", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@WorkoutAdd, "failed", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Addwork>, t: Throwable) {
                // Handle network errors or failures here
                Toast.makeText(this@WorkoutAdd, "Network error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })

    }

    fun submit(){

        val wForm = addWorkForm(
            targetMuscle.text.toString(),
            calories.text.toString(),
            fat.text.toString(),
            carbs.text.toString()

        )


        val musclevali = wForm.validatemuscle()
        val calvali = wForm.validatecal()
        val fatvali = wForm.validatefat()
        val carbvali = wForm.validatecarbs()

        when (musclevali){

            is ValidationResult.Valid ->{
                count ++


            }
            is ValidationResult.Invalid ->{
                targetMuscle.error = musclevali.errorMessage

            }
            is ValidationResult.Empty ->{
                targetMuscle.error = musclevali.errorMessage

            }


        }

        when (calvali){

            is ValidationResult.Valid ->{
                count ++


            }
            is ValidationResult.Invalid ->{
                calories.error = calvali.errorMessage

            }
            is ValidationResult.Empty ->{
                calories.error = calvali.errorMessage

            }


        }

        when (fatvali){

            is ValidationResult.Valid ->{
                count ++


            }
            is ValidationResult.Invalid ->{
                fat.error = fatvali.errorMessage

            }
            is ValidationResult.Empty ->{
                fat.error = fatvali.errorMessage

            }


        }
        when (carbvali){

            is ValidationResult.Valid ->{
                count ++


            }
            is ValidationResult.Invalid ->{
                carbs.error = carbvali.errorMessage

            }
            is ValidationResult.Empty ->{
                carbs.error = carbvali.errorMessage

            }


        }



        if(count==4){
            displayAlert("Success","You have successfully Added workout")

        }




    }

    fun displayAlert(title:String, message:String){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("OK") { dialog, which ->
            // Do something when the "OK" button is clicked
           addWork()
        }
        val dialog = builder.create()
        dialog.show()
    }

}