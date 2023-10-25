package com.example.fitmeadmin.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitmeadmin.APIServices.PaymentService
import com.example.fitmeadmin.APIServices.UserService
import com.example.fitmeadmin.Activity.UserDetail
import com.example.fitmeadmin.Adapter.PaymentAdapter
import com.example.fitmeadmin.Adapter.UserAdapter
import com.example.fitmeadmin.EntityDao.PaymentItem
import com.example.fitmeadmin.EntityDao.PaymentRes
import com.example.fitmeadmin.EntityDao.UserItem
import com.example.fitmeadmin.EntityDao.UserRes
import com.example.fitmeadmin.R
import com.example.fitmeadmin.RetrofitService.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PaymentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PaymentFragment : Fragment() {

    private lateinit var rvPaylist: RecyclerView
    private lateinit var paymentAdapter: PaymentAdapter
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_payment, container, false)

        initRecyclerView(view)
        return view



    }


    private fun initRecyclerView(view: View) {
        rvPaylist = view.findViewById(R.id.rvPay)

        // Use requireContext() to get the context
        rvPaylist.layoutManager = LinearLayoutManager(requireContext())

        paymentAdapter = PaymentAdapter({ PaymentItem ->
            paymentCardClick(PaymentItem)
        }, requireContext()) // Pass requireContext() here

        rvPaylist.adapter = paymentAdapter
        fetchDetails()
    }

    private fun paymentCardClick(paymentItem: PaymentItem) {
//        val bundle = Bundle().apply {
//            putSerializable("payment", paymentItem)
//        }
//
//        val intent = Intent(requireContext(), UserDetail::class.java)
//        intent.putExtras(bundle)
//        requireActivity().startActivity(intent)
    }

    private fun fetchDetails() {
        val retrofitService = RetrofitService()
        val getList = retrofitService.getRetrofit().create(PaymentService::class.java)
        val call: Call<PaymentRes> = getList.getAllPayment()

        call.enqueue(object : Callback<PaymentRes> {
            override fun onResponse(call: Call<PaymentRes>, response: Response<PaymentRes>) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        val payRes = response.body()
                        val payItem = payRes?.items  //select item
                        paymentAdapter.setList(payItem!!)
                        // Handle your UI elements here, if needed.
                    }
                } else {
                    // Use requireContext() to get the context
                    Toast.makeText(requireContext(), "Invalid response", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<PaymentRes>, t: Throwable) {
                // Use requireContext() to get the context
                Toast.makeText(requireContext(), "Server Error", Toast.LENGTH_SHORT).show()
            }
        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PaymentFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PaymentFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}