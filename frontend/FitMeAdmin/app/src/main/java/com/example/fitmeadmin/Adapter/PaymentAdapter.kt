package com.example.fitmeadmin.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.fitmeadmin.EntityDao.PaymentItem
import com.example.fitmeadmin.EntityDao.UserItem
import com.example.fitmeadmin.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class PaymentAdapter (
    private val paymentCardClick: (PaymentItem) -> Unit,
    private val context: Context
): RecyclerView.Adapter<PaymentAdapter.PaymentAdapterViewHolder>(){

    private val paymentlist = ArrayList<PaymentItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentAdapter.PaymentAdapterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.payment_list_item, parent, false)
        return PaymentAdapter.PaymentAdapterViewHolder(listItem)
    }

    override fun getItemCount(): Int {
        return paymentlist.size
    }

    override fun onBindViewHolder(holder: PaymentAdapter.PaymentAdapterViewHolder, position: Int) {
        holder.bind(paymentlist[position], paymentCardClick, context)
    }

    fun setList(paymentItem: List<PaymentItem>) {
        paymentlist.clear()
        paymentlist.addAll(paymentItem)
        notifyDataSetChanged()
    }

    class PaymentAdapterViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(paymentItem: PaymentItem, paymentCardClick: (PaymentItem) -> Unit, context: Context) {
            val cardName = view.findViewById<TextView>(R.id.from)
            val email = view.findViewById<TextView>(R.id.charge)
            val tel = view.findViewById<TextView>(R.id.experi)
            //val imgWrkThumb = view.findViewById<ImageView>(R.id.imageView5)
            val cvTcard = view.findViewById<FloatingActionButton>(R.id.floatingActionButton2)




            cardName.text = paymentItem.cardName
            email.text = paymentItem.from
            tel.text = paymentItem.forValue



            cvTcard.setOnClickListener {
                paymentCardClick(paymentItem)
            }
        }
    }
}