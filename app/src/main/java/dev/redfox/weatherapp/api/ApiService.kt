package dev.redfox.weatherapp.api

import dev.redfox.weatherapp.model.Weather
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("weather/Udaipur")
    suspend fun  getWeather(): Response<Weather>
}