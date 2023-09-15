package com.example.fitmeadmin.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import com.example.fitmeadmin.Activity.Trainers
import com.example.fitmeadmin.R

class WorkoutFragment : Fragment() {

    private lateinit var cvTrainerActCard:CardView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_workout, container, false)

        cvTrainerActCard = view.findViewById(R.id.cvTrainerActCard)
        cvTrainerActCard.setOnClickListener {
            startActivity(Intent(requireContext(),Trainers::class.java))
        }

        return  view
    }


}