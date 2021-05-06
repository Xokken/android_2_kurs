package com.example.android_2_kurs.presentation.main

import androidx.recyclerview.widget.ListAdapter
import com.example.android_2_kurs.domain.FindAndSaveCitiesUseCase
import com.example.android_2_kurs.domain.LocationRepository
import com.example.android_2_kurs.presentation.entity.City
import com.example.android_2_kurs.presentation.recyclerview.CityHolder
import kotlinx.coroutines.launch
import moxy.MvpPresenter
import moxy.presenterScope
import javax.inject.Inject

class MainPresenter @Inject constructor(
    private val findAndSaveCitiesUseCase: FindAndSaveCitiesUseCase,
    private val locationRepository: LocationRepository
): MvpPresenter<MainView>(){

    suspend fun getLocation() = locationRepository.getUserLocation()

    fun findCityByName(name: String) {
        presenterScope.launch {
            viewState.showLoading()
            try {
                findAndSaveCitiesUseCase.findWeatherCity(name).run {
                    viewState.goNewActivity(id)
                }
            } catch (e: Exception) {
                viewState.consumerError(e)
                viewState.showSnackbar("This city was not found!")
            } finally {
                viewState.hideLoading()
            }
        }
    }

    fun showCitiesWeather(latitude: Double, longitude: Double, adapter: ListAdapter<City, CityHolder>) {
        viewState.showLoading()
        presenterScope.launch {
            try{
                val list = findAndSaveCitiesUseCase.findWeatherCities(latitude, longitude)
                findAndSaveCitiesUseCase.clearAllCities()
                findAndSaveCitiesUseCase.saveListCities(list)
                adapter.submitList(list)
                viewState.setCityAdapter(adapter)
            } catch (e: Exception){
                viewState.consumerError(e)
                adapter.submitList(findAndSaveCitiesUseCase.getListCities())
                viewState.setCityAdapter(adapter)
                viewState.showSnackbar("Error connection. This old data senpai!!!")
            } finally {
                viewState.hideLoading()
            }
        }
    }



}