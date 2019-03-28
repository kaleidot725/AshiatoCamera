package kaleidot725.highestpeaks.service

import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import io.reactivex.subjects.BehaviorSubject
import java.lang.IllegalStateException

class LocationService(context : Context) {
    val context : Context = context
    var running : Boolean = false
    val locationManager : LocationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    val altitude : BehaviorSubject<Double> = BehaviorSubject.create()
    val latitude : BehaviorSubject<Double> = BehaviorSubject.create()
    val longitude : BehaviorSubject<Double> = BehaviorSubject.create()
    val locationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
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
}