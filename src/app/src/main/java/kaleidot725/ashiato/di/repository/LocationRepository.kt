package kaleidot725.ashiato.di.repository

import android.app.Activity
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.Subject
import java.util.*

interface LocationRepository : Disposable {
    val running : Boolean

    val update : Subject<Date>
    val lastUpdate : Date

    val altitude : Subject<Double>
    val lastAltitude : Double

    val latitude : Subject<Double>
    val lastLatitude : Double

    val longitude : Subject<Double>
    val lastLongitude : Double

    fun start(activity : Activity)
    fun stop()
}