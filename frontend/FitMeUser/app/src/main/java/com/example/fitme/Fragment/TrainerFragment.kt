package com.example.fitme.Fragment

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitme.APIServices.MealService
import com.example.fitme.APIServices.TrainerService
import com.example.fitme.Activity.MealDetail
import com.example.fitme.Activity.Reservation
import com.example.fitme.Activity.TrainerDetail
import com.example.fitme.Adapter.MealAdapter
import com.example.fitme.Adapter.TrainerAdapter
import com.example.fitme.Entity.UserRecord
import com.example.fitme.EntityDao.MealItem
import com.example.fitme.EntityDao.MealRes
import com.example.fitme.EntityDao.TrainerItem
import com.example.fitme.EntityDao.TrainerRes
import com.example.fitme.R
import com.example.fitme.RetrofitService.RetrofitService
import com.facebook.shimmer.ShimmerFrameLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TrainerFragment : Fragment() {
    private lateinit var shimmerScan: ShimmerFrameLayout
    private lateinit var fragmentTrainerClayout: ConstraintLayout

    private lateinit var rvTrainerFrag: RecyclerView
    private lateinit var trainerAdapter:TrainerAdapter

    private lateinit var out:UserRecord

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_trainer, container, false)
        out = arguments?.getSerializable("user", UserRecord::class.java)!!


        fragmentTrainerClayout = view.findViewById(R.id.fragmentTrainerClayout)
        shimmerScan = view.findViewById(R.id.shimmerTrainerFrag)

        shimmerScan.startShimmer()
        initRecycler(view)
        return view
    }
    private fun initRecycler(view:View){
        rvTrainerFrag = view.findViewById(R.id.rvTrainerFrag)
        rvTrainerFrag.layoutManager= LinearLayoutManager(requireActivity())
        trainerAdapter = TrainerAdapter ({
                trainerItem: TrainerItem -> trainerCardClicked(trainerItem)
        },{
                trainerItem: TrainerItem -> trainerHireBtnClicked(trainerItem)
        },requireContext() )
        rvTrainerFrag.adapter = trainerAdapter
        fetchDetails()
    }

    private fun trainerHireBtnClicked(trainerItem: TrainerItem) {
        val bundle = Bundle().apply {
            putSerializable("trainer", trainerItem)
            putSerializable("user", out)
        }
        val intent = Intent(requireActivity(), Reservation::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    private fun trainerCardClicked(trainerItem: TrainerItem) {
        val bundle = Bundle().apply {
            putSerializable("trainer", trainerItem)
            putSerializable("user", out)
        }
        val intent = Intent(requireActivity(), TrainerDetail::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    private fun fetchDetails() {
        val retrofitService= RetrofitService()
        val getList =retrofitService.getRetrofit().create(TrainerService::class.java)
        val call: Call<TrainerRes> = getList.getAllTrainers()

        call.enqueue(object : Callback<TrainerRes> {
            override fun onResponse(call: Call<TrainerRes>, response: Response<TrainerRes>) {
                if(response.isSuccessful){
                    if (response.body()!=null){
                        val trainerRes = response.body()
                        val trainerItem = trainerRes?.items
                        trainerAdapter.setList(trainerItem!!)
                        shimmerScan.stopShimmer()
                        shimmerScan.visibility = View.GONE
                        rvTrainerFrag.visibility = View.VISIBLE
                        fragmentTrainerClayout.visibility = View.VISIBLE
                    }
                }else{
                    Toast.makeText(requireContext(),"Invalid response", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<TrainerRes>, t: Throwable) {
                Toast.makeText(requireContext(),"Server Error", Toast.LENGTH_SHORT).show()
            }
        })
    }

}