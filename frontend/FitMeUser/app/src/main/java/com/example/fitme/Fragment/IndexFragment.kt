package com.example.fitme.Fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.fitme.Activity.ScanBody
import com.example.fitme.EntityDao.ExerciseItem
import com.example.fitme.R
import com.facebook.shimmer.ShimmerFrameLayout


class IndexFragment : Fragment() {
    private lateinit var shimmerScan: ShimmerFrameLayout
    private lateinit var constraintLayout: ConstraintLayout
    private lateinit var btnBrowseWrkout: Button
    private lateinit var btnScanBody:Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_index, container, false)

        constraintLayout = view.findViewById(R.id.fragmentMainClayout)
        shimmerScan = view.findViewById(R.id.shimmerIndexFrag)
        btnScanBody = view.findViewById(R.id.btnScanBody)

        shimmerScan.startShimmer()

        // Delay the stop of the shimmer after 1000 milliseconds (1 second)
        Handler(Looper.getMainLooper()).postDelayed({
            shimmerScan.stopShimmer()
            constraintLayout.visibility = View.VISIBLE
            shimmerScan.visibility = View.GONE
        }, 1000)

        btnScanBody.setOnClickListener {
            startActivity(Intent(requireActivity(),ScanBody::class.java))
        }
        return view
    }

}