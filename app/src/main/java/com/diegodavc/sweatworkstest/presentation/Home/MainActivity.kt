package com.diegodavc.sweatworkstest.presentation.Home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.animation.AnimationUtils
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.diegodavc.sweatworkstest.App
import com.diegodavc.sweatworkstest.R
import com.diegodavc.sweatworkstest.data.UserRepository
import com.diegodavc.sweatworkstest.data.model.User
import com.diegodavc.sweatworkstest.data.network.RequestManager
import com.diegodavc.sweatworkstest.data.network.model.UserResponse
import com.diegodavc.sweatworkstest.presentation.UserDetail.UserDetailActivity
import com.diegodavc.sweatworkstest.utils.LoadMoreListener
import com.diegodavc.sweatworkstest.utils.OnScrollLoadMore
import com.diegodavc.sweatworkstest.utils.PreferencesUtil
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.toolbar
import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity(), LoadMoreListener, HomeContract.View{



    private var searchView: SearchView?  = null
    private var mainAdapter: UserListAdapter? = null
    private lateinit var presenter: HomePresenter

    private val onQueryTextListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String): Boolean {

            return true
        }

        override fun onQueryTextChange(newText: String): Boolean {

            return true
        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(false)

        bindView()

        presenter = HomePresenter(UserRepository(App.database.userDAO(), App.services), this)

        presenter.getUsers()
    }

    private fun bindView(){
        rv_general.layoutManager = GridLayoutManager(this, 3)
        rv_favourites.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        rv_general.addOnScrollListener( OnScrollLoadMore( rv_general.layoutManager as GridLayoutManager , this))

        mainAdapter = UserListAdapter {
            val intent = Intent(this, UserDetailActivity::class.java)
            intent.putExtra("user", Gson().toJson(it))
            startActivity(intent)
        }

        rv_general.adapter = mainAdapter
        rv_general.layoutAnimation = AnimationUtils.loadLayoutAnimation(this, R.anim.fade_in_anim)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)

        searchView = menu?.findItem(R.id.action_search)?.actionView as SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.maxWidth = Integer.MAX_VALUE
        searchView?.setOnQueryTextListener(onQueryTextListener)

        return super.onCreateOptionsMenu(menu)
    }

    override fun loadMoreElements() {
        presenter.getUsers()

    }

    override fun loadUsers(users: List<User>) {
        mainAdapter?.addUsers(users)
    }

    override fun loadSavedUsers(users: List<User>) {

    }

    override fun refreshSavedUsers() {

    }

    override fun hideSavedUser() {

    }

}
