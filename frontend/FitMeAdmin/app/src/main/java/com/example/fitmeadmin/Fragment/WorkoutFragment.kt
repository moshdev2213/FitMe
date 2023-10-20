package com.example.fitmeadmin.Fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitmeadmin.APIServices.WorkoutService
import com.example.fitmeadmin.Activity.MealPlans
import com.example.fitmeadmin.Activity.Trainers
import com.example.fitmeadmin.Activity.WorkoutAdd
import com.example.fitmeadmin.Activity.WorkoutDetail
import com.example.fitmeadmin.Adapter.WorkoutAdapter
import com.example.fitmeadmin.EntityDao.ExerciseItem
import com.example.fitmeadmin.EntityDao.WrkOutRes
import com.example.fitmeadmin.R
import com.example.fitmeadmin.RetrofitService.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WorkoutFragment : Fragment() {

    private lateinit var cvTrainerActCard:CardView
    private lateinit var cvopenMealAct:CardView

    private lateinit var rvWrkOutList: RecyclerView
    private lateinit var workoutAdapter: WorkoutAdapter
    private lateinit var addbtn: ImageView
    private lateinit var addworkout: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_workout, container, false)

        cvTrainerActCard = view.findViewById(R.id.cvTrainerActCard)
        cvopenMealAct = view.findViewById(R.id.cvopenMealAct)
        addbtn = view.findViewById(R.id.addbtn)
        addworkout= view.findViewById(R.id.addW)

        cvTrainerActCard.setOnClickListener {
            startActivity(Intent(requireContext(),Trainers::class.java))
        }
        cvopenMealAct.setOnClickListener {
            startActivity(Intent(requireContext(),MealPlans::class.java))
        }
        addbtn.setOnClickListener {
            startActivity(Intent(requireContext(),MealPlans::class.java))
        }

        addworkout.setOnClickListener {
            startActivity(Intent(requireContext(),WorkoutAdd::class.java))
        }

        rvWrkOutList = view.findViewById(R.id.rvWrkOutList)

//        imgBackBtn3.setOnClickListener {
////            finish()
//        }
        initRecyclerView(view)

        return  view
    }

    private fun initRecyclerView(view: View) {
        rvWrkOutList = view.findViewById(R.id.rvWrkOutList)

        // Use requireContext() to get the context
        rvWrkOutList.layoutManager = LinearLayoutManager(requireContext())

        workoutAdapter = WorkoutAdapter({ exerciseItem ->
            wrkOutCardClicked(exerciseItem)
        }, requireContext()) // Pass requireContext() here

        rvWrkOutList.adapter = workoutAdapter
        fetchDetails()
    }

    private fun wrkOutCardClicked(exerciseItem: ExerciseItem) {
        val bundle = Bundle().apply {
            putSerializable("exercise", exerciseItem)
        }

        val intent = Intent(requireContext(), WorkoutDetail::class.java)
        intent.putExtras(bundle)
        requireActivity().startActivity(intent)
    }

    private fun fetchDetails() {
        val retrofitService = RetrofitService()
        val getList = retrofitService.getRetrofit().create(WorkoutService::class.java)
        val call: Call<WrkOutRes> = getList.getAllWorkouts()

        call.enqueue(object : Callback<WrkOutRes> {
            override fun onResponse(call: Call<WrkOutRes>, response: Response<WrkOutRes>) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        val wrkOutRes = response.body()
                        val exerciseItem = wrkOutRes?.items  //select item
                        workoutAdapter.setList(exerciseItem!!)
                        // Handle your UI elements here, if needed.
                    }
                } else {
                    // Use requireContext() to get the context
                    Toast.makeText(requireContext(), "Invalid response", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<WrkOutRes>, t: Throwable) {
                // Use requireContext() to get the context
                Toast.makeText(requireContext(), "Server Error", Toast.LENGTH_SHORT).show()
            }
        })
    }





}