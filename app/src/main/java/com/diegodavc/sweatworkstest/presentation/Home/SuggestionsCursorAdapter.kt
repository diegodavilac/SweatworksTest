package com.diegodavc.sweatworkstest.presentation.Home

import android.content.Context
import android.database.Cursor
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.cursoradapter.widget.CursorAdapter
import com.diegodavc.sweatworkstest.R
import com.diegodavc.sweatworkstest.data.model.User
import com.diegodavc.sweatworkstest.utils.inflate
import com.diegodavc.sweatworkstest.utils.loadImage
import com.google.gson.Gson


class SuggestionsCursorAdapter (context: Context ,
                                cursor: Cursor,
                                val listener: (User) -> Unit): CursorAdapter(context, cursor, false){


    override fun newView(context: Context?, cursor: Cursor?, parent: ViewGroup): View
            = parent.inflate(R.layout.item_suggestion)


    override fun bindView(view: View?, context: Context?, cursor: Cursor?) {

        val user = User(cursor?.getString(cursor.getColumnIndexOrThrow("gender"))!!,
            cursor.getString(cursor.getColumnIndexOrThrow("_id")),
            cursor.getString(cursor.getColumnIndexOrThrow("phone")),
            cursor.getString(cursor.getColumnIndexOrThrow("cell")),
            cursor.getString(cursor.getColumnIndexOrThrow("nat")),
            cursor.getString(cursor.getColumnIndexOrThrow("name")),
            cursor.getString(cursor.getColumnIndexOrThrow("location")),
            cursor.getString(cursor.getColumnIndexOrThrow("dob")),
            cursor.getString(cursor.getColumnIndexOrThrow("picture"))
        )

        val tv_name = view?.findViewById<TextView>(R.id.tv_name)
        tv_name?.text = user.name
        val iv_user = view?.findViewById<ImageView>(R.id.iv_user)

        iv_user?.loadImage(context!!, user.picture()["thumbnail"].asString)

        view?.setOnClickListener {
            listener(user)
        }
    }


}