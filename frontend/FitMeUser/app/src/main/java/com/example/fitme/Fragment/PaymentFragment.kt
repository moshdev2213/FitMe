package com.example.fitme.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitme.APIServices.MealService
import com.example.fitme.APIServices.PaymentService
import com.example.fitme.Adapter.MealAdapter
import com.example.fitme.Adapter.PaymentAdapter
import com.example.fitme.Entity.UserRecord
import com.example.fitme.EntityDao.MealItem
import com.example.fitme.EntityDao.MealRes
import com.example.fitme.EntityDao.PayResAll
import com.example.fitme.EntityDao.PaymentRes
import com.example.fitme.R
import com.example.fitme.RetrofitService.RetrofitService
import com.facebook.shimmer.ShimmerFrameLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PaymentFragment : Fragment() {
    private lateinit var shimmerPaymentFrag: ShimmerFrameLayout
    private lateinit var fragmentPaymentClayout: ConstraintLayout

    private lateinit var rvPaymentFrag: RecyclerView
    private lateinit var paymentAdapter: PaymentAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_payment, container, false)

        fragmentPaymentClayout = view.findViewById(R.id.fragmentPaymentClayout)
        shimmerPaymentFrag = view.findViewById(R.id.shimmerPaymentFrag)

        shimmerPaymentFrag.startShimmer()
        initRecycler(view)
        return view
    }
    private fun initRecycler(view:View){
        rvPaymentFrag = view.findViewById(R.id.rvPaymentFrag)
        rvPaymentFrag.layoutManager= LinearLayoutManager(requireActivity())
        paymentAdapter = PaymentAdapter { paymentRes: PaymentRes ->
            payCardClicked(paymentRes)
        }
        rvPaymentFrag.adapter = paymentAdapter
        fetchDetails()
    }

    private fun fetchDetails() {
        val retrofitService= RetrofitService()
        val getList =retrofitService.getRetrofit().create(PaymentService::class.java)
        val call: Call<PayResAll> = getList.getAllPayments()

        call.enqueue(object : Callback<PayResAll> {
            override fun onResponse(call: Call<PayResAll>, response: Response<PayResAll>) {
                if(response.isSuccessful){
                    if (response.body()!=null){
                        val payRes = response.body()
                        val payItem = payRes?.items
                        paymentAdapter.setList(payItem!!)
                        shimmerPaymentFrag.stopShimmer()
                        shimmerPaymentFrag.visibility = View.GONE
                        rvPaymentFrag.visibility = View.VISIBLE
                        fragmentPaymentClayout.visibility = View.VISIBLE
                    }
                }else{
                    Toast.makeText(requireContext(),"Invalid response", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<PayResAll>, t: Throwable) {
                Toast.makeText(requireContext(),"Server Error", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun payCardClicked(paymentRes: PaymentRes) {
        Toast.makeText(requireContext(),"Under Dev",Toast.LENGTH_SHORT).show()
    }

}