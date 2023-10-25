package com.example.fitmeadmin.Activity

import android.content.Intent
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitmeadmin.APIServices.MealService
import com.example.fitmeadmin.APIServices.TrainerAPI
import com.example.fitmeadmin.Adapter.MealAdapter
import com.example.fitmeadmin.Adapter.TrainerAdapter
import com.example.fitmeadmin.EntityDao.MealItem
import com.example.fitmeadmin.EntityDao.MealRes
import com.example.fitmeadmin.EntityDao.TrainerItem
import com.example.fitmeadmin.EntityDao.TrainerRes
import com.example.fitmeadmin.R
import com.example.fitmeadmin.RetrofitService.RetrofitService
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Trainers : AppCompatActivity() {
    private lateinit var trainer_line_chart:LineChart
    private lateinit var btnBackTrainer:ImageView
    private lateinit var rvtrainer:RecyclerView
    private lateinit var trainerAdapter: TrainerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trainers)

        trainer_line_chart = findViewById(R.id.trainer_line_chart)
        btnBackTrainer = findViewById(R.id.btnBackTrainer)

        btnBackTrainer.setOnClickListener {
            finish()
        }

        // Remove grid lines and labels for the XAxis
        val xAxis = trainer_line_chart.xAxis
        xAxis.setDrawGridLines(false)
        xAxis.setDrawLabels(false)

// Remove grid lines and labels for the left YAxis
        val leftYAxis = trainer_line_chart.axisLeft
        leftYAxis.setDrawGridLines(false)
        leftYAxis.setDrawLabels(true)

// Remove grid lines and labels for the right YAxis (if needed)
        val rightYAxis = trainer_line_chart.axisRight
        rightYAxis.setDrawGridLines(false)
        rightYAxis.setDrawLabels(false)


        val entries = ArrayList<Entry>()
        entries.add(Entry(1f, 50f))
        entries.add(Entry(2f, 30f))
        entries.add(Entry(3f, 90f))
        entries.add(Entry(4f, 20f))
        entries.add(Entry(5f, 60f))

        // Apply the gradient to the paint of the data set
        val colorPrimary = ContextCompat.getColor(this,R.color.bg_danger)

        val dataSet = LineDataSet(entries, "Trainees Data")

        // Enable cubic interpolation for curved lines
        dataSet.mode = LineDataSet.Mode.CUBIC_BEZIER
        // Set the color of the line connecting the circles
        dataSet.color = colorPrimary // Change the color to your desired color


        dataSet.setDrawFilled(true)
        dataSet.fillColor = colorPrimary
        dataSet.fillAlpha = colorPrimary

        // Set the color of the data points (markers)
        dataSet.setCircleColor(colorPrimary) // Change the color to your desired color
         // If you want to change the inner color of the marker

        val lineData = LineData(dataSet)
        trainer_line_chart.data = lineData

// Rename the description label
        trainer_line_chart.description.text = ""
        trainer_line_chart.animateY(1000)

        initRecyclerView()



    }

    private fun initRecyclerView() {
        rvtrainer = findViewById(R.id.rvtrain)

        // Use this as the context
        val context = this

        rvtrainer.layoutManager = LinearLayoutManager(context)

        trainerAdapter = TrainerAdapter({ tarinerItem ->
            trainerCardClick(tarinerItem)
        }, context)

        rvtrainer.adapter = trainerAdapter
        fetchtrainerdetails()
    }

    private fun trainerCardClick(tarinerItem: TrainerItem) {
        val bundle = Bundle().apply {
            putSerializable("trainer", tarinerItem)
        }

        val intent = Intent(this, TrainerDetail::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    private fun fetchtrainerdetails() {
        val retrofitService = RetrofitService()
        val getList = retrofitService.getRetrofit().create(TrainerAPI::class.java)
        val call: Call<TrainerRes> = getList.getAllTrainers()

        call.enqueue(object : Callback<TrainerRes> {
            override fun onResponse(call: Call<TrainerRes>, response: Response<TrainerRes>) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        val trainRes = response.body()
                        val trainerItem = trainRes?.items  // select items
                        trainerAdapter.setList(trainerItem!!)
                        // Handle your UI elements here, if needed.
                    }
                } else {
                    // Use requireContext() to get the context
                    Toast.makeText(this@Trainers, "Invalid response", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<TrainerRes>, t: Throwable) {
                // Use requireContext() to get the context
                Toast.makeText(this@Trainers, "Server Error", Toast.LENGTH_SHORT).show()
            }
        })
    }


}