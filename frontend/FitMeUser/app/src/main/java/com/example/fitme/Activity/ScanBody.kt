package com.example.fitme.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.fitme.R
import com.facebook.shimmer.ShimmerFrameLayout

class ScanBody : AppCompatActivity() {
    private lateinit var shimmerScan: ShimmerFrameLayout
    private lateinit var imgPlaceHolderScan:ImageView
    private lateinit var cvBodyScanMain:CardView
    private lateinit var cvScanMeals:CardView
    private lateinit var cvScanTrainee:CardView
    private lateinit var cvScanAgainPic:CardView
    private lateinit var tvFitSuggest01:TextView
    private lateinit var tvFitSuggest02:TextView
    private lateinit var imgFitSuggest03:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_body)

        shimmerScan = findViewById(R.id.shimmerActivityEyeSCan)
        imgPlaceHolderScan = findViewById(R.id.imgPlaceHolderScan)
        cvBodyScanMain = findViewById(R.id.cvBodyScanMain)
        cvScanMeals = findViewById(R.id.cvScanMeals)
        cvScanTrainee = findViewById(R.id.cvScanTrainee)
        cvScanAgainPic = findViewById(R.id.cvScanAgainPic)
        tvFitSuggest01 = findViewById(R.id.tvFitSuggest01)
        tvFitSuggest02 = findViewById(R.id.tvFitSuggest02)
        imgFitSuggest03 = findViewById(R.id.imgFitSuggest03)

        shimmerScan.startShimmer()

        // Delay the stop of the shimmer after 1000 milliseconds (1 second)
        Handler(Looper.getMainLooper()).postDelayed({
            shimmerScan.stopShimmer()
            makeViewsVisible()
            shimmerScan.visibility = View.GONE
        }, 1000)
    }
    private fun makeViewsVisible(){
        cvBodyScanMain.visibility = View.VISIBLE
        cvScanMeals.visibility = View.VISIBLE
        cvScanTrainee.visibility = View.VISIBLE
        cvScanAgainPic.visibility = View.VISIBLE
        tvFitSuggest01.visibility = View.VISIBLE
        tvFitSuggest02.visibility = View.VISIBLE
        imgFitSuggest03.visibility = View.VISIBLE
    }
}