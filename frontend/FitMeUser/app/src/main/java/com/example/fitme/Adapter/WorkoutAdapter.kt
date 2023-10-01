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
import com.example.fitme.R

class WorkoutAdapter(
    private val wrkOutCardClicked: (ExerciseItem) -> Unit,
    contexts: Context
):RecyclerView.Adapter<WorkoutAdapterViewHolder>(){
    val conts = contexts
    private val exerciseList = ArrayList<ExerciseItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutAdapterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.workout_item,parent,false)
        return WorkoutAdapterViewHolder(listItem)
    }

    override fun getItemCount(): Int {
        return exerciseList.size
    }

    override fun onBindViewHolder(holder: WorkoutAdapterViewHolder, position: Int) {
        holder.bind(exerciseList[position],wrkOutCardClicked, conts)
    }
    fun setList(exerciseItem: List<ExerciseItem>){
        exerciseList.clear()
        exerciseList.addAll(exerciseItem)
        notifyDataSetChanged()
    }
}
class WorkoutAdapterViewHolder(private val view: View):RecyclerView.ViewHolder(view) {
    fun bind(exerciseItem: ExerciseItem,wrkOutCardClicked:(ExerciseItem)->Unit,context: Context){
        val tvWrkCal = view.findViewById<TextView>(R.id.tvWrkCal)
        val tvWrkFat = view.findViewById<TextView>(R.id.tvWrkFat)
        val tvWrkOutBdyPart = view.findViewById<TextView>(R.id.tvWrkOutBdyPart)
        val tvWorkTargetMuscle = view.findViewById<TextView>(R.id.tvWorkTargetMuscle)
        val tvWrkCarbs = view.findViewById<TextView>(R.id.tvWrkCarbs)
        val tvWrkOutName = view.findViewById<TextView>(R.id.tvWrkOutName)
        val imgWrkThumb = view.findViewById<ImageView>(R.id.imgWrkThumb)
        val cvWrkOutCard = view.findViewById<CardView>(R.id.cvWrkOutCard)

        val imgUrl = exerciseItem.gifUrl.toString()

            tvWrkCal.text = "${ exerciseItem.calories.toString().capitalize() }Kcal 🔥"
            tvWrkFat.text ="Fat ${exerciseItem.fat.toString().capitalize()}% 💧"
            tvWrkCarbs.text ="Carbs  ${ exerciseItem.carbs.toString().capitalize() }% 🍞"
            tvWrkOutName.text = exerciseItem.name.toString().capitalize()
            tvWrkOutBdyPart.text = "Body Part :  ${ exerciseItem.bodyPart.toString().capitalize() }"
            tvWorkTargetMuscle.text = "Target Muscle :  ${ exerciseItem.target.toString().capitalize() }"

        Glide.with(context)
            .load(imgUrl)
            .fitCenter()
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .placeholder(R.drawable.animegife)
            .into(imgWrkThumb)

        cvWrkOutCard.setOnClickListener {
            wrkOutCardClicked(exerciseItem)
        }
    }
}