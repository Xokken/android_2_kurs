package com.example.android_2_kurs.presentation.detail

import android.util.Log
import com.example.android_2_kurs.data.api.response.WeatherResponse
import com.example.android_2_kurs.domain.FindAndSaveCitiesUseCase
import com.example.android_2_kurs.presentation.entity.CityResponse
import kotlinx.coroutines.launch
import moxy.MvpPresenter
import moxy.presenterScope

class DetailPresenter(
    private val findAndSaveCitiesUseCase: FindAndSaveCitiesUseCase
): MvpPresenter<DetailView>(){

    fun getCityInfo(id: Int){
        presenterScope.launch {
            try {
                Log.d("MYTAG", "11111111")
                viewState.showLoading()
                findAndSaveCitiesUseCase.getWeatherById(id).run {
                    findAndSaveCitiesUseCase.saveCityResponse(getCityResponse(this))
                    setInfo(getCityResponse(this))
                }
            } catch (e: Exception){
                Log.d("MYTAG", "2222222")
                viewState.consumerError(e)
                setInfo(findAndSaveCitiesUseCase.getCityResponse(id))
            } finally {
                viewState.hideLoading()
            }

        }
    }

    private fun setInfo(cityResponse: CityResponse){
        Log.d("MYTAG", cityResponse?.toString())
        cityResponse?.run {
            viewState.setName(name)
            viewState.setLat(lat)
            viewState.setLon(lon)
            viewState.setTemp(temp.toInt())
            viewState.setSunrise(sunrise)
            viewState.setSunset(sunset)
            viewState.setHumidity(humidity)
            viewState.setDesc(desc)
            viewState.setMinTemp(minTemp)
            viewState.setMaxTemp(maxTemp)
            viewState.setFeelsTemp(feelsTemp.toInt())
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
    
}