package com.example.android_2_kurs.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import com.example.android_2_kurs.R
import com.example.android_2_kurs.services.ApiFactory
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private val api = ApiFactory.weatherAPI
    private val PERMISSION_LOCATION = 999
    lateinit var mFusedLocationClient: FusedLocationProviderClient
    private val CONST_LATITUDE = 54.550546
    private val CONST_LONGITUDE = 53.602365

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        requestPermissions()
        showCitiesWeather(CONST_LATITUDE, CONST_LONGITUDE)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                lifecycleScope.launch {
                    try {
                        query?.let {
                            api.getWeatherByName(it).run {
                                val intent = Intent(
                                    this@MainActivity,
                                    InfoDetailActivity::class.java
                                )
                                intent.putExtra("id", id)
                                startActivity(intent)
                            }
                        }
                    } catch (e: Exception) {
                        Snackbar.make(
                            findViewById(android.R.id.content),
                            "This city was not found!", Snackbar.LENGTH_LONG
                        ).show()
                    }
                }


                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }


        }
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
                Log.d("MYTAG", "Получены пермишены")
                getLocation()
            } else {
                Log.d("MYTAG", "Не получены пермишены")
                //showCitiesWeather(55.7887, 49.1221)
                //showSnackBar("По умолчанию выбрана Казань")
            }
        }
    }

//    private fun findCityByName(name: String) {
//        lifecycleScope.launch {
//            try {
//                val weather = api.getWeatherByName(name)
//                startDetailInformationActivity(weather.id)
//            } catch (e: IOException) {
//                showSnackBar("Нет интернета")
//            } catch (e: HttpException) {
//                showSnackBar("Город не найден")
//            }
//        }
//    }

    private fun getLocation() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            mFusedLocationClient?.lastLocation?.addOnCompleteListener {
                if (it.result != null) {
                    //showCitiesWeather(it.result.latitude, it.result.longitude)
                } else {
                    //showCitiesWeather(55.7887, 49.1221)
                    //showSnackBar("По умолчанию выбрана Казань")
                }
            }
        }
    }


    private fun showCitiesWeather(latitude: Double, longitude: Double) {
        lifecycleScope.launch {
            val list = api.getWeatherCities(latitude, longitude, 10).list
            Log.d("MYTAG", list.toString())
//            adapter.submitList(list.map { weather ->
//                City(weather.id, weather.name, weather.main.temp)
//            }.toMutableList())
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
}

