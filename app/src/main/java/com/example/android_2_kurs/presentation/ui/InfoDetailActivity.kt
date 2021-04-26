package com.example.android_2_kurs.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.android_2_kurs.R
import com.example.android_2_kurs.data.RoomRepositoryImpl
import com.example.android_2_kurs.data.WeatherRepositoryImpl
import com.example.android_2_kurs.data.api.ApiFactory
import com.example.android_2_kurs.data.api.response.WeatherResponse
import com.example.android_2_kurs.data.room.AppDatabase
import com.example.android_2_kurs.domain.FindAndSaveCitiesUseCase
import com.example.android_2_kurs.presentation.entity.CityResponse
import kotlinx.android.synthetic.main.activity_info_detail.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat


class InfoDetailActivity : AppCompatActivity() {
    private lateinit var findAndSaveCitiesUseCase: FindAndSaveCitiesUseCase
    private val api = ApiFactory.weatherAPI
    private val TYPE_TEMP = "°C"
    private val simpleDateFormat = SimpleDateFormat("h:mm a")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        findAndSaveCitiesUseCase = FindAndSaveCitiesUseCase(
            WeatherRepositoryImpl(ApiFactory.weatherAPI),
            RoomRepositoryImpl(AppDatabase(this)),
            Dispatchers.IO
        )
        setContentView(R.layout.activity_info_detail)
        val id = intent.getIntExtra("id", -1)
        getInfo(id)
    }

    private fun getInfo(id: Int){
        lifecycleScope.launch {
            try {
                api.getWeatherById(id).run {
                    findAndSaveCitiesUseCase.saveCityResponse(getCityResponse(this))
                    setInfo(getCityResponse(this))
                }
            } catch (e: Exception){
                setInfo(findAndSaveCitiesUseCase.getCityResponse(id))
            }
        }
    }

    private fun getCityResponse(response: WeatherResponse): CityResponse{
        return response?.let{
            CityResponse(
                it.id,
                it.name,
                it.coord.lat,
                it.coord.lon,
                it.main.temp,
                it.sys.sunrise,
                it.sys.sunset,
                it.main.humidity,
                it.weather[0].description,
                it.main.tempMin,
                it.main.tempMax,
                it.main.feelsLike
            )
        }
    }

    private fun setInfo(cityResponse: CityResponse){
        cityResponse.run {
            tv_nameCity.text = name
            tv_lat.text = lat.toString()
            tv_lon.text = lon.toString()
            tv_temp.text = "Now ${temp.toInt()} ${TYPE_TEMP}"
            tv_sunrise.text = simpleDateFormat.format(sunrise)
            tv_sunset.text = simpleDateFormat.format(sunset)
            tv_hum.text = humidity.toString()
            tv_desc.text = desc
            tv_min_temp.text = "min ${minTemp} ${TYPE_TEMP}"
            tv_max_temp.text = "max ${maxTemp} ${TYPE_TEMP}"
            tv_feels_temp.text = "${feelsTemp.toInt()} ${TYPE_TEMP}"
        }
    }
}