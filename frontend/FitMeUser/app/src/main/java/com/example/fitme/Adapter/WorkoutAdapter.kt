package com.example.fitme.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.fitme.EntityDao.ExerciseItem
import com.example.fitme.R

class WorkoutAdapter(
    private val wrkOutCardClicked: (ExerciseItem) -> Unit
):RecyclerView.Adapter<WorkoutAdapterViewHolder>(){

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
        holder.bind(exerciseList[position],wrkOutCardClicked)
    }
    fun setList(exerciseItem: List<ExerciseItem>){
        exerciseList.clear()
        exerciseList.addAll(exerciseItem)
        notifyDataSetChanged()
    }
}
class WorkoutAdapterViewHolder(private val view: View):RecyclerView.ViewHolder(view) {
    fun bind(exerciseItem: ExerciseItem,wrkOutCardClicked:(ExerciseItem)->Unit){
        val tvWrkCal = view.findViewById<TextView>(R.id.tvWrkCal)
        val tvWrkFat = view.findViewById<TextView>(R.id.tvWrkFat)
        val tvWrkOutBdyPart = view.findViewById<TextView>(R.id.tvWrkOutBdyPart)
        val tvWorkTargetMuscle = view.findViewById<TextView>(R.id.tvWorkTargetMuscle)
        val tvWrkCarbs = view.findViewById<TextView>(R.id.tvWrkCarbs)
        val tvWrkOutName = view.findViewById<TextView>(R.id.tvWrkOutName)
        val imgWrkThumb = view.findViewById<ImageView>(R.id.imgWrkThumb)
        val cvWrkOutCard = view.findViewById<CardView>(R.id.cvWrkOutCard)

            tvWrkCal.text = "${ exerciseItem.calories.toString().capitalize() }Kcal 🔥"
            tvWrkFat.text ="Fat ${exerciseItem.fat.toString().capitalize()}% 💧"
            tvWrkCarbs.text ="Carbs  ${ exerciseItem.carbs.toString().capitalize() }% 🍞"
            tvWrkOutName.text = exerciseItem.name.toString().capitalize()
            tvWrkOutBdyPart.text = "Body Part :  ${ exerciseItem.bodyPart.toString().capitalize() }"
            tvWorkTargetMuscle.text = "Target Muscle :  ${ exerciseItem.target.toString().capitalize() }"
//        imgWrkThumb.text = appointment.time

        cvWrkOutCard.setOnClickListener {
            wrkOutCardClicked(exerciseItem)
        }
    }
}