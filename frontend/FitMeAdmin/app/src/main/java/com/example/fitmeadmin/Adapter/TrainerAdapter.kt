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
import com.example.fitmeadmin.EntityDao.TrainerItem
import com.example.fitmeadmin.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TrainerAdapter(
    private val trainerCardClick: (TrainerItem) -> Unit,
    private val context: Context
) : RecyclerView.Adapter<TrainerAdapter.TrainerAdapterViewHolder>() {

    private val trainerList = ArrayList<TrainerItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainerAdapter.TrainerAdapterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.trainer_list_item, parent, false)
        return TrainerAdapterViewHolder(listItem)
    }
    override fun getItemCount(): Int {
        return trainerList.size
    }

    override fun onBindViewHolder(holder: TrainerAdapterViewHolder, position: Int) {
        holder.bind(trainerList[position], trainerCardClick, context)
    }

    fun setList(trainerItem: List<TrainerItem>) {
        trainerList.clear()
        trainerList.addAll(trainerItem)
        notifyDataSetChanged()
    }

    class TrainerAdapterViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(tarinerItem: TrainerItem, trainerCardClick: (TrainerItem) -> Unit, context: Context) {
            val name = view.findViewById<TextView>(R.id.from)
            val charge = view.findViewById<TextView>(R.id.charge)
            val exp = view.findViewById<TextView>(R.id.experi)
            val imgWrkThumb = view.findViewById<ImageView>(R.id.imageView5)
            val cvTcard = view.findViewById<FloatingActionButton>(R.id.floatingActionButton2)


            val imgUrl = tarinerItem.profile.toString()

            name.text = tarinerItem.name.capitalize()
            charge.text = tarinerItem.charges.toString()
            exp.text = tarinerItem.experience.toString()

            Glide.with(context)
                .load(imgUrl)
                .fitCenter()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .placeholder(R.drawable.animegife)
                .into(imgWrkThumb)

            cvTcard.setOnClickListener {
                trainerCardClick(tarinerItem)
            }
        }
    }


}