package com.example.fitme.Adapter

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
import com.example.fitme.EntityDao.ExerciseItem
import com.example.fitme.EntityDao.MealItem
import com.example.fitme.R

class MealAdapter(
    private val mealCardClicked: (MealItem) -> Unit,
    contexts: Context
): RecyclerView.Adapter<MealAdapterViewHolder>() {
    val conts = contexts
    private val mealList = ArrayList<MealItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealAdapterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.meal_item,parent,false)
        return MealAdapterViewHolder(listItem)
    }

    override fun getItemCount(): Int {
        return mealList.size
    }

    override fun onBindViewHolder(holder: MealAdapterViewHolder, position: Int) {
        holder.bind(mealList[position],mealCardClicked, conts)
    }
    fun setList(mealItem: List<MealItem>){
        mealList.clear()
        mealList.addAll(mealItem)
        notifyDataSetChanged()
    }
}

class MealAdapterViewHolder(private val view: View):RecyclerView.ViewHolder(view) {
    fun bind(mealItem: MealItem,mealCardClicked:(MealItem)->Unit,context: Context){
        val tvMealDesc = view.findViewById<TextView>(R.id.tvMealDesc)
        val tvPriceMeal = view.findViewById<TextView>(R.id.tvPriceMeal)
        val tvMealName = view.findViewById<TextView>(R.id.tvMealName)
        val imgThumbMeal = view.findViewById<ImageView>(R.id.imgThumbMeal)
        val cvMealItemCard = view.findViewById<CardView>(R.id.cvMealItemCard)

        val imgUrl = mealItem.bannerImg

        tvMealDesc.text = mealItem.description.capitalize()
        tvPriceMeal.text = "Rs.${mealItem.price}"
        tvMealName.text = mealItem.name.capitalize()

        Glide.with(context)
            .load(imgUrl)
            .fitCenter()
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .placeholder(R.drawable.animegife)
            .into(imgThumbMeal)

        cvMealItemCard.setOnClickListener {
            mealCardClicked(mealItem)
        }
    }

}