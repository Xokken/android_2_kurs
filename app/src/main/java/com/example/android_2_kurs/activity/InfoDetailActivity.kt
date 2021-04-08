package com.example.android_2_kurs.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.android_2_kurs.R
import com.example.android_2_kurs.services.ApiFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_info_detail.*
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

class InfoDetailActivity : AppCompatActivity() {
    private val api = ApiFactory.weatherAPI
    private val TYPE_TEMP = "°C"
    private val simpleDateFormat = SimpleDateFormat("h:mm a")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_detail)
        val id = intent.getIntExtra("id", -1)
        getInfo(id)

    }

    private fun getInfo(id: Int){
        lifecycleScope.launch{
            api.getWeatherById(id).run {
                Snackbar.make(
                    findViewById(android.R.id.content),
                    "town: ${name} temp : ${main.temp}", Snackbar.LENGTH_LONG
                ).show()
                tv_nameCity.text = name
                tv_lat.text = coord.lat.toString()
                tv_lon.text = coord.lon.toString()
                tv_temp.text = "Now ${main.temp.toInt()} ${TYPE_TEMP}"
                tv_sunrise.text = simpleDateFormat.format(sys.sunrise)
                tv_sunset.text = simpleDateFormat.format(sys.sunset)
                tv_hum.text = main.humidity.toString()
                tv_desc.text = weather[0].description
                tv_min_temp.text = "min ${main.tempMin} ${TYPE_TEMP}"
                tv_max_temp.text = "max ${main.tempMax} ${TYPE_TEMP}"
                tv_feels_temp.text = "${main.feelsLike.toInt()} ${TYPE_TEMP}"
            }
        }
    }
}