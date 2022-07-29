package dev.redfox.weatherapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.redfox.weatherapp.model.Weather
import dev.redfox.weatherapp.repository.WeatherRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel
@Inject
constructor(private val repository: WeatherRepository) : ViewModel(){

private val _resp = MutableLiveData<Weather>()
    val weatherResp: LiveData<Weather>
    get() = _resp


    init{
        getWeather()
    }

    private fun getWeather() = viewModelScope.launch {
        repository.getWeather().let { response ->

            if(response.isSuccessful){
                _resp.postValue((response.body()))
            }else{
                Log.d("Tag","getWeather Error Response : ${response.message()}")
            }
        }
    }

}