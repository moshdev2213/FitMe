package com.example.fitmeadmin.EntityDao

import android.text.Editable
import java.io.Serializable

data class Addwork(

    val bodyPart: String,
    val calories: Int,
    val carbs: Int,
    val description: String,
    val duration: String,
    val equipment: String,
    val fat: Int,
    val gifUrl: String,
    val muscle: String,
    val name: String,
    val target: String,
): Serializable
