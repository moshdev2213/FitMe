package com.example.fitme.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitme.APIServices.WorkoutService
import com.example.fitme.Adapter.WorkoutAdapter
import com.example.fitme.EntityDao.ExerciseItem
import com.example.fitme.EntityDao.WrkOutRes
import com.example.fitme.R
import com.example.fitme.RetrofitService.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WorkoutList : AppCompatActivity() {
    private lateinit var imgBackBtn3:ImageView
    private lateinit var rvWrkOutList:RecyclerView
    private lateinit var workoutAdapter:WorkoutAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_list)

        imgBackBtn3 = findViewById(R.id.imgBackBtn3)
        rvWrkOutList = findViewById(R.id.rvWrkOutList)

        imgBackBtn3.setOnClickListener {
            finish()
        }
        initRecyclerView()
    }
    private fun initRecyclerView(){
        rvWrkOutList = findViewById(R.id.rvWrkOutList)
        rvWrkOutList.layoutManager= LinearLayoutManager(this@WorkoutList)
        workoutAdapter = WorkoutAdapter {
                exerciseItem: ExerciseItem ->wrkOutCardClicked(exerciseItem)
        }
        rvWrkOutList.adapter = workoutAdapter
        fetchDetails()
    }
    private fun wrkOutCardClicked(exerciseItem: ExerciseItem) {
        val bundle = Bundle().apply {
            putSerializable("exercise", exerciseItem)
        }


        val intent = Intent(this@WorkoutList,WorkoutDetail::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }
    private fun fetchDetails(){
        val retrofitService= RetrofitService()
        val getList =retrofitService.getRetrofit().create(WorkoutService::class.java)
        val call: Call<WrkOutRes> = getList.getAllWorkouts()

        call.enqueue(object : Callback<WrkOutRes> {
            override fun onResponse(call: Call<WrkOutRes>, response: Response<WrkOutRes>) {
                if(response.isSuccessful){
                    if (response.body()!=null){
                        val wrkOutRes = response.body()
                        val exerciseItem = wrkOutRes?.items
                        workoutAdapter.setList(exerciseItem!!)
//                        shimmerDocFrag.stopShimmer()
//                        shimmerDocFrag.visibility = View.GONE
//                        rvFragDocList.visibility = View.VISIBLE

                    }
                }else{
                    Toast.makeText(this@WorkoutList,"Invalid response", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<WrkOutRes>, t: Throwable) {
                Toast.makeText(this@WorkoutList,"Server Error", Toast.LENGTH_SHORT).show()
            }
        })

    }
}