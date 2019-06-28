package com.diegodavc.sweatworkstest.presentation.Home

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.diegodavc.sweatworkstest.R
import com.diegodavc.sweatworkstest.data.model.User
import com.diegodavc.sweatworkstest.utils.inflate
import kotlinx.android.synthetic.main.item_grid.view.*

class UserListAdapter(val listener: (User) -> Unit) : RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {

    var data : MutableList<User> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder
            = UserViewHolder(parent.inflate(R.layout.item_grid))

    override fun getItemCount(): Int  = data.size

    override fun getItemId(position: Int): Long = position.toLong()

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = data[position]
        holder.bind(user, listener)
    }

    fun addUsers(users : List<User>){
        data.addAll(users)
        notifyDataSetChanged()
    }


    class UserViewHolder(view : View): RecyclerView.ViewHolder(view){
        fun bind(user : User , listener: (User) -> Unit) = with(itemView){

            itemView.card.setOnClickListener { listener }

            val requestOptions = RequestOptions()
            requestOptions.placeholder(R.drawable.ic_broken_image)
            requestOptions.error(R.drawable.ic_broken_image)
            Glide.with(itemView).load(user.picture.get("medium").asString).apply(requestOptions).into(itemView.iv_user)

        }
    }

}