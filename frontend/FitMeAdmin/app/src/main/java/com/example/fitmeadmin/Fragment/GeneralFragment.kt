package com.example.fitmeadmin.Fragment

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fitmeadmin.R
import com.github.mikephil.charting.charts.RadarChart
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry
import com.github.mikephil.charting.utils.ColorTemplate

class GeneralFragment : Fragment() {

    private lateinit var radarChart :RadarChart
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_general, container, false)

        radarChart= view.findViewById(R.id.radar_chart)

        val list:ArrayList<RadarEntry> = ArrayList()

        list.add(RadarEntry(100f))
        list.add(RadarEntry(101f))
        list.add(RadarEntry(102f))
        list.add(RadarEntry(103f))
        list.add(RadarEntry(104f))

        val radarDataSet= RadarDataSet(list,"List")

        radarDataSet.setColors(ColorTemplate.MATERIAL_COLORS,255)

        radarDataSet.lineWidth=2f

        radarDataSet.valueTextColor = Color.RED

        radarDataSet.valueTextSize=14f

        val radarData= RadarData()

        radarData.addDataSet(radarDataSet)

        radarChart.data=radarData


        radarChart.description.text= "Radar Chart"


        radarChart.animateY(2000)


        return  view
    }

}