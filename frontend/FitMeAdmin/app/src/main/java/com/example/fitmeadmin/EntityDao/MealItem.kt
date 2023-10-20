package com.example.fitmeadmin.EntityDao

import java.io.Serializable

data class MealItem(

    val id: String,
    val collectionId: String,
    val collectionName: String,
    val created: String,
    val updated: String,
    val name: String,
    val price: Int,
    val description: String,
    val bannerImg: String,
    val mealType: String,
    val weight: Int,
    val carbs: Int,
    val protien: Int,
    val fat: Int,
    val calories: Int,
    val serving: String,
    val factDescription: String
): Serializable
