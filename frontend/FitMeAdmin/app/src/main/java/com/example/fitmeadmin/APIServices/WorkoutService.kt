package com.example.fitmeadmin.APIServices


import com.example.fitmeadmin.EntityDao.Addwork
import com.example.fitmeadmin.EntityDao.ExerciseItem
import com.example.fitmeadmin.EntityDao.WrkOutRes
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface WorkoutService {
    @GET("/api/collections/workout/records")
    fun getAllWorkouts(
    ): Call<WrkOutRes>

    @PATCH("/api/collections/workout/records/{id}")
    fun updateWorkout(
        @Path("id") id: String, // Replace with the data type of your 'id' parameter
        @Body updatedWorkout: ExerciseItem // Replace with the data type of your updated workout data
    ): Call<ExerciseItem>


    @POST("/api/collections/workout/records")
    fun addWorkout(@Body newWorkout: Addwork): Call<Addwork>




}