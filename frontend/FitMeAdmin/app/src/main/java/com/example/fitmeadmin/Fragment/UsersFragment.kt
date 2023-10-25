package com.example.fitmeadmin.Fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitmeadmin.APIServices.UserService
import com.example.fitmeadmin.APIServices.WorkoutService
import com.example.fitmeadmin.Activity.UserDetail
import com.example.fitmeadmin.Activity.WorkoutDetail
import com.example.fitmeadmin.Adapter.UserAdapter
import com.example.fitmeadmin.Adapter.WorkoutAdapter
import com.example.fitmeadmin.EntityDao.ExerciseItem
import com.example.fitmeadmin.EntityDao.UserItem
import com.example.fitmeadmin.EntityDao.UserRes
import com.example.fitmeadmin.EntityDao.WrkOutRes
import com.example.fitmeadmin.R
import com.example.fitmeadmin.RetrofitService.RetrofitService
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsersFragment : Fragment() {
    private  lateinit var barChart: BarChart
    private lateinit var rvUserlist: RecyclerView
    private lateinit var userAdapter: UserAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_users, container, false)

        barChart = view.findViewById(R.id.barChartUsersFrag)
       // rvUser = view.findViewById(R.id.rvU)

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

        initRecyclerView(view)

        return view
    }
    private fun initRecyclerView(view: View) {
        rvUserlist = view.findViewById(R.id.rvU)

        // Use requireContext() to get the context
        rvUserlist.layoutManager = LinearLayoutManager(requireContext())

        userAdapter = UserAdapter({ UserItem ->
            userCardClick(UserItem)
        }, requireContext()) // Pass requireContext() here

        rvUserlist.adapter = userAdapter
        fetchDetails()
    }
    private fun userCardClick(userItem: UserItem) {
        val bundle = Bundle().apply {
            putSerializable("user", userItem)
        }

        val intent = Intent(requireContext(), UserDetail::class.java)
        intent.putExtras(bundle)
        requireActivity().startActivity(intent)
    }

    private fun fetchDetails() {
        val retrofitService = RetrofitService()
        val getList = retrofitService.getRetrofit().create(UserService::class.java)
        val call: Call<UserRes> = getList.getUsers()

        call.enqueue(object : Callback<UserRes> {
            override fun onResponse(call: Call<UserRes>, response: Response<UserRes>) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        val userRes = response.body()
                        val userItem = userRes?.items  //select item
                        userAdapter.setList(userItem!!)
                        // Handle your UI elements here, if needed.
                    }
                } else {
                    // Use requireContext() to get the context
                    Toast.makeText(requireContext(), "Invalid response", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<UserRes>, t: Throwable) {
                // Use requireContext() to get the context
                Toast.makeText(requireContext(), "Server Error", Toast.LENGTH_SHORT).show()
            }
        })
    }

}