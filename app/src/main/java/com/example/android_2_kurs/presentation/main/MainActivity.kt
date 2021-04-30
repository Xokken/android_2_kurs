package com.example.android_2_kurs.presentation.main

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ListAdapter
import com.example.android_2_kurs.R
import com.example.android_2_kurs.data.LocationRepositoryImpl
import com.example.android_2_kurs.data.RoomRepositoryImpl
import com.example.android_2_kurs.data.WeatherRepositoryImpl
import com.example.android_2_kurs.data.api.ApiFactory
import com.example.android_2_kurs.data.room.AppDatabase
import com.example.android_2_kurs.domain.FindAndSaveCitiesUseCase
import com.example.android_2_kurs.presentation.entity.City
import com.example.android_2_kurs.presentation.recyclerview.CityAdapter
import com.example.android_2_kurs.presentation.recyclerview.CityHolder
import com.example.android_2_kurs.presentation.detail.InfoDetailActivity
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter


class MainActivity : MvpAppCompatActivity(), MainView {

    private val PERMISSION_LOCATION = 999
    private val CONST_LATITUDE = 54.550546
    private val CONST_LONGITUDE = 53.602365
    @InjectPresenter
    lateinit var mainPresenter: MainPresenter

    @ProvidePresenter
    fun initMainPresenter(): MainPresenter {
        return MainPresenter(
            FindAndSaveCitiesUseCase(
                WeatherRepositoryImpl(ApiFactory.weatherAPI),
                RoomRepositoryImpl(AppDatabase(this)),
                Dispatchers.IO),
            LocationRepositoryImpl(LocationServices.getFusedLocationProviderClient(this)))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestPermissions()
        initViews()

    }


    override fun showLoading() {
        progress_main.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progress_main.visibility = View.GONE
    }

    override fun showSnackbar(text: String) {
        Snackbar.make(
            findViewById(android.R.id.content),
            text, Snackbar.LENGTH_LONG
        ).show()
    }

    override fun goNewActivity(id: Int) {
        val intent = Intent(
            this@MainActivity,
            InfoDetailActivity::class.java
        )
        intent.putExtra("id", id)
        startActivity(intent)
    }

    override fun consumerError(throwable: Throwable) {
        showSnackbar(throwable.localizedMessage)
    }

    override fun setCityAdapter(adapter: ListAdapter<City, CityHolder>) {
        recyclerView.adapter = adapter
    }

    private fun initViews(){
        recyclerView.adapter = CityAdapter({
            val intent = Intent(this@MainActivity, InfoDetailActivity::class.java)
            intent.putExtra("id", it.id)
            this@MainActivity.startActivity(intent)
        }
            , applicationContext)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                this@MainActivity,
                DividerItemDecoration.VERTICAL
            )
        )

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    mainPresenter.findCityByName(it)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        }
        )

    }


    private fun getLocation() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            lifecycleScope.launch{
                mainPresenter.getLocation().let {
                    mainPresenter.showCitiesWeather(it.latitude, it.longitude,
                        recyclerView.adapter as ListAdapter<City, CityHolder>
                    )
                }
            }
        } else {
            mainPresenter.showCitiesWeather(CONST_LATITUDE, CONST_LONGITUDE, recyclerView.adapter as ListAdapter<City, CityHolder>)
        }
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this@MainActivity,
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            PERMISSION_LOCATION
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_LOCATION) {
            var areGranted = true
            grantResults.forEach {
                if (it == PackageManager.PERMISSION_DENIED) {
                    areGranted = false
                }
            }
            if (areGranted) {
                Log.d("MYTAG", "Получено")
                getLocation()
            } else {
                mainPresenter.showCitiesWeather(CONST_LATITUDE, CONST_LONGITUDE, recyclerView.adapter as ListAdapter<City, CityHolder>)
            }
        }
    }
}

