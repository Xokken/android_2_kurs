package com.example.android_2_kurs.presentation.detail

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.example.android_2_kurs.R
import com.example.android_2_kurs.data.LocationRepositoryImpl
import com.example.android_2_kurs.data.RoomRepositoryImpl
import com.example.android_2_kurs.data.WeatherRepositoryImpl
import com.example.android_2_kurs.data.api.ApiFactory
import com.example.android_2_kurs.data.room.AppDatabase
import com.example.android_2_kurs.domain.FindAndSaveCitiesUseCase
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_info_detail.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import java.text.SimpleDateFormat


class InfoDetailActivity : MvpAppCompatActivity(), DetailView {

    private val TYPE_TEMP = "°C"
    private val simpleDateFormat = SimpleDateFormat("h:mm a")

    @InjectPresenter
    lateinit var detailPresenter: DetailPresenter

    @ProvidePresenter
    fun initDetailPresenter(): DetailPresenter {
        return DetailPresenter(
            FindAndSaveCitiesUseCase(
                WeatherRepositoryImpl(ApiFactory.weatherAPI),
                RoomRepositoryImpl(AppDatabase(this)),
                Dispatchers.Default
            ),
        )

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_detail)
        val id = intent.getIntExtra("id", -1)
        detailPresenter.getCityInfo(id)
    }

    override fun setName(name: String) {
        tv_nameCity.text = name
    }

    override fun setLat(lat: Double) {
        tv_lat.text = lat.toString()
    }

    override fun setLon(lon: Double) {
        tv_lon.text = lon.toString()
    }

    override fun setTemp(temp: Int) {
        tv_temp.text = "Now ${temp.toInt()} ${TYPE_TEMP}"
    }

    override fun setSunrise(sunrise: Int) {
        tv_sunrise.text = simpleDateFormat.format(sunrise)
    }

    override fun setSunset(sunset: Int) {
        tv_sunset.text = simpleDateFormat.format(sunset)
    }

    override fun setHumidity(hum: Int) {
        tv_hum.text = hum.toString()
    }

    override fun setDesc(desc: String) {
        tv_desc.text = desc
    }

    override fun setMinTemp(minTemp: Double) {
        tv_min_temp.text = "min ${minTemp} ${TYPE_TEMP}"
    }

    override fun setMaxTemp(maxTemp: Double) {
        tv_max_temp.text = "max ${maxTemp} ${TYPE_TEMP}"
    }

    override fun setFeelsTemp(feels: Int) {
        tv_feels_temp.text = "${feels.toInt()} ${TYPE_TEMP}"
    }

    override fun showLoading() {
        progress_detail.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progress_detail.visibility = View.GONE
    }

    override fun showSnackbar(text: String) {
        Snackbar.make(
            findViewById(android.R.id.content),
            text, Snackbar.LENGTH_LONG
        ).show()
    }

    override fun consumerError(throwable: Throwable) {
        showSnackbar(throwable.localizedMessage)
    }
}

