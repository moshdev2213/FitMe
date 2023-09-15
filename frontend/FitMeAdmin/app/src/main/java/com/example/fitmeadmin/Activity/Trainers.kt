package com.example.fitmeadmin.Activity

import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.example.fitmeadmin.R
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

class Trainers : AppCompatActivity() {
    private lateinit var trainer_line_chart:LineChart
    private lateinit var btnBackTrainer:ImageView
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
    }

}