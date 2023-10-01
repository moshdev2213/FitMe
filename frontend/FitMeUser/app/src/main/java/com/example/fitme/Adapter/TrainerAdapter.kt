package com.example.fitme.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.fitme.Activity.Reservation
import com.example.fitme.Activity.TrainerDetail
import com.example.fitme.EntityDao.MealItem
import com.example.fitme.EntityDao.TrainerItem
import com.example.fitme.R

class TrainerAdapter(
    private val trainerCardClicked: (TrainerItem) -> Unit,
    private val trainerHireBtnClicked: (TrainerItem) -> Unit,
    contexts: Context
): RecyclerView.Adapter<TrainerAdapterViewHolder>() {

    val conts = contexts
    private val trainerList = ArrayList<TrainerItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainerAdapterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.trainer_item,parent,false)
        return TrainerAdapterViewHolder(listItem)
    }

    override fun getItemCount(): Int {
        return trainerList.size
    }

    override fun onBindViewHolder(holder: TrainerAdapterViewHolder, position: Int) {
        holder.bind(trainerList[position],trainerCardClicked,trainerHireBtnClicked, conts)
    }

    fun setList(trainerItem: List<TrainerItem>){
        trainerList.clear()
        trainerList.addAll(trainerItem)
        notifyDataSetChanged()
    }
}
class TrainerAdapterViewHolder(private val view: View):RecyclerView.ViewHolder(view) {
    fun bind(trainerItem: TrainerItem,trainerCardClicked:(TrainerItem)->Unit,trainerHireBtnClicked:(TrainerItem)->Unit,context: Context){
        val cvTrainerClickDetail = view.findViewById<CardView>(R.id.cvTrainerClickDetail)
        val btnHireTrainer = view.findViewById<Button>(R.id.btnHireTrainer)
        val btnDetailTrainer = view.findViewById<Button>(R.id.btnDetailTrainer)
        val tvTrainerExp = view.findViewById<TextView>(R.id.tvTrainerExp)
        val tvTrainerGender = view.findViewById<TextView>(R.id.tvTrainerGender)
        val tvTrainerAddress = view.findViewById<TextView>(R.id.tvTrainerAddress)
        val tvTrainerEmail = view.findViewById<TextView>(R.id.tvTrainerEmail)
        val tvTrainerName = view.findViewById<TextView>(R.id.tvTrainerName)
        val imgTrainerPic = view.findViewById<ImageView>(R.id.imgTrainerPic)

        tvTrainerExp.text = "${ trainerItem.experience } years"
        tvTrainerGender.text = trainerItem.gender.capitalize()
        tvTrainerAddress.text = "Add: ${ trainerItem.location.capitalize() }"
        tvTrainerEmail.text = "Email: ${ trainerItem.email }"
        tvTrainerName.text = "Name: ${ trainerItem.name.capitalize() }"

        Glide.with(context)
            .load(trainerItem.profile)
            .fitCenter()
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .placeholder(R.drawable.animegife)
            .into(imgTrainerPic)

        cvTrainerClickDetail.setOnClickListener {
            trainerCardClicked(trainerItem)
        }
        btnDetailTrainer.setOnClickListener {
            trainerCardClicked(trainerItem)
        }
        btnHireTrainer.setOnClickListener {
            trainerHireBtnClicked(trainerItem)
        }
    }
}