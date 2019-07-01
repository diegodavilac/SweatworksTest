package com.diegodavc.sweatworkstest.presentation.Home

import android.content.Intent
import android.database.Cursor
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.diegodavc.sweatworkstest.App
import com.diegodavc.sweatworkstest.R
import com.diegodavc.sweatworkstest.data.UserRepository
import com.diegodavc.sweatworkstest.data.model.User
import com.diegodavc.sweatworkstest.data.network.model.UserResponse
import com.diegodavc.sweatworkstest.presentation.UserDetail.UserDetailActivity
import com.diegodavc.sweatworkstest.utils.LoadMoreListener
import com.diegodavc.sweatworkstest.utils.OnScrollLoadMore
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.toolbar


class MainActivity : AppCompatActivity(), LoadMoreListener, HomeContract.View{

    private var searchView: SearchView?  = null
    private var mainAdapter: UserListAdapter? = null
    private var savedAdapter : SavedUserListAdapter? = null
    private lateinit var presenter: HomePresenter

    private val onQueryTextListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(search: String): Boolean {
            val query = "%$search%"
            presenter.getSuggestions(query)
            return true
        }

        override fun onQueryTextChange(newText: String): Boolean {
            val query = "%$newText%"
            presenter.getSuggestions(query)
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

    override fun onResume() {
        super.onResume()
        presenter.getSavedUsers()
    }

    private fun bindView(){
        rv_general.layoutManager = GridLayoutManager(this, 3)
        rv_favourites.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        rv_general.addOnScrollListener( OnScrollLoadMore( rv_general.layoutManager as GridLayoutManager , this))

        mainAdapter = UserListAdapter {
            openUserDetail(it.toUser(), false)
        }

        savedAdapter = SavedUserListAdapter {
            openUserDetail(it, true)
        }

        rv_general.adapter = mainAdapter
        rv_general.layoutAnimation = AnimationUtils.loadLayoutAnimation(this, R.anim.fade_in_anim)

        rv_favourites.adapter = savedAdapter
        rv_favourites.layoutAnimation = AnimationUtils.loadLayoutAnimation(this, R.anim.fade_in_anim)

    }

    private fun openUserDetail(user: User, fromDb: Boolean){
        val intent = Intent(this, UserDetailActivity::class.java)
        intent.putExtra("fromDB", fromDb)
        intent.putExtra("user", Gson().toJson(user))
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)

        searchView = menu?.findItem(R.id.action_search)?.actionView as SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.maxWidth = Integer.MAX_VALUE
        searchView?.setOnQueryTextListener(onQueryTextListener)

        val searchAutoComplete : SearchView.SearchAutoComplete = searchView!!.findViewById(androidx.appcompat.R.id.search_src_text)
        searchAutoComplete.setHintTextColor(Color.WHITE)
        searchAutoComplete.setTextColor(Color.WHITE)

        return super.onCreateOptionsMenu(menu)
    }

    override fun loadMoreElements() {
        presenter.getUsers()

    }

    override fun loadUsers(users: List<UserResponse>) {
        mainAdapter?.addUsers(users)
    }

    override fun loadSavedUsers(users: List<User>) {
        savedAdapter?.clear()
        savedAdapter?.addUsers(users)
    }

    override fun hideSavedUser() {
       cl_favorites.visibility = View.GONE
    }

    override fun loadSuggestions(cursor: Cursor) {
        searchView?.suggestionsAdapter = SuggestionsCursorAdapter(this, cursor) {
            openUserDetail(it, true)
        }
    }

}
