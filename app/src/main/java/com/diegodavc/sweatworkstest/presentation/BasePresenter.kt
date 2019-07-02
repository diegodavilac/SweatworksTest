package com.diegodavc.sweatworkstest.presentation

interface BasePresenter<T> {
    fun setView(view: T)
    fun dropView()
}