package com.example.fitme.Activity

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.example.fitme.APIServices.AIVisionService
import com.example.fitme.EntityDao.PredictionResponse
import com.example.fitme.R
import com.example.fitme.RealPathUtil.RealPathUtil
import com.example.fitme.RetrofitService.AzureVisionService
import com.facebook.shimmer.ShimmerFrameLayout
import com.github.dhaval2404.imagepicker.ImagePicker
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class ScanBody : AppCompatActivity() {
    private lateinit var shimmerScan: ShimmerFrameLayout
    private lateinit var imgPlaceHolderScan:ImageView
    private lateinit var cvBodyScanMain:CardView
    private lateinit var cvScanMeals:CardView
    private lateinit var cvScanTrainee:CardView
    private lateinit var cvScanAgainPic:CardView
    private lateinit var vFitSuggestion01:TextView
    private lateinit var tvFitSuggest02:TextView
    private lateinit var imgFitSuggest03:ImageView

    private lateinit var btnScanImage:Button
    private lateinit var btngoBackScan:Button

    private lateinit var tvBodyScanTime:TextView
    private lateinit var tvBodyScanDate:TextView
    private lateinit var tvProbRate:TextView
    private lateinit var tvScanImgType:TextView
    private lateinit var tvScanBodyType:TextView
    private lateinit var imgScanPreview:ImageView

    private lateinit var imgArrowBackBtn:ImageView

    private lateinit var path:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_body)

        shimmerScan = findViewById(R.id.shimmerActivityEyeSCan)
        imgPlaceHolderScan = findViewById(R.id.imgPlaceHolderScan)
        cvBodyScanMain = findViewById(R.id.cvBodyScanMain)
        cvScanMeals = findViewById(R.id.cvScanMeals)
        cvScanTrainee = findViewById(R.id.cvScanTrainee)
        cvScanAgainPic = findViewById(R.id.cvScanAgainPic)
        vFitSuggestion01 = findViewById(R.id.vFitSuggestion01)
        tvFitSuggest02 = findViewById(R.id.tvFitSuggest02)
        imgFitSuggest03 = findViewById(R.id.imgFitSuggest03)

        btnScanImage = findViewById(R.id.btnScanImage)
        btngoBackScan = findViewById(R.id.btngoBackScan)

        tvBodyScanTime = findViewById(R.id.tvBodyScanTime)
        tvBodyScanDate = findViewById(R.id.tvBodyScanDate)
        tvProbRate = findViewById(R.id.tvProbRate)
        tvScanImgType = findViewById(R.id.tvScanImgType)
        tvScanBodyType = findViewById(R.id.tvScanBodyType)
        imgScanPreview = findViewById(R.id.imgScanPreview)

        imgArrowBackBtn = findViewById(R.id.imgArrowBackBtn)

        imgArrowBackBtn.setOnClickListener {
            finish()
        }
        btngoBackScan.setOnClickListener {
            finish()
        }
        makeViewsGone()
        cvScanAgainPic.visibility=View.VISIBLE
        imgPlaceHolderScan.visibility=View.VISIBLE

        btnScanImage.setOnClickListener {

            ImagePicker.with(this@ScanBody)
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        imgScanPreview.setImageURI(data?.data)


        val outputDateFormat = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault())
        val date = Date() // Replace this with your desired date
        val formattedDate = outputDateFormat.format(date)
        tvBodyScanDate.text = formattedDate

        val outputTimeFormat = SimpleDateFormat("hh.mm a", Locale.getDefault())

        val currentTime = Calendar.getInstance().time
        val formattedTime = outputTimeFormat.format(currentTime)
        tvBodyScanTime.text = formattedTime

        if (data != null) {
            path = data.data?.let {
                RealPathUtil().getRealPath(this@ScanBody, it).toString()
            }.toString()

        }
        shimmerScan.startShimmer()
        shimmerScan.visibility = View.VISIBLE
        imgPlaceHolderScan.visibility=View.GONE
        makeViewsGone()
        scanBodyImage()
    }
    private fun scanBodyImage(){
        val file = File(path)
        val reqBody = RequestBody.create(MediaType.parse("multipart/form-data"),file)
        val body = MultipartBody.Part.createFormData("image",file.name,reqBody)
        val predictionKey = "5db61c6ea94645959688878437a3720f"


        val azureVisionService=AzureVisionService()
        val scanApi = azureVisionService.getRetrofit().create(AIVisionService::class.java)

        val call: Call<PredictionResponse> = scanApi.scanBody(predictionKey,body)
        call.enqueue(object : Callback<PredictionResponse> {
            override fun onResponse(call: Call<PredictionResponse>, response: Response<PredictionResponse>) {
                println(response)
                if(response.isSuccessful){
                    if (response.body()!=null){
                        shimmerScan.stopShimmer()
                        shimmerScan.visibility = View.GONE
                        makeViewsVisible()
                        populateViews(response)
                    }
                }else{
                    Toast.makeText(this@ScanBody,"Invalid response", Toast.LENGTH_SHORT).show()
                    makeViewsGone()
                    shimmerScan.stopShimmer()
                    shimmerScan.visibility = View.GONE
                    cvScanAgainPic.visibility=View.VISIBLE
                    imgPlaceHolderScan.visibility=View.VISIBLE
                }
            }

            override fun onFailure(call: Call<PredictionResponse>, t: Throwable) {
                Toast.makeText(this@ScanBody,"Failed", Toast.LENGTH_SHORT).show()
                makeViewsGone()
                shimmerScan.stopShimmer()
                shimmerScan.visibility = View.GONE
                cvScanAgainPic.visibility=View.VISIBLE
                imgPlaceHolderScan.visibility=View.VISIBLE
            }
        })
    }
    private fun makeViewsVisible(){
        cvBodyScanMain.visibility = View.VISIBLE
        cvScanMeals.visibility = View.VISIBLE
        cvScanTrainee.visibility = View.VISIBLE
        cvScanAgainPic.visibility = View.VISIBLE
        vFitSuggestion01.visibility = View.VISIBLE
        tvFitSuggest02.visibility = View.VISIBLE
        imgFitSuggest03.visibility = View.VISIBLE
    }
    private fun makeViewsGone(){
        cvBodyScanMain.visibility = View.GONE
        cvScanMeals.visibility = View.GONE
        cvScanTrainee.visibility = View.GONE
        cvScanAgainPic.visibility = View.GONE
        vFitSuggestion01.visibility = View.GONE
        tvFitSuggest02.visibility = View.GONE
        imgFitSuggest03.visibility = View.GONE
    }
    private fun populateViews(response: Response<PredictionResponse>){
        val predictions= response.body()?.predictions

        // Get the first three predictions
        val topThreePredictions = predictions?.take(3)

        if(topThreePredictions!=null){
            // Filter out disease with specific tag names
            val tagNamesToDisplay  = listOf(
                "malnutrition",
                "obesity",
                "fitbody"
            )

            val tagPrintNames = mapOf(
                "malnutrition" to "Malnutrition",
                "obesity" to "Obesity",
                "fitbody" to "Fit Body"
            )

            // Filter and sort predictions by highest probability
            val sortedPredictions = topThreePredictions
                .filter { it.tagName in tagNamesToDisplay }
                .sortedByDescending { it.probability.toDouble() }

            var type = ""
            var riskRange=""
            for((index,predict) in sortedPredictions.withIndex()){
                val probability = predict.probability.toDouble() * 100
                val printname = tagPrintNames[predict.tagName]

                if (index == 0) {
                    type = when {
                        predict.tagName.endsWith("_scan", ignoreCase = true) -> "Scanned"
                        predict.tagName.endsWith("_pic", ignoreCase = true) -> "Not Scanned"
                        else -> "Not Found"
                    }
                    riskRange = when{
                        probability< 45 ->"Low"
                        probability< 75 ->"Moderate"
                        else ->"High"
                    }
                    tvProbRate.text = "${String.format("%.4f", probability)} %"
                    tvScanBodyType.text = printname
                    tvScanImgType.text = type
                }

                // Set the probabilities to the TextViews
                val textViewProbabiltyResourceId = resources.getIdentifier("tvPredictRate${String.format("%02d", index+1)}", "id", packageName)
                val tvProb = findViewById<TextView>(textViewProbabiltyResourceId)
                tvProb.text = "${String.format("%.4f", probability)} %"

                val textViewNameResourceId = resources.getIdentifier("tvPredictDisName${String.format("%02d", index+1)}", "id", packageName)
                val tvDisName = findViewById<TextView>(textViewNameResourceId)
                tvDisName.text = printname

                val textColor =when(riskRange){
                    "Low" -> Color.GREEN
//                    "Moderate" -> Color.YELLOW
                    "Moderate" -> ContextCompat.getColor(this@ScanBody, R.color.bg_warning)
                    "High" -> Color.RED
                    else -> Color.GREEN
                }
                tvProb.setTextColor(textColor)
                tvProbRate.setTextColor(textColor)
            }
        }
    }
}