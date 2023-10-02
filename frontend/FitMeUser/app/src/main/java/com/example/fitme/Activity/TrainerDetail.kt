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
import com.example.fitme.EntityDao.TrainerItem
import com.example.fitme.R

class TrainerDetail : AppCompatActivity() {
    private lateinit var trainerItem: TrainerItem
    private lateinit var userRecord: UserRecord

    private lateinit var imgBackBtn2:ImageView
    private lateinit var cvBackBtn:CardView
    private lateinit var cvHireTrainerBtn:CardView
    private lateinit var imgTrainerPicDetail:ImageView
    private lateinit var tvTrainerNameDetail:TextView
    private lateinit var tvTrainerSpecialDetail:TextView
    private lateinit var tvTrainerLocationDetail:TextView
    private lateinit var tvTrainerExpDetail:TextView
    private lateinit var tvTrainerGenderDetail:TextView
    private lateinit var tvTrainerDetailText:TextView
    private lateinit var tvTrainerRangeDetail:TextView
    private lateinit var tvTrainerChargesDetails:TextView
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trainer_detail)

        val bundle = intent.extras
        trainerItem = bundle?.getSerializable("trainer", TrainerItem::class.java)!!
        userRecord = bundle?.getSerializable("user", UserRecord::class.java)!!

        imgBackBtn2 = findViewById(R.id.imgBackBtn2)
        cvBackBtn = findViewById(R.id.cvBackBtn)

        cvHireTrainerBtn = findViewById(R.id.cvHireTrainerBtn)
        imgTrainerPicDetail = findViewById(R.id.imgTrainerPicDetail)
        tvTrainerNameDetail = findViewById(R.id.tvTrainerNameDetail)
        tvTrainerSpecialDetail = findViewById(R.id.tvTrainerSpecialDetail)
        tvTrainerLocationDetail = findViewById(R.id.tvTrainerLocationDetail)
        tvTrainerExpDetail = findViewById(R.id.tvTrainerExpDetail)
        tvTrainerGenderDetail = findViewById(R.id.tvTrainerGenderDetail)
        tvTrainerDetailText = findViewById(R.id.tvTrainerDetailText)
        tvTrainerRangeDetail = findViewById(R.id.tvTrainerRangeDetail)
        tvTrainerChargesDetails = findViewById(R.id.tvTrainerChargesDetails)

        imgBackBtn2.setOnClickListener {
            finish()
        }
        cvBackBtn.setOnClickListener {
            finish()
        }
        cvHireTrainerBtn.setOnClickListener {
            val bundle = Bundle().apply {
                putSerializable("trainer", trainerItem)
                putSerializable("user", userRecord)
            }
            val intent = Intent(this@TrainerDetail, Reservation::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }
        //load main banner with glide
        Glide.with(this@TrainerDetail)
            .load(trainerItem.profile)
            .fitCenter()
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .placeholder(R.drawable.animegife)
            .into(imgTrainerPicDetail)
        tvTrainerNameDetail.text = "Mr. ${ trainerItem.name.capitalize() }"
        tvTrainerSpecialDetail.text = "Spec: ${ trainerItem.specialized.capitalize() }"
        tvTrainerLocationDetail.text = "Add: ${ trainerItem.location.capitalize() }"
        tvTrainerExpDetail.text = "${ trainerItem.experience } years"
        tvTrainerGenderDetail.text = trainerItem.gender.capitalize()
        tvTrainerDetailText.text = trainerItem.description.capitalize()
        tvTrainerRangeDetail.text = trainerItem.availability.capitalize()
        tvTrainerChargesDetails.text = "Rs ${ trainerItem.charges }.00"

    }
}