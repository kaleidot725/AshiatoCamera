package kaleidot725.highestpeaks.di.repository

import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
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

    fun start(provider : String, minTime : Int, minDistance : Int)
    fun stop()
}