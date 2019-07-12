package kaleidot725.highestpeaks.model.repository

import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import java.util.*

interface LocationRepository : Disposable {
    val running : Boolean

    val update : PublishSubject<Date>
    var lastUpdate : Date?

    val altitude : PublishSubject<Double>
    var lastAltitude : Double?

    val latitude : PublishSubject<Double>
    var lastLatitude : Double?

    val longitude : PublishSubject<Double>
    var lastLongitude : Double?

    fun start(provider : String, minTime : Int, minDistance : Int)
    fun stop()
}