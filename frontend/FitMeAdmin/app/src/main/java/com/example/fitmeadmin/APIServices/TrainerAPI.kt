package com.example.fitmeadmin.APIServices

import com.example.fitmeadmin.EntityDao.AddTrainer
import com.example.fitmeadmin.EntityDao.Addwork
import com.example.fitmeadmin.EntityDao.ExerciseItem
import com.example.fitmeadmin.EntityDao.TrainerItem
import com.example.fitmeadmin.EntityDao.TrainerRes

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface TrainerAPI {

    @GET("/api/collections/trainer/records")
    fun getAllTrainers(
    ): Call<TrainerRes>

    @PATCH("/api/collections/trainer/records/{id}")
    fun UpdateTrainer(
        @Path("id") id: String, // Replace with the data type of your 'id' parameter
        @Body updateTrainer: TrainerItem // Replace with the data type of your updated workout data
    ): Call<TrainerItem>


    @POST("/api/collections/trainer/records")
    fun addTrainer(@Body newTrainer: AddTrainer): Call<AddTrainer>
}