package com.example.fitme.Activity

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import com.example.fitme.Entity.UserRecord
import com.example.fitme.EntityDao.MealItem
import com.example.fitme.EntityDao.TrainerItem
import com.example.fitme.R

class Reservation : AppCompatActivity() {

    private  var trainerItem: TrainerItem?=null
    private  var mealItem: MealItem?=null
    private lateinit var userRecord: UserRecord

    private lateinit var cvProceedToPayAct:CardView
    private lateinit var tvTotal:TextView
    private lateinit var tvSubTotal:TextView
    private lateinit var tvPhoneUser:TextView
    private lateinit var tvEmailUser:TextView
    private lateinit var tvNameUser:TextView
    private lateinit var btnBackReservation:ImageView

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)

        btnBackReservation = findViewById(R.id.btnBackReservation)
        cvProceedToPayAct = findViewById(R.id.cvProceedToPayAct)
        tvTotal = findViewById(R.id.tvTotal)
        tvSubTotal = findViewById(R.id.tvSubTotal)
        tvPhoneUser = findViewById(R.id.tvPhoneUser)
        tvEmailUser = findViewById(R.id.tvEmailUser)
        tvNameUser = findViewById(R.id.tvNameUser)

        val bundle = intent.extras
        userRecord = bundle?.getSerializable("user", UserRecord::class.java)!!
        mealItem = bundle.getSerializable("meal", MealItem::class.java)
        trainerItem = bundle.getSerializable("trainer", TrainerItem::class.java)

        tvPhoneUser.text = userRecord.record.telephone
        tvEmailUser.text = userRecord.record.email
        tvNameUser.text = userRecord.record.name.capitalize()

        if(trainerItem!=null && userRecord!=null){
            reserveTrainer()
        }else if (mealItem!=null && userRecord!=null){
            reserveMeal()
        }
        btnBackReservation.setOnClickListener {
            finish()
        }
        cvProceedToPayAct.setOnClickListener {
            val bundle = Bundle().apply {
                putSerializable("trainer", trainerItem)
                putSerializable("meal", mealItem)
                putSerializable("user", userRecord)
            }
            val intent = Intent(this@Reservation, Payment::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
            finish()
        }
    }

    private fun reserveMeal() {
        tvTotal.text = "Rs.${mealItem?.price}.00"
        tvSubTotal.text = "Rs.${mealItem?.price}.00"
    }

    private fun reserveTrainer() {
        tvTotal.text = "Rs.${trainerItem?.charges}.00"
        tvSubTotal.text = "Rs.${trainerItem?.charges}.00"
    }
}