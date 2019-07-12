package kaleidot725.highestpeaks.model.repository

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

class LocationRepositoryImpl(val context : Context) : Disposable {

    private val locationManager : LocationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    var running : Boolean = false
        private set

    var disposed : Boolean = false
        private set

    val update : PublishSubject<Date> = PublishSubject.create()
    var lastUpdate : Date? = null
        private set

    val altitude : PublishSubject<Double> = PublishSubject.create()
    var lastAltitude : Double? = null
        private set

    val latitude : PublishSubject<Double> = PublishSubject.create()
    var lastLatitude : Double? = null
        private set

    val longitude : PublishSubject<Double> = PublishSubject.create()
    var lastLongitude : Double? = null
        private set

    private val locationListener = object : LocationListener {
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

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}
    }

    fun start(provider : String, minTime : Int, minDistance: Int){
        if (!(ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION ) == PackageManager.PERMISSION_GRANTED)) {
            throw IllegalStateException("don`t have permited ACCESS_FILE_LOCATION")
        }

        if (running) {
            throw IllegalStateException("already have started")
        }

        running = true
        locationManager.requestLocationUpdates(provider, minTime.toLong(), minDistance.toFloat(), locationListener)
    }

    fun stop(){
        if (!(ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION ) == PackageManager.PERMISSION_GRANTED)) {
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

    override fun isDisposed() = disposed
}