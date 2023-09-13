package com.example.fitmeadmin.Fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.fitmeadmin.R
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate


class HomeFragment : Fragment() {

    private  lateinit var barChart:BarChart
    private  lateinit var peiChartHome:PieChart
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_home, container, false)

        barChart = view.findViewById(R.id.barChartHome)
        peiChartHome=view.findViewById(R.id.peiChartHome)

        val list: ArrayList<BarEntry> = ArrayList()

        list.add(BarEntry(100f,100f))
        list.add(BarEntry(101f,200f))
        list.add(BarEntry(102f,280f))
        list.add(BarEntry(103f,400f))
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
        val colorPrimary = ContextCompat.getColor(requireContext(),R.color.primary)
//        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS,255)
        barDataSet.setColors(colorPrimary)
        barDataSet.valueTextColor= Color.BLACK
        val barData= BarData(barDataSet)
        barData.barWidth=0.5f
//        barData.groupBars(1980f, 0.45f, 0.02f);
        barChart.setFitBars(true)
        barChart.data= barData
        barChart.description.text= ""
        barChart.animateY(1000)





        val list02:ArrayList<PieEntry> = ArrayList()

        list02.add(PieEntry(100f,"100"))
        list02.add(PieEntry(101f,"101"))
        list02.add(PieEntry(102f,"102"))
        list02.add(PieEntry(103f,"103"))
        list02.add(PieEntry(104f,"104"))

        val pieDataSet= PieDataSet(list02,"List")

        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS,255)
        pieDataSet.valueTextColor = Color.TRANSPARENT // Make text transparent to hide it
        pieDataSet.valueTextSize = 0f // Set text size to 0 to hide it

        val pieData= PieData(pieDataSet)

        peiChartHome.data= pieData

        peiChartHome.description.isEnabled = false // Disable chart description
        peiChartHome.legend.isEnabled = false // Disable legend
        peiChartHome.setDrawEntryLabels(true) // Remove entry labels (legends)

//        peiChartHome.description.text= "Pie Chart"
        peiChartHome.centerText="Meal Types"

        peiChartHome.animateY(2000)

        return view
    }


}