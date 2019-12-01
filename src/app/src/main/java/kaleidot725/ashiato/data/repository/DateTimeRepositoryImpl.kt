package kaleidot725.ashiato.data.repository

import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import java.util.*
import kotlin.concurrent.timerTask

class DateTimeRepositoryImpl : DateTimeRepository {
    private var _running = false
    override val running: Boolean get() = _running

    private val _date: PublishSubject<Date> = PublishSubject.create()
    override val date: Subject<Date> get() = _date

    private var _lastDate: Date = Date()
    override val lastDate: Date get() = _lastDate

    private var timer: Timer? = null

    override fun start(intervalMs: Long) {
        if (running) {
            throw Exception("this repository is running!!")
        }

        timer = Timer()
        timer?.scheduleAtFixedRate(timerTask {
            val date = Date()
            _date.onNext(date)
            _lastDate = date
        }, 0, intervalMs)
    }

    override fun stop() {
        if (!running) {
            throw Exception("this repository is not running!!")
        }

        timer?.cancel()
        _date.onComplete()
    }
}