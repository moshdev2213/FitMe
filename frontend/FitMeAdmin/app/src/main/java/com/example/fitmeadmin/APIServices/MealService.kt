package com.example.fitmeadmin.APIServices

import com.example.fitmeadmin.EntityDao.Addwork
import com.example.fitmeadmin.EntityDao.ExerciseItem
import com.example.fitmeadmin.EntityDao.MealItem
import com.example.fitmeadmin.EntityDao.MealRes
import com.example.fitmeadmin.EntityDao.WrkOutRes
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface MealService {
    @GET("/api/collections/meal/records")
    fun getAllMeals(
    ): Call<MealRes>

    @PATCH("/api/collections/meal/records/{id}")
    fun updateMeals(
        @Path("id") id: String, // Replace with the data type of your 'id' parameter
        @Body updateMeals: MealItem // Replace with the data type of your updated workout data
    ): Call<MealItem>


//    @POST("/api/collections/workout/records")
//    fun addWorkout(@Body newWorkout: Addwork): Call<Addwork>
}