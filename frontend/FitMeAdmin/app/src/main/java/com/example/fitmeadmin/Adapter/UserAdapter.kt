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

import com.example.fitmeadmin.EntityDao.UserItem
import com.example.fitmeadmin.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class UserAdapter(
    private val userCardClick: (UserItem) -> Unit,
    private val context: Context
) : RecyclerView.Adapter<UserAdapter.UserAdapterViewHolder>() {

    private val userlist = ArrayList<UserItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.UserAdapterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.user_list_item, parent, false)
        return UserAdapter.UserAdapterViewHolder(listItem)
    }

    override fun getItemCount(): Int {
        return userlist.size
    }

    override fun onBindViewHolder(holder: UserAdapter.UserAdapterViewHolder, position: Int) {
        holder.bind(userlist[position], userCardClick, context)
    }

    fun setList(userItem: List<UserItem>) {
        userlist.clear()
        userlist.addAll(userItem)
        notifyDataSetChanged()
    }

    class UserAdapterViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(userItem: UserItem, userCardClick: (UserItem) -> Unit, context: Context) {
            val username = view.findViewById<TextView>(R.id.from)
            val email = view.findViewById<TextView>(R.id.charge)
            val tel = view.findViewById<TextView>(R.id.experi)
            val imgWrkThumb = view.findViewById<ImageView>(R.id.imageView5)
            val cvTcard = view.findViewById<FloatingActionButton>(R.id.floatingActionButton2)


            val imgUrl = userItem.avatar

            username.text = userItem.username
            email.text = userItem.email
            tel.text = userItem.telephone

            Glide.with(context)
                .load(imgUrl)
                .fitCenter()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .placeholder(R.drawable.animegife)
                .into(imgWrkThumb)

            cvTcard.setOnClickListener {
                userCardClick(userItem)
            }
        }
    }
}