package com.example.android_2_kurs.presentation.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.android_2_kurs.R
import com.example.android_2_kurs.data.RoomRepositoryImpl
import com.example.android_2_kurs.data.WeatherRepositoryImpl
import com.example.android_2_kurs.presentation.recyclerview.CityAdapter
import com.example.android_2_kurs.data.api.ApiFactory
import com.example.android_2_kurs.data.room.AppDatabase
import com.example.android_2_kurs.domain.FindAndSaveCitiesUseCase
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var findAndSaveCitiesUseCase: FindAndSaveCitiesUseCase
    private val PERMISSION_LOCATION = 999
    lateinit var mFusedLocationClient: FusedLocationProviderClient
    private val CONST_LATITUDE = 54.550546
    private val CONST_LONGITUDE = 53.602365
    private var adapter: CityAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findAndSaveCitiesUseCase = FindAndSaveCitiesUseCase(
            WeatherRepositoryImpl(ApiFactory.weatherAPI),
            RoomRepositoryImpl(AppDatabase(this)),
            Dispatchers.IO)
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        requestPermissions()

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
                    findCityByName(it)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        }
        )


    }


    private fun findCityByName(name: String) {
        lifecycleScope.launch {
            try {
                findAndSaveCitiesUseCase.findWeatherCity(name).run {
                    val intent = Intent(
                        this@MainActivity,
                        InfoDetailActivity::class.java
                    )
                    intent.putExtra("id", id)
                    startActivity(intent)
                }
            } catch (e: Exception) {
                Snackbar.make(
                    findViewById(android.R.id.content),
                    "This city was not found!", Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun showCitiesWeather(latitude: Double, longitude: Double) {
        adapter = CityAdapter({
            val intent = Intent(this@MainActivity, InfoDetailActivity::class.java)
            intent.putExtra("id", it.id)
            this@MainActivity.startActivity(intent)
        }
            , applicationContext)
        lifecycleScope.launch {
            try{
                Log.d("MYTAG", "DJITKKKKK")
                val list = findAndSaveCitiesUseCase.findWeatherCities(latitude, longitude)
                findAndSaveCitiesUseCase.clearAllCities()
                findAndSaveCitiesUseCase.saveListCities(list)
                adapter?.submitList(list)
                recyclerView.adapter = adapter
            } catch (e: Exception){
                adapter?.submitList(findAndSaveCitiesUseCase.getListCities())
                recyclerView.adapter = adapter
                Snackbar.make(
                    findViewById(android.R.id.content),
                    "Error connection. This old data senpai!!!", Snackbar.LENGTH_LONG
                ).show()
            }
        }
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
            mFusedLocationClient.lastLocation.addOnCompleteListener {
                if (it.result != null) {
                    showCitiesWeather(it.result.latitude, it.result.longitude)
                } else {
                    showCitiesWeather(CONST_LATITUDE, CONST_LONGITUDE)
                }
            }
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
                Log.d("MYTAG", "Не получено")
                showCitiesWeather(CONST_LATITUDE, CONST_LONGITUDE)
            }
        }
    }
}

