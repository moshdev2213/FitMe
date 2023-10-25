package com.example.fitmeadmin.EntityDao

import java.io.Serializable

data class AddTrainer(
    val name: String,
    val email: String,
    val specialized: String,
    val location: String,
    val experience: Int,
    val age: Int,
    val description: String,
    val availability: String,
    val charges: Int,
    val profile: String,
    val gender: String
): Serializable
