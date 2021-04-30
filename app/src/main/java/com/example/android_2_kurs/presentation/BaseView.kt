package com.example.android_2_kurs.presentation

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.Skip

@AddToEndSingle
interface BaseView: MvpView {
    fun showLoading()
    fun hideLoading()
    fun showSnackbar(text: String)
    @Skip
    fun consumerError(throwable: Throwable)
}