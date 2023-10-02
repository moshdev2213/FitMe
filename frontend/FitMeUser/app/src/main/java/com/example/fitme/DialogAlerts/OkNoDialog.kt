package com.example.fitme.DialogAlerts

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.TextView
import com.example.fitme.R

class OkNoDialog(
    private val context: Context
) {
    private var dialog = Dialog(context)
    private lateinit var  tvDescription : TextView
    private lateinit var btnDialogSuccess : Button

    fun dialogWithSuccess(description: String,onDismiss: () -> Unit) {

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.ok_no_dialog)
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.setGravity(Gravity.BOTTOM)
        tvDescription = dialog.findViewById(R.id.tvDescription)
        btnDialogSuccess = dialog.findViewById(R.id.btnDialogSuccess)

        tvDescription.text = description
        btnDialogSuccess.setOnClickListener {
            onDismiss()
            dialog.dismiss()
        }
        dialog.show()
    }
}