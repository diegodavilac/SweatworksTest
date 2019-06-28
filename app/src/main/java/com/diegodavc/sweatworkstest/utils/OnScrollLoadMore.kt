package com.diegodavc.sweatworkstest.utils

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class OnScrollLoadMore(val layoutManager: GridLayoutManager, val listener: LoadMoreListener): RecyclerView.OnScrollListener() {

    var load: Boolean = false

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        load = true
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (dy > 0){
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount
            val pastVisiblesItems = layoutManager.findFirstVisibleItemPosition()
            if (load){
                if ((visibleItemCount + pastVisiblesItems) >= totalItemCount){
                    load = false
                    listener.loadMoreElements()
                }
            }
        }
    }
}

interface LoadMoreListener {
    fun loadMoreElements()
}