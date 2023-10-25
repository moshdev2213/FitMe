package com.example.fitmeadmin.APIServices


import com.example.fitmeadmin.EntityDao.UserItem
import com.example.fitmeadmin.EntityDao.UserRes
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH

import retrofit2.http.Path

interface UserService {

    @GET("/api/collections/users/records")
    fun getUsers(
    ): Call<UserRes>

    @PATCH("/api/collections/users/records")
    fun UpdateUser(
        @Path("id") id: String, // Replace with the data type of your 'id' parameter
        @Body updateTrainer: UserItem // Replace with the data type of your updated workout data
    ): Call<UserItem>


//    @POST("/api/collections/users/records")
//    fun addUsers(@Body newTrainer: AddTrainer): Call<AddTrainer>
}