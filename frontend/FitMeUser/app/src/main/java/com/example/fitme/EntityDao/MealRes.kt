package com.example.fitme.EntityDao

import java.io.Serializable

data class MealRes(
    val page: Int,
    val perPage: Int,
    val totalItems: Int,
    val totalPages: Int,
    val items: List<MealItem>
):Serializable
data class MealItem(
    val bannerImg: String,
    val calories: Int,
    val carbs: Int,
    val collectionId: String,
    val collectionName: String,
    val created: String,
    val description: String,
    val factDescription: String,
    val fat: Int,
    val id: String,
    val mealType: String,
    val name: String,
    val price: Int,
    val protien: Int,
    val serving: String,
    val updated: String,
    val weight: Int
):Serializable