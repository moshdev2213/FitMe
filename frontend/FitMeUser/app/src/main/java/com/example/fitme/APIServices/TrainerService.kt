package com.example.fitme.APIServices

import com.example.fitme.EntityDao.MealRes
import com.example.fitme.EntityDao.TrainerRes
import retrofit2.Call
import retrofit2.http.GET

interface TrainerService {
    @GET("/api/collections/trainer/records")
    fun getAllTrainers(
    ): Call<TrainerRes>
}