package kaleidot725.ashiato.di.repository

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import androidx.core.content.ContextCompat
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import kaleidot725.ashiato.di.service.weather.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class LocationRepositoryImpl(
    private val context: Context,
    private val provider: String,
    private val minTime: Int,
    private val minDistance: Int,
    private val geocoder: Geocoder,
    private val locationManager: LocationManager,
    private val weatherService: WeatherService,
    private val weatherAppId: String
) : LocationRepository {

    override var running: Boolean = false
        private set

    private val _update: PublishSubject<Date> = PublishSubject.create()
    override val update: Subject<Date> get() = _update


    override var lastUpdate: Date = Date()
        private set

    private val _altitude: PublishSubject<Double> = PublishSubject.create()
    override val altitude: Subject<Double> = _altitude
    override var lastAltitude: Double = 0.0
        private set

    private val _latitude: PublishSubject<Double> = PublishSubject.create()
    override val latitude: Subject<Double> = _latitude
    override var lastLatitude: Double = 0.0
        private set

    override val longitude: PublishSubject<Double> = PublishSubject.create()
    override var lastLongitude: Double = 0.0
        private set

    private val _address: PublishSubject<String> = PublishSubject.create()
    override val address: Subject<String> = _address
    override var lastAddress: String = "Unknown Address"
        private set

    private val _nullWeather = AllWeather(
        "",
        Clouds(0),
        0,
        Coord(0.0, 0.0),
        0,
        0,
        Main(0, 0, 0.0, 0.0, 0.0),
        "",
        Sys("", 0, 0.0, 0, 0, 0),
        0,
        0,
        listOf(Weather("Unknown Weather", "", 0, "Unknown Weather")),
        Wind(0, 0.0)
    )

    private val _weather: PublishSubject<AllWeather> = PublishSubject.create()
    override val weather: Subject<AllWeather> = _weather
    override var lastWeather: AllWeather = _nullWeather
        private set

    private val locationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            CoroutineScope(Dispatchers.IO).launch {
                updateLocation(location)
            }
        }

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}
    }

    override fun start(activity: Activity) {
        if (running) {
            throw IllegalStateException("already have started")
        }

        if (!(ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED)
        ) {
            throw IllegalStateException("don`t have permited ACCESS_FILE_LOCATION")
        }

        running = true
        locationManager.requestLocationUpdates(
            provider,
            minTime.toLong(),
            minDistance.toFloat(),
            locationListener
        )
    }

    override fun stop() {
        if (!(ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED)
        ) {
            throw IllegalStateException("don`t have permited ACCESS_FILE_LOCATION")
        }

        if (!running) {
            throw IllegalStateException("already have started")
        }

        running = false
        locationManager.removeUpdates(locationListener)
    }

    override fun dispose() {
        update.onComplete()
        altitude.onComplete()
        latitude.onComplete()
        longitude.onComplete()
        disposed = true
    }

    private var disposed: Boolean = false
    override fun isDisposed() = disposed

    private suspend fun updateLocation(location: Location) {
        update.onNext(Date(location.time))
        lastUpdate = Date(location.time)

        altitude.onNext(location.altitude)
        lastAltitude = location.altitude

        latitude.onNext(location.latitude)
        lastLatitude = location.latitude

        longitude.onNext(location.longitude)
        lastLongitude = location.longitude

        lastAddress = getAddress(lastLatitude, lastLongitude)
        address.onNext(lastAddress)

//        lastWeather = getWeather(lastLatitude, lastLongitude)
//        weather.onNext(lastWeather)
    }

    private fun getAddress(latitude: Double, longitude: Double): String {
        try {
            val address = geocoder.getFromLocation(latitude, longitude, 1).first()
            return address.getAddressLine(0)
        } catch (e: Exception) {
            return "Unknown Address"
        }
    }

//    private fun getWeather(latitude: Double, longitude: Double): AllWeather {
//        var weather = _nullWeather
//
//        try {
//            val response =
//                weatherService.getByCoordinates(latitude, longitude, weatherAppId).execute()
//            if (response.body() != null) {
//                weather = response.body() as AllWeather
//            }
//        } catch (e: Exception) {
//            Log.v("LocationRepositoryImpl", e.toString() + e.stackTrace.toString())
//        }
//
//        return weather
//    }
}