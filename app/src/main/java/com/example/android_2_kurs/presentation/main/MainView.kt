package com.example.android_2_kurs.presentation.main


import android.content.Context
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android_2_kurs.presentation.BaseView
import com.example.android_2_kurs.presentation.entity.City
import com.example.android_2_kurs.presentation.recyclerview.CityAdapter
import com.example.android_2_kurs.presentation.recyclerview.CityHolder
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.Skip

@AddToEndSingle
interface MainView : MvpView, BaseView{
    fun goNewActivity(id: Int)
    fun setCityAdapter(adapter: ListAdapter<City, CityHolder>)
}