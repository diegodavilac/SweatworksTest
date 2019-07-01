package com.diegodavc.sweatworkstest.presentation.UserDetail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.Menu
import android.view.MenuItem
import com.diegodavc.sweatworkstest.App
import com.diegodavc.sweatworkstest.R
import com.diegodavc.sweatworkstest.data.UserRepository
import com.diegodavc.sweatworkstest.data.model.User
import com.diegodavc.sweatworkstest.utils.loadImage
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_user_detail.*
import kotlinx.android.synthetic.main.toolbar.*

class UserDetailActivity : AppCompatActivity(), DetailContract.View {

    private lateinit var user: User
    private var fromDb: Boolean = false
    private var favoriteItem: MenuItem? = null
    private lateinit var presenter: DetailsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)
        initToolbar()

        var userString = intent.extras?.getString("user", "")
        fromDb = intent.extras?.getBoolean("fromDB", false)!!

        if (userString != null && userString.isNotEmpty()) {
            user = Gson().fromJson(userString, User::class.java)
        }

        bindUser()

        presenter = DetailsPresenter(UserRepository(App.database.userDAO(), App.services), this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu_details, menu)
        favoriteItem = menu?.findItem(R.id.action_favorite)
        if (fromDb) favoriteItem?.icon = getDrawable(R.drawable.ic_action_heart_red)
        else presenter.isSaved(user)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId){
            R.id.action_favorite -> {
                if (fromDb){
                    presenter.deleteUser(user)
                }else{
                    presenter.saveUser(user)
                }
            }
            R.id.action_add -> {
                val intent = Intent(Intent.ACTION_INSERT)
                intent.type = ContactsContract.Contacts.CONTENT_TYPE
                intent.putExtra(ContactsContract.Intents.Insert.NAME, user.name)
                intent.putExtra(ContactsContract.Intents.Insert.EMAIL, user.email)
                intent.putExtra(ContactsContract.Intents.Insert.PHONE, user.phone)
                startActivity(intent)
            }
            android.R.id.home ->{
                onBackPressed()
            }

        }

        return super.onOptionsItemSelected(item)
    }

    override fun userSaved() {
        favoriteItem?.icon = getDrawable(R.drawable.ic_action_heart_red)
    }

    override fun userDeleted() {
        favoriteItem?.icon = getDrawable(R.drawable.ic_action_heart)
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
    }

    private fun bindUser() {
        tv_name.text = user.name
        tv_email.text = user.email
        tv_phone.text = user.phone

        iv_user.loadImage(this, user.picture()["large"].asString)
    }
}
