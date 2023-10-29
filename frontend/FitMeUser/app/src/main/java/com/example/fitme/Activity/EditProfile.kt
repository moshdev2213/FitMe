package com.example.fitme.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import com.example.fitme.R

class EditProfile : AppCompatActivity() {
    private lateinit var cvGoBack:CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        cvGoBack = findViewById(R.id.cvGoBack)
        cvGoBack.setOnClickListener {
            finish()
        }
    }
}