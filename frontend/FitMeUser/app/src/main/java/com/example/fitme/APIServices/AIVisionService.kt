package com.example.fitme.APIServices

import com.example.fitme.EntityDao.AuthPassEmail
import com.example.fitme.EntityDao.PredictionResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface AIVisionService {
    @Multipart
    @POST("/customvision/v3.0/Prediction/27ae95a3-7047-4383-aada-fa3d1385f2a2/classify/iterations/Iteration3/image")
//    @Headers("Content-Type: application/octet-stream")
    fun scanBody(
        @Header("Prediction-Key") predict_Key:String,
        @Part image: MultipartBody.Part
    ):Call<PredictionResponse>

}