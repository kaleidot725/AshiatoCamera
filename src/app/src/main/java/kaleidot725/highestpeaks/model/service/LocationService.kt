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
import java.util.*

class LocationService(context : Context) : Disposable {

    private val context : Context = context
    private var running : Boolean = false
    private var disposed : Boolean = false
    private val locationManager : LocationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    val update : PublishSubject<Date> = PublishSubject.create()
    val altitude : PublishSubject<Double> = PublishSubject.create()
    val latitude : PublishSubject<Double> = PublishSubject.create()
    val longitude : PublishSubject<Double> = PublishSubject.create()
    val locationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            update.onNext(Date(location.time))
            altitude.onNext(location.altitude)
            latitude.onNext(location.latitude)
            longitude.onNext(location.longitude)
            Log.v("GPS", "高度:${location.altitude} 緯度:${location.latitude} 経度:${location.longitude}")
        }

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {

        }

        override fun onProviderEnabled(provider: String) {

        }

        override fun onProviderDisabled(provider: String) {

        }
    }

    fun start(){
        if (!(ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION ) == PackageManager.PERMISSION_GRANTED)) {
            throw IllegalStateException("don`t have permited ACCESS_FILE_LOCATION")
        }

        if (running) {
            throw IllegalStateException("already have started")
        }

        running = true
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 50f, locationListener)
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