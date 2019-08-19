package kaleidot725.ashiato.di.repository

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import java.util.*

class LocationRepositoryImpl(
    private val context: Context,
    private val provider: String,
    private val minTime: Int,
    private val minDistance: Int
) : LocationRepository {

    private val locationManager: LocationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

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
        locationManager.requestLocationUpdates(provider, minTime.toLong(), minDistance.toFloat(), locationListener)
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
}