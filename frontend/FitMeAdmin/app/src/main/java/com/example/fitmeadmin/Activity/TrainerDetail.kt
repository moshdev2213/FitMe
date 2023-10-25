package com.example.fitmeadmin.Activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.fitmeadmin.EntityDao.MealItem
import com.example.fitmeadmin.EntityDao.TrainerItem
import com.example.fitmeadmin.R

class TrainerDetail : AppCompatActivity() {
    private lateinit var imgBackBtnDetail: ImageView
    private lateinit var banner: ImageView
    private lateinit var name: TextView
    private lateinit var email: TextView
    private lateinit var location: TextView
    private lateinit var exp: TextView
    private lateinit var age: TextView
    private lateinit var desc: TextView
    private lateinit var gender: TextView
    private lateinit var charge: TextView
    private lateinit var mobile: TextView
    private lateinit var delete: TextView
    private lateinit var edit: TextView
    private lateinit var trainer: TrainerItem

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trainer_detail)

        imgBackBtnDetail = findViewById(R.id.imgBackBtn)
        banner = findViewById(R.id.imgDocPicDetail)
        name = findViewById(R.id.tvDocNameDetail)
        email = findViewById(R.id.tvDocSpecialDetail)
        location = findViewById(R.id.tvDocLocationDetail)
        exp = findViewById(R.id.exp)
        age = findViewById(R.id.age)
        desc = findViewById(R.id.tvDocDetailText)
        gender = findViewById(R.id.gender)
        charge = findViewById(R.id.charge)
        mobile = findViewById(R.id.tvDocTimeRangeDetail)
        delete = findViewById(R.id.delb)
        edit = findViewById(R.id.edtbtn)


        val bundle = intent.extras
        trainer = bundle?.getSerializable("trainer") as TrainerItem
        name.text = trainer.name.toString()
        email.text = trainer.email.toString()
        location.text = trainer.location.toString()
        exp.text = "${trainer.experience.toString().capitalize()} years";
        age.text = trainer.age.toString().capitalize()
        desc.text = trainer.description.toString().capitalize()
        gender.text = trainer.gender.toString()
        charge.text = trainer.charges.toString()
        mobile.text = trainer.availability.toString()




        Glide.with(this@TrainerDetail)
            .load(trainer.profile)
            .fitCenter()
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .placeholder(R.drawable.animegife)
            .into(banner)


        imgBackBtnDetail.setOnClickListener{
            finish()
        }
        delete.setOnClickListener {
            finish()
        }
        edit.setOnClickListener {
            val intent = Intent(this, TrainerEdit::class.java)
            intent.putExtra("trainer", trainer)
            startActivity(intent)
        }
    }
}