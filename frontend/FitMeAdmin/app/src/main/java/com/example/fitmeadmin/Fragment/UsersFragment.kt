package com.example.fitmeadmin.Fragment

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.fitmeadmin.R
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate

class UsersFragment : Fragment() {
    private  lateinit var barChart: BarChart

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_users, container, false)

        barChart = view.findViewById(R.id.barChartUsersFrag)

        val list: ArrayList<BarEntry> = ArrayList()

        list.add(BarEntry(100f,400f))
        list.add(BarEntry(101f,100f))
        list.add(BarEntry(102f,80f))
        list.add(BarEntry(103f,320f))
        list.add(BarEntry(104f,350f))
        list.add(BarEntry(105f,500f))

        barChart.setScaleEnabled(false)
        barChart.setDrawMarkers(false)

        val xAxis =  barChart.xAxis
        xAxis.setDrawGridLines(false)
        xAxis.setDrawAxisLine(false)
        xAxis.setDrawLabels(false) // Hide x-axis labels

        val lAxis =  barChart.axisLeft
        lAxis.setDrawGridLines(false)
        lAxis.setDrawAxisLine(false)


        val rAxis =  barChart.axisRight
        rAxis.setDrawGridLines(false)
        rAxis.setDrawAxisLine(false)
        rAxis.setDrawLabels(false) // Hide right y-axis labels

        val barDataSet= BarDataSet(list,"User Activities")
//        val colorPrimary = ContextCompat.getColor(requireContext(),R.color.primary)
        //        barDataSet.setColors(colorPrimary)

        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS,255)
        barDataSet.valueTextColor= Color.BLACK
        val barData= BarData(barDataSet)
        barData.barWidth=0.5f
//        barData.groupBars(1980f, 0.45f, 0.02f);
        barChart.setFitBars(true)
        barChart.data= barData
        barChart.description.text= ""
        barChart.animateY(1000)

        return view
    }

}