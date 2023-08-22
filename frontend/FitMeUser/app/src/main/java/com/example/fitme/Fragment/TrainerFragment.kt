package com.example.fitme.Fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.fitme.R
import com.facebook.shimmer.ShimmerFrameLayout

class TrainerFragment : Fragment() {
    private lateinit var shimmerScan: ShimmerFrameLayout
    private lateinit var constraintLayout: ConstraintLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_trainer, container, false)

        constraintLayout = view.findViewById(R.id.fragmentTrainerClayout)
        shimmerScan = view.findViewById(R.id.shimmerTrainerFrag)

        shimmerScan.startShimmer()

        // Delay the stop of the shimmer after 1000 milliseconds (1 second)
        Handler(Looper.getMainLooper()).postDelayed({
            shimmerScan.stopShimmer()
            constraintLayout.visibility = View.VISIBLE
            shimmerScan.visibility = View.GONE
        }, 800)

        return view
    }


}