package com.example.fitmeadmin.APIServices

import com.example.fitmeadmin.Entity.Admin
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AdminAPI {
    @POST("/api/collections/admin/records")
    fun createAdmin(@Body patient:Admin): Call<Admin>

//    @GET("https://fit-me.pockethost.io/api/collections/admin/records/?email={email}&password={password}")
//    fun getAdminExist(@Path(value = "email") email:String,@Path(value = "password") password:String):Call<Admin>



}