package kaleidot725.highestpeaks.model.service

import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import java.lang.IllegalStateException
import java.security.Provider
import java.util.*

class LocationService(val context : Context) : Disposable {

    private val locationManager : LocationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    private var _running : Boolean = false
    val running get() = _running

    private var _disposed : Boolean = false
    val diposed get() = _disposed

    val update : PublishSubject<Date> = PublishSubject.create()
    var lastUpdate : Date? = null
        private set
        get

    val altitude : PublishSubject<Double> = PublishSubject.create()
    var lastAltitude : Double? = null
        private set
        get

    val latitude : PublishSubject<Double> = PublishSubject.create()
    var lastLatitude : Double? = null
        private set
        get

    val longitude : PublishSubject<Double> = PublishSubject.create()
    var lastLongitude : Double? = null
        private set
        get

    val locationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            update.onNext(Date(location.time))
            lastUpdate = Date(location.time)

            altitude.onNext(location.altitude)
            lastAltitude = location.altitude

            latitude.onNext(location.latitude)
            lastLatitude = location.latitude

            longitude.onNext(location.longitude)
            lastLongitude = location.longitude

            Log.v("GPS", "高度:${location.altitude} 緯度:${location.latitude} 経度:${location.longitude}")
        }

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {

        }

        override fun onProviderEnabled(provider: String) {

        }

        override fun onProviderDisabled(provider: String) {

        }
    }

    fun start(provider : String, minTime : Int, minDistance: Int){
        if (!(ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION ) == PackageManager.PERMISSION_GRANTED)) {
            throw IllegalStateException("don`t have permited ACCESS_FILE_LOCATION")
        }

        if (_running) {
            throw IllegalStateException("already have started")
        }

        _running = true
        locationManager.requestLocationUpdates(provider, minTime.toLong(), minDistance.toFloat(), locationListener)
    }

    fun stop(){
        if (!(ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION ) == PackageManager.PERMISSION_GRANTED)) {
            throw IllegalStateException("don`t have permited ACCESS_FILE_LOCATION")
        }

        if (!_running) {
            throw IllegalStateException("already have started")
        }

        _running = false
        locationManager.removeUpdates(locationListener)
    }

    override fun dispose() {
        update.onComplete()
        altitude.onComplete()
        latitude.onComplete()
        longitude.onComplete()
        _disposed = true
    }

    override fun isDisposed() = _disposed
}