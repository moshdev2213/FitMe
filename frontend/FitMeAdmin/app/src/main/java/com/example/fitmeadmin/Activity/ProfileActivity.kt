package com.example.fitmeadmin.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.fitmeadmin.R

class ProfileActivity : AppCompatActivity() {
    private lateinit var imbBackBtn6:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        imbBackBtn6 = findViewById(R.id.imbBackBtn6)
        imbBackBtn6.setOnClickListener {
            finish()
        }
    }
}