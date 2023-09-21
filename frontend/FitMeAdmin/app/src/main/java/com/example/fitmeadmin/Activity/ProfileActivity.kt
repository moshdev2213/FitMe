package com.example.fitmeadmin.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.fitmeadmin.R

class ProfileActivity : AppCompatActivity() {
    private lateinit var imbBackBtn:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        imbBackBtn = findViewById(R.id.imbBackBtn)
        imbBackBtn.setOnClickListener {
            finish()
        }
    }
}