package com.example.android_2_kurs.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.android_2_kurs.R
import com.example.android_2_kurs.services.ApiFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_info_detail.*
import kotlinx.coroutines.launch

class InfoDetailActivity : AppCompatActivity() {
    private val api = ApiFactory.weatherAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_detail)
        val id = intent.getIntExtra("id", -1)


        lifecycleScope.launch{
            api.getWeatherById(id).run {
                Snackbar.make(
                    findViewById(android.R.id.content),
                    "town: ${name} temp : ${main.temp}", Snackbar.LENGTH_LONG
                ).show()
                tv_nameCity.text = name
                tv_temp.text = "Текущая ${main.temp}"
                tv_description.text = weather[0].description
                tv_feels_temp.text = "Ощущается ${main.feelsLike}"
            }
        }

    }
}