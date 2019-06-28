package com.diegodavc.sweatworkstest.presentation.Home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.diegodavc.sweatworkstest.App
import com.diegodavc.sweatworkstest.R
import com.diegodavc.sweatworkstest.data.model.User
import com.diegodavc.sweatworkstest.data.network.RequestManager
import com.diegodavc.sweatworkstest.data.network.model.UserResponse
import com.diegodavc.sweatworkstest.utils.LoadMoreListener
import com.diegodavc.sweatworkstest.utils.OnScrollLoadMore
import com.diegodavc.sweatworkstest.utils.PreferencesUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.toolbar
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity(), LoadMoreListener{


    private var searchView: SearchView?  = null
    private var mainAdapter: UserListAdapter? = null


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

        bindView()

        App.services?.getUsers(50, null ,1 )
            .observeOn(AndroidSchedulers.mainThread())
            .map { saveAndReturnSeed(it) }
            .doOnSuccess { loadUsers(it) }

    }

    private fun bindView(){
        rv_general.layoutManager = GridLayoutManager(this, 3)
        rv_favourites.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)

        mainAdapter = UserListAdapter {
            //TODO
            println("TEST")
        }

        rv_general.adapter = mainAdapter

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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun saveAndReturnSeed(response: UserResponse) : List<User> {
        //TODO save seed
        return response.results
    }

    private fun loadUsers(users : List<User>){
        println("TEST2")
        mainAdapter?.addUsers(users)
    }
}
