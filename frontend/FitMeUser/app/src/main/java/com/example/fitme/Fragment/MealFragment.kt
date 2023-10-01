package com.example.fitme.Fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitme.APIServices.MealService
import com.example.fitme.APIServices.WorkoutService
import com.example.fitme.Activity.MealDetail
import com.example.fitme.Activity.WorkoutDetail
import com.example.fitme.Adapter.MealAdapter
import com.example.fitme.Adapter.WorkoutAdapter
import com.example.fitme.EntityDao.ExerciseItem
import com.example.fitme.EntityDao.MealItem
import com.example.fitme.EntityDao.MealRes
import com.example.fitme.EntityDao.WrkOutRes
import com.example.fitme.R
import com.example.fitme.RetrofitService.RetrofitService
import com.facebook.shimmer.ShimmerFrameLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealFragment : Fragment() {
    private lateinit var shimmerScan: ShimmerFrameLayout
    private lateinit var fragmentMealClayout: ConstraintLayout

    private lateinit var rvMealFrag: RecyclerView
    private lateinit var mealAdapter: MealAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_meal, container, false)

        fragmentMealClayout = view.findViewById(R.id.fragmentMealClayout)
        shimmerScan = view.findViewById(R.id.shimmerMealFrag)

        shimmerScan.startShimmer()
        initRecycler(view)
        return view
    }
    private fun initRecycler(view:View){
        rvMealFrag = view.findViewById(R.id.rvMealFrag)
        rvMealFrag.layoutManager= LinearLayoutManager(requireActivity())
        mealAdapter = MealAdapter ({
                mealItem: MealItem -> mealCardClicked(mealItem)
        },requireContext() )
        rvMealFrag.adapter = mealAdapter
        fetchDetails()
    }
    private fun mealCardClicked(mealItem: MealItem) {
        val bundle = Bundle().apply {
            putSerializable("meal", mealItem)
        }
        val intent = Intent(requireActivity(), MealDetail::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }
    private fun fetchDetails() {
        val retrofitService= RetrofitService()
        val getList =retrofitService.getRetrofit().create(MealService::class.java)
        val call: Call<MealRes> = getList.getAllMeals()

        call.enqueue(object : Callback<MealRes> {
            override fun onResponse(call: Call<MealRes>, response: Response<MealRes>) {
                if(response.isSuccessful){
                    if (response.body()!=null){
                        val mealRes = response.body()
                        val mealItem = mealRes?.items
                        mealAdapter.setList(mealItem!!)
                        shimmerScan.stopShimmer()
                        shimmerScan.visibility = View.GONE
                        rvMealFrag.visibility = View.VISIBLE
                        fragmentMealClayout.visibility = View.VISIBLE
                    }
                }else{
                    Toast.makeText(requireContext(),"Invalid response", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<MealRes>, t: Throwable) {
                Toast.makeText(requireContext(),"Server Error", Toast.LENGTH_SHORT).show()
            }
        })
    }
}