package com.diegodavc.sweatworkstest.presentation.Home

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.diegodavc.sweatworkstest.R
import com.diegodavc.sweatworkstest.data.model.User
import com.diegodavc.sweatworkstest.utils.inflate
import kotlinx.android.synthetic.main.item_user_saved.view.*

class SavedUserListAdapter(val listener: (User) -> Unit) : RecyclerView.Adapter<SavedUserListAdapter.UserSavedViewHolder>() {

    var data : MutableList<User> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserSavedViewHolder {
        return UserSavedViewHolder(parent.inflate(R.layout.item_user_saved))
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: UserSavedViewHolder, position: Int) {
        val user = data[position]
        holder.bindView(user, listener)
    }

    fun addUsers(users : List<User>){
        data.addAll(users)
        notifyDataSetChanged()
    }

    fun clear(){
        data.clear()
        notifyDataSetChanged()
    }

    class UserSavedViewHolder(view : View) : RecyclerView.ViewHolder(view){
        fun bindView(user: User, listener: (User) -> Unit) = with(itemView){
            itemView.setOnClickListener { listener(user) }

            itemView.tv_user.text = user.name
            val requestOptions = RequestOptions()
            requestOptions.placeholder(R.drawable.ic_broken_image)
            requestOptions.error(R.drawable.ic_broken_image)
            Glide.with(itemView)
                .load(user.picture().get("medium").asString)
                .apply(requestOptions)
                .apply(RequestOptions.circleCropTransform())
                .into(itemView.iv_user)
        }

    }
}