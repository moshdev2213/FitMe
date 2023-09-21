package com.example.fitmeadmin.Fragment

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.cardview.widget.CardView
import com.example.fitmeadmin.Activity.ProfileActivity
import com.example.fitmeadmin.R
import com.github.mikephil.charting.charts.RadarChart
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry
import com.github.mikephil.charting.utils.ColorTemplate

class GeneralFragment : Fragment() {

    private lateinit var radarChart :RadarChart
    private lateinit var cvDeleteAct :CardView
    private lateinit var cvHandleProfile :CardView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_general, container, false)

        radarChart= view.findViewById(R.id.radar_chart)
        cvDeleteAct= view.findViewById(R.id.cvDeleteActTest)
        cvHandleProfile= view.findViewById(R.id.cvHandleProfile)

        cvHandleProfile.setOnClickListener {
            startActivity(Intent(requireContext(),ProfileActivity::class.java))
        }

        cvDeleteAct.setOnClickListener {
            val dialog = Dialog(requireActivity())
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.botom_sheet_layout)

            dialog.show()
            dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.window?.setGravity(Gravity.BOTTOM)
        }

        val list:ArrayList<RadarEntry> = ArrayList()

        list.add(RadarEntry(100f))
        list.add(RadarEntry(101f))
        list.add(RadarEntry(102f))
        list.add(RadarEntry(103f))
        list.add(RadarEntry(104f))

        val radarDataSet= RadarDataSet(list,"server logs")

        radarDataSet.setColors(ColorTemplate.MATERIAL_COLORS,255)

        radarDataSet.lineWidth=3f

        radarDataSet.valueTextColor = Color.RED
        radarDataSet.valueTextSize=0f

        val radarData= RadarData()

        radarData.addDataSet(radarDataSet)

        radarChart.data=radarData
        radarChart.description.isEnabled = false // Disable chart description

        radarChart.animateY(2000)


        return  view
    }

}