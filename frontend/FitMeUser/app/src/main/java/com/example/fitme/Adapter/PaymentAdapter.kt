package com.example.fitme.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.fitme.EntityDao.PaymentRes
import com.example.fitme.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone

class PaymentAdapter(
    private val paymentCardClicked: (PaymentRes) -> Unit
):RecyclerView.Adapter<PaymentViewHolder>() {
    private val paymentList = ArrayList<PaymentRes>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.payment_item,parent,false)
        return PaymentViewHolder(listItem)
    }

    override fun getItemCount(): Int {
        return paymentList.size
    }

    override fun onBindViewHolder(holder: PaymentViewHolder, position: Int) {
        holder.bind(paymentList[position],paymentCardClicked)
    }
    fun setList(paymentRes: List<PaymentRes>){
        paymentList.clear()
        paymentList.addAll(paymentRes)
        notifyDataSetChanged()
    }
}
class PaymentViewHolder(private val view: View):RecyclerView.ViewHolder(view){
    fun bind(paymentRes: PaymentRes, paymentCardClicked:(PaymentRes)->Unit){
        val tvMainNameDis = view.findViewById<TextView>(R.id.tvMainNameDis)
        val tvForPay = view.findViewById<TextView>(R.id.tvForPay)
        val tvPayTime = view.findViewById<TextView>(R.id.tvPayTime)
        val tvPayDate = view.findViewById<TextView>(R.id.tvPayDate)
        val cvMainPayCard = view.findViewById<CardView>(R.id.cvMainPayCard)

        tvMainNameDis.text = paymentRes.from
        tvPayTime.text = formatTime(paymentRes.created)
        tvPayDate.text = formatDate(paymentRes.created)
        tvForPay.text ="Payment For : ${ paymentRes.forValue.capitalize() }"
        cvMainPayCard.setOnClickListener {
            paymentCardClicked(paymentRes)
        }

    }
    fun formatDate(inputDate: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS'Z'")
        val outputDateFormat = SimpleDateFormat("yyyy.MM.dd")

        // Set the timezone to UTC
        inputFormat.timeZone = TimeZone.getTimeZone("UTC")

        val date: Date = inputFormat.parse(inputDate) ?: Date()

        return outputDateFormat.format(date)
    }
    fun formatTime(inputDate: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS'Z'")
        val outputTimeFormat = SimpleDateFormat("hh:mm a")

        // Set the timezone to UTC
        inputFormat.timeZone = TimeZone.getTimeZone("UTC")

        val date: Date = inputFormat.parse(inputDate) ?: Date()

        return outputTimeFormat.format(date)
    }
}