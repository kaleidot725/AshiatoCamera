package kaleidot725.ashiato.di.service.weather

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("/data/2.5/weather")
    fun getByCoordinates(@Query("lat")latitude : Double, @Query("lon") longitude : Double, @Query("APPID") appid: String) : Call<AllWeather>
}