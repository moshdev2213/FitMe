package com.example.fitmeadmin.EntityDao

import java.io.Serializable

data class Addmeal(
    val name: String,
    val price: Int,
    val description: String,
    val mealType: String,
    val weight: Int,
    val calories: Int,
    val carbs: Int,
    val factDescription: String,
    val fat: Int,
    val protien: Int,
    val serving: String,
    val bannerImg: String,
    
): Serializable
