package com.example.fitme.Fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.fitme.Activity.EditProfile
import com.example.fitme.Activity.SignIn
import com.example.fitme.DialogAlerts.ProgressLoader
import com.example.fitme.R
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ProfileFragment : Fragment() {

    private lateinit var shimmerScan: ShimmerFrameLayout
    private lateinit var constraintLayout: ConstraintLayout
    private lateinit var cvLogoutUser: CardView
    private lateinit var cvSignUpFb: CardView
    private lateinit var cvProceedToPyyAct: CardView
    private lateinit var progressLoader: ProgressLoader
    private lateinit var fbEditProfile: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_profile, container, false)

        constraintLayout = view.findViewById(R.id.fragmentProfileClayout)
        cvProceedToPyyAct = view.findViewById(R.id.cvProceedToPyyAct)
        shimmerScan = view.findViewById(R.id.shimmerProfileFrag)
        cvLogoutUser = view.findViewById(R.id.cvLogoutUser)
        cvProceedToPyyAct = view.findViewById(R.id.cvProceedToPyyAct)
        fbEditProfile = view.findViewById(R.id.FbEditProfile)
        cvSignUpFb = view.findViewById(R.id.cvSignUpFb)

        shimmerScan.startShimmer()

        // Delay the stop of the shimmer after 1000 milliseconds (1 second)
        Handler(Looper.getMainLooper()).postDelayed({
            shimmerScan.stopShimmer()
            constraintLayout.visibility = View.VISIBLE
            shimmerScan.visibility = View.GONE
        }, 800)
        cvLogoutUser.setOnClickListener {
            logoutUser()
        }
        cvProceedToPyyAct.setOnClickListener {
            logoutUser()
        }
        fbEditProfile.setOnClickListener {
            startActivity(Intent(requireContext(),EditProfile::class.java))
        }
        cvSignUpFb.setOnClickListener {
            startActivity(Intent(requireContext(),EditProfile::class.java))
        }
        return view
    }
    private fun logoutUser(){
        progressLoader = ProgressLoader(
            requireContext(),"Verifying Logout","Please Wait....."
        )
        progressLoader.startProgressLoader()

        Handler(Looper.getMainLooper()).postDelayed({
           progressLoader.dismissProgressLoader()
            val intent = Intent(requireContext(), SignIn::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            requireActivity().finish()
        }, 500)
    }
}