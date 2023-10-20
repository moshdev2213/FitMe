package com.example.fitmeadmin.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.fitmeadmin.EntityDao.MealItem
import com.example.fitmeadmin.R

class MealAdapter(
    private val mealCardClicked: (MealItem) -> Unit,
    private val context: Context
) : RecyclerView.Adapter<MealAdapter.MealAdapterViewHolder>() {
    private val mealList = ArrayList<MealItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealAdapterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.meal_plan_list_item, parent, false)
        return MealAdapterViewHolder(listItem)
    }

    override fun getItemCount(): Int {
        return mealList.size
    }

    override fun onBindViewHolder(holder: MealAdapterViewHolder, position: Int) {
        holder.bind(mealList[position], mealCardClicked, context)
    }

    fun setList(mealItem: List<MealItem>) {
        mealList.clear()
        mealList.addAll(mealItem)
        notifyDataSetChanged()
    }

    class MealAdapterViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(mealItem: MealItem, mealCardClicked: (MealItem) -> Unit, context: Context) {
            val name = view.findViewById<TextView>(R.id.textView56)
            val price = view.findViewById<TextView>(R.id.textView58)
            val imgWrkThumb = view.findViewById<ImageView>(R.id.imageView10)
            val cvMealCard = view.findViewById<CardView>(R.id.mealcard)

            val imgUrl = mealItem.bannerImg.toString()

            name.text = mealItem.name.capitalize()
            price.text = mealItem.price.toString()

            Glide.with(context)
                .load(imgUrl)
                .fitCenter()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .placeholder(R.drawable.animegife)
                .into(imgWrkThumb)

            cvMealCard.setOnClickListener {
                mealCardClicked(mealItem)
            }
        }
    }
}
